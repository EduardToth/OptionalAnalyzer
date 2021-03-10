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

import optionalanalizer.metamodel.entity.MRule2Atom;
import optionalanalizer.metamodel.factory.Factory;
import utilities.OptionalInvocationFinder;
import utilities.UtilityClass;

public class Rule2AntipatternFinder{

	public List<MRule2Atom> getMAtoms(ASTNode astNode) {
		return getAtoms(astNode)
				.stream()
				.map(Rule2Antipattern::getInstance)
				.filter(Optional::isPresent)
				.map(Optional::get)
				.map(Factory.getInstance()::createMRule2Atom)
				.collect(Collectors.toList());
	}

	private List<MethodInvocation> getAtoms(ASTNode astNode) {
		OptionalInvocationFinder optionalInvocationFinder = new OptionalInvocationFinder();
		List<MethodInvocation> simpleGetInvocations = optionalInvocationFinder.getInvocations(astNode, "get");
		List<MethodInvocation> getAsIntInvocations = optionalInvocationFinder.getInvocations(astNode, "getAsInt");
		List<MethodInvocation> getAsLongInvocations = optionalInvocationFinder.getInvocations(astNode, "getAsLong");
		List<MethodInvocation> getAsDoubleInvocations = optionalInvocationFinder.getInvocations(astNode, "getAsDouble");

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
		ASTNode astNode = methodInvocation.getParent();
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
