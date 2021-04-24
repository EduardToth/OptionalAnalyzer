package rule19Antipattern;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.BooleanLiteral;
import org.eclipse.jdt.core.dom.CharacterLiteral;
import org.eclipse.jdt.core.dom.Expression;
import org.eclipse.jdt.core.dom.InfixExpression;
import org.eclipse.jdt.core.dom.MethodInvocation;
import org.eclipse.jdt.core.dom.NullLiteral;
import org.eclipse.jdt.core.dom.NumberLiteral;
import org.eclipse.jdt.core.dom.StringLiteral;
import org.eclipse.jdt.core.dom.TypeLiteral;

import optionalanalyzer.metamodel.entity.MRule19sAntipattern;
import optionalanalyzer.metamodel.factory.Factory;
import utilities.OptionalInvocationFinder;

public class Rule19AntipatternFinder {

	public List<MRule19sAntipattern> getMAntipatterns(ASTNode astNode) {
		OptionalInvocationFinder optionalInvocationFinder = new OptionalInvocationFinder();
		List<MethodInvocation> ofGroup = optionalInvocationFinder.getInvocations(astNode, "of");
		List<MethodInvocation> ofNullableGroup = optionalInvocationFinder.getInvocations(astNode, "ofNullable");
		List<MethodInvocation> badInvocationsForOptionalOf = getBadInvocationsForOptionalOf(ofGroup);
		List<MethodInvocation> badInvocationsForOptionalOfNullable = getBadInvocationsForOptionalOfNullable(ofNullableGroup);

		return Stream.of(badInvocationsForOptionalOf, badInvocationsForOptionalOfNullable)
				.flatMap(Collection::stream)
				.map(Rule19Antipattern::getInstance)
				.flatMap(Optional::stream)
				.map(Factory.getInstance()::createMRule19sAntipattern)
				.collect(Collectors.toList());
	}

	private boolean isArgumentLiteral(MethodInvocation methodInvocation) {
		List<?> arguments = methodInvocation.arguments();
		boolean isArgumentLiteral = false;
		//Optional.of and ofNullable has only one argument
		if(arguments.size() == 1) {
			isArgumentLiteral =  arguments.stream()
					.findFirst()
					.filter(Expression.class::isInstance)
					.map(Expression.class::cast)
					.map(this::isLiteral)
					.orElse( false );
		} 

		return isArgumentLiteral;
	}

	private boolean isArgumentAnOperationOfLiterals(MethodInvocation methodInvocation) {
		List<?> arguments = methodInvocation.arguments();

		return arguments.stream()
				.findFirst()
				.filter(InfixExpression.class::isInstance)
				.map(InfixExpression.class::cast)
				.map(this::getOperands)
				.stream()
				.flatMap(List::stream)
				.anyMatch(Predicate.not(this::isLiteral));
	}

	private List<Expression> getOperands(InfixExpression infixExpression) {

		List<Expression> operands = new ArrayList<>();
		operands.add(infixExpression.getLeftOperand());
		operands.add(infixExpression.getRightOperand());

		List<Expression> extendedOperands = getExtendedOperands(infixExpression);
		operands.addAll(extendedOperands);

		return operands;
	}

	private List<Expression> getExtendedOperands(InfixExpression infixExpression) {
		if(infixExpression.hasExtendedOperands()) {
			List<?> extendedOperands = infixExpression.extendedOperands();

			return extendedOperands.stream()
					.filter(Expression.class::isInstance)
					.map(Expression.class::cast)
					.collect(Collectors.toList());

		}

		return Collections.emptyList();
	}

	private boolean isLiteral(Expression expression) {
		return expression instanceof BooleanLiteral ||
				expression instanceof CharacterLiteral ||
				expression instanceof NullLiteral ||
				expression instanceof NumberLiteral ||
				expression instanceof StringLiteral ||
				expression instanceof TypeLiteral;
	}

	private List<MethodInvocation> getBadInvocationsForOptionalOfNullable(List<MethodInvocation> ofNullableList) {

		Predicate<MethodInvocation> predicate1 = this::isArgumentLiteral;
		Predicate<MethodInvocation> predicate2 = this::isArgumentAnOperationOfLiterals;
		List<MethodInvocation> badInvocationsForOptionalOfNullable = ofNullableList.stream()
				.filter(predicate1.or(predicate2))
				.collect(Collectors.toList());

		return badInvocationsForOptionalOfNullable;
	}

	private List<MethodInvocation> getBadInvocationsForOptionalOf(List<MethodInvocation> ofList) {

		return ofList.stream()
				.filter(Predicate.not(this::isArgumentLiteral))
				.filter(Predicate.not(this::isArgumentAnOperationOfLiterals))
				.collect(Collectors.toList());
	}
}
