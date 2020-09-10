package compilationUnits.groups;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.eclipse.jdt.core.dom.BooleanLiteral;
import org.eclipse.jdt.core.dom.CharacterLiteral;
import org.eclipse.jdt.core.dom.Expression;
import org.eclipse.jdt.core.dom.InfixExpression;
import org.eclipse.jdt.core.dom.MethodInvocation;
import org.eclipse.jdt.core.dom.NullLiteral;
import org.eclipse.jdt.core.dom.NumberLiteral;
import org.eclipse.jdt.core.dom.StringLiteral;
import org.eclipse.jdt.core.dom.TypeLiteral;

import optionalanalizer.metamodel.entity.MCompilationUnit;
import optionalanalizer.metamodel.entity.MInvocation;
import optionalanalizer.metamodel.factory.Factory;
import ro.lrg.xcore.metametamodel.Group;
import ro.lrg.xcore.metametamodel.IRelationBuilder;
import ro.lrg.xcore.metametamodel.RelationBuilder;


@RelationBuilder
public class Rule19AntipatternBuilder  implements IRelationBuilder<MInvocation, MCompilationUnit>{

	@Override
	public Group<MInvocation> buildGroup(MCompilationUnit arg0) {
		OptionalInvocationsBuilder optionalInvocationsGroup = new OptionalInvocationsBuilder();
		Group<MInvocation> ofGroup = optionalInvocationsGroup.buildGroup(arg0, "of");
		Group<MInvocation> ofNullableGroup = optionalInvocationsGroup.buildGroup(arg0, "ofNullable");
		Group<MInvocation> mInvocationGroup = new Group<MInvocation>();

		List<MInvocation> badInvocationsForOptionalOf = getBadInvocationsForOptionalOf(ofGroup);
		List<MInvocation> badInvocationsForOptionalOfNullable = getBadInvocationsForOptionalOfNullable(ofNullableGroup);
		mInvocationGroup.addAll(badInvocationsForOptionalOfNullable);
		mInvocationGroup.addAll(badInvocationsForOptionalOf);

		return mInvocationGroup;
	}

	private boolean isArgumentLiteral(MethodInvocation methodInvocation) {
		List<?> arguments = methodInvocation.arguments();
		//Optional.of and ofNullable has only one argument
		if(arguments.size() != 1) {
			return false;
		}

		Object argument = arguments.get(0);

		return isLiteral((Expression)argument);
	}

	private boolean isArgumentAnOperationOfLiterals(MethodInvocation methodInvocation) {

		List<?> arguments = methodInvocation.arguments();

		if(arguments.size() != 1) {
			return false;
		}

		Object argument = arguments.get( 0 );

		boolean isArgumentAnOperantionOfLiterals = false;
		if(argument instanceof InfixExpression) {
			InfixExpression infixExpression = (InfixExpression)argument;
			List<Expression> operands = getOperands(infixExpression);
			isArgumentAnOperantionOfLiterals = !operands.stream()
					.filter(expr -> !isLiteral(expr))
					.findAny()
					.isPresent();
		}

		return isArgumentAnOperantionOfLiterals;
	}

	private List<Expression> getOperands(InfixExpression infixExpression) {

		List<Expression> operands = new ArrayList<>();
		operands.add(infixExpression.getLeftOperand());
		operands.add(infixExpression.getRightOperand());

		if(infixExpression.hasExtendedOperands()) {
			List<?> extendedOperands = infixExpression.extendedOperands();

			for(Object obj : extendedOperands) {
				if(obj instanceof Expression) {
					operands.add((Expression)obj);
				}
			}
		}
		return operands;
	}

	private boolean isLiteral(Expression expression) {

		return expression instanceof BooleanLiteral ||
				expression instanceof CharacterLiteral ||
				expression instanceof NullLiteral ||
				expression instanceof NumberLiteral ||
				expression instanceof StringLiteral ||
				expression instanceof TypeLiteral;
	}

	private List<MInvocation> getBadInvocationsForOptionalOfNullable(Group<MInvocation> ofNullableGroup) {
		List<MInvocation> badInvocationsForOptionalOfNullable = ofNullableGroup.getElements().stream()
				.map(mInv -> (MethodInvocation)mInv.getUnderlyingObject())
				.filter(methodInvocation -> isArgumentAnOperationOfLiterals(methodInvocation) || isArgumentLiteral(methodInvocation))
				.map(methodInvocation -> Factory.getInstance().createMInvocation(methodInvocation))
				.collect(Collectors.toList());
		return badInvocationsForOptionalOfNullable;
	}

	private List<MInvocation> getBadInvocationsForOptionalOf(Group<MInvocation> ofGroup) {
		List<MInvocation> badInvocationsForOptionalOf = ofGroup.getElements().stream()
				.filter(mInv -> mInv.getUnderlyingObject() instanceof MethodInvocation)
				.map(mInv -> (MethodInvocation)mInv.getUnderlyingObject())
				.filter(methodInvocation -> !isArgumentLiteral(methodInvocation))
				.filter(methodInvocation -> !isArgumentAnOperationOfLiterals(methodInvocation))
				.map(methodInvocation -> Factory.getInstance().createMInvocation(methodInvocation))
				.collect(Collectors.toList());
		return badInvocationsForOptionalOf;
	}
}


