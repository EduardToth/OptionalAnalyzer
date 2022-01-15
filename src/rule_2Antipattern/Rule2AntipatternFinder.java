package rule_2Antipattern;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.Expression;
import org.eclipse.jdt.core.dom.ForStatement;
import org.eclipse.jdt.core.dom.IfStatement;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.MethodInvocation;
import org.eclipse.jdt.core.dom.WhileStatement;

import optionalanalyzer.metamodel.entity.MRule2sAntipattern;
import optionalanalyzer.metamodel.factory.Factory;
import utilities.OptionalInvocationFinder;
import utilities.UtilityClass;

public class Rule2AntipatternFinder{

	public List<MRule2sAntipattern> getMAntipatterns(ASTNode astNode) {
		return getAntipatterns(astNode)
				.stream()
				.map(Rule2Antipattern::getInstance)
				.flatMap(Optional::stream)
				.map(Factory.getInstance()::createMRule2sAntipattern)
				.collect(Collectors.toList());
	}

	private List<MethodInvocation> getAntipatterns(ASTNode astNode) {
		var optionalInvocationFinder = new OptionalInvocationFinder();
		var simpleGetInvocations = optionalInvocationFinder.getInvocations(astNode, "get");
		var getAsIntInvocations = optionalInvocationFinder.getInvocations(astNode, "getAsInt");
		var getAsLongInvocations = optionalInvocationFinder.getInvocations(astNode, "getAsLong");
		var getAsDoubleInvocations = optionalInvocationFinder.getInvocations(astNode, "getAsDouble");

		return  Stream.of(simpleGetInvocations, getAsIntInvocations, getAsLongInvocations, getAsDoubleInvocations)
				.flatMap(List::stream)
				.filter(this::isTheSecondRulesAntipattern)
				.collect(Collectors.toList());

	}

	private boolean isTheSecondRulesAntipattern(MethodInvocation methodInvocation) {

		return UtilityClass
				.getInvocatorName(methodInvocation)
				.map(invocatorName -> verifyInParents(methodInvocation, invocatorName))
				.orElse(false);
	}

	private boolean verifyInParents(MethodInvocation methodInvocation, String invocatorName) {
		var astNode = methodInvocation.getParent();
		for(; !(astNode instanceof MethodDeclaration);) {

			if(astNode instanceof IfStatement 
					&& containsIsPresentInvocationForTheVariable(((IfStatement) astNode).getExpression(), invocatorName)) {
				return false;
			}

			if(astNode instanceof WhileStatement 
					&& containsIsPresentInvocationForTheVariable(((WhileStatement) astNode).getExpression(), invocatorName)) {
				return false;
			}

			if(astNode instanceof ForStatement 
					&& ((ForStatement) astNode).getExpression() != null 
					&& containsIsPresentInvocationForTheVariable(((ForStatement) astNode).getExpression(), invocatorName)) {
				return false;
			}
			astNode = astNode.getParent();
		}
		return true;
	}

	private boolean containsIsPresentInvocationForTheVariable(Expression expression, String invocatorName) {
		return expression.toString()
				.indexOf(invocatorName + ".isPresent()") != -1;
	}
}
