package rule26Antipattern;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.MethodInvocation;
import org.eclipse.jdt.core.dom.PrefixExpression;

import optionalanalyzer.metamodel.entity.MRule26sAntipattern;
import optionalanalyzer.metamodel.factory.Factory;
import utilities.OptionalInvocationFinder;

public class Rule26AntipatternFinder {
	
	public List<MRule26sAntipattern> getMAntipatterns(ASTNode astNode) {
		var optionalInvocationFinder = new OptionalInvocationFinder();
		var isPresentInvocations = optionalInvocationFinder.getInvocations(astNode);
		
		return isPresentInvocations.stream()
				.map(this::getParentPrefixExpression)
				.flatMap(Optional::stream)
				.filter(this::isOperatorNegation)
				.map(Rule26Antipattern::getInstance)
				.flatMap(Optional::stream)
				.map(Factory.getInstance()::createMRule26sAntipattern)
				.collect(Collectors.toList());
	} 

	private Optional<PrefixExpression> getParentPrefixExpression(MethodInvocation methodInvocation) {
		ASTNode astNode = methodInvocation;

		while(astNode != null && !(astNode instanceof PrefixExpression)) {
			astNode = astNode.getParent();
		}

		return Optional.ofNullable((PrefixExpression)astNode);
	}

	private boolean isOperatorNegation(PrefixExpression prefixExpression) {
		var stringForm = prefixExpression
				.getOperator()
				.toString();
		
		var operator = PrefixExpression.Operator.toOperator(stringForm);

		return operator == PrefixExpression.Operator.NOT;
	}

}
