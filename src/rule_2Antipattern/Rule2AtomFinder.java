package rule_2Antipattern;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.Expression;
import org.eclipse.jdt.core.dom.IfStatement;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.MethodInvocation;

import optionalanalizer.metamodel.entity.MRule2Atom;
import optionalanalizer.metamodel.factory.Factory;
import utilities.OptionalInvocationFinder;
import utilities.UtilityClass;

public class Rule2AtomFinder{

	public List<MRule2Atom> getMAtoms(ASTNode astNode) {
		return getAtoms(astNode)
				.stream()
				.map(Rule2Atom::getInstance)
				.filter(Optional::isPresent)
				.map(Optional::get)
				.map(el -> Factory.getInstance().createMRule2Atom(el))
				.collect(Collectors.toList());
	}

	private List<MethodInvocation> getAtoms(ASTNode astNode) {
		OptionalInvocationFinder optionalInvocationFinder = new OptionalInvocationFinder();
		List<MethodInvocation> invocations = optionalInvocationFinder.getInvocations(astNode, "get");
		return invocations.parallelStream()
				.filter(this::isTheSecondRulesAntipattern)
				.collect(Collectors.toList());

	}

	private boolean isTheSecondRulesAntipattern(MethodInvocation methodInvocation) {

		String invocatorName = UtilityClass
				.getInvocatorName(methodInvocation)
				.orElse(null);
		return verifyInParents(methodInvocation, invocatorName);
	}

	private boolean verifyInParents(MethodInvocation methodInvocation, String invocatorName) {
		ASTNode astNode = methodInvocation.getParent();
		for(; !(astNode instanceof MethodDeclaration);) {

			if(astNode instanceof IfStatement 
					&& containsIsPresentInvocationForTheVariable(((IfStatement) astNode).getExpression(), invocatorName)) {
				return false;
			}
			astNode = astNode.getParent();
		}
		return true;
	}

	private boolean containsIsPresentInvocationForTheVariable(Expression expression, String invocatorName) {
		return expression.toString().indexOf(invocatorName + ".isPresent()") != -1;
	}
}
