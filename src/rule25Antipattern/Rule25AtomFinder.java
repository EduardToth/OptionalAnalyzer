package rule25Antipattern;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.Expression;
import org.eclipse.jdt.core.dom.InfixExpression;
import org.eclipse.jdt.core.dom.InfixExpression.Operator;

import optionalanalizer.metamodel.entity.MRule25Atom;
import optionalanalizer.metamodel.factory.Factory;
import utilities.UtilityClass;

public class Rule25AtomFinder {
	public List<MRule25Atom> getMAtoms(ASTNode astNode) {
		final List<InfixExpression> infixExpressions = getAllInfixExpressions(astNode);

		return infixExpressions.stream()
				.filter(this::isFine)
				.map(Rule25Atom::getInstance)
				.filter( Optional::isPresent)
				.map(Optional::get)
				.map(Factory.getInstance()::createMRule25Atom)
				.collect(Collectors.toList());
	}

	private List<InfixExpression> getAllInfixExpressions(ASTNode astNode) {

		final List<InfixExpression> infixExpressions = new LinkedList<>();

		astNode.accept(new ASTVisitor() {

			@Override
			public boolean visit(InfixExpression infixExpression) {
				infixExpressions.add(infixExpression);
				return super.visit(infixExpression);
			}
		});

		return infixExpressions;
	}

	private boolean isFine(final InfixExpression infixExpression) {
		String leftHandSideOperandType = getTypeName(infixExpression.getLeftOperand());
		String rightHandSideOperandType = getTypeName(infixExpression.getRightOperand());
		Operator operator = infixExpression.getOperator();

		return UtilityClass.isTypeOptional(leftHandSideOperandType) 
				&& UtilityClass.isTypeOptional(rightHandSideOperandType)
				&& operator.equals(Operator.EQUALS);
	}

	private String getTypeName(Expression expression) {
		String typeName = "";

		try {
			typeName = expression.resolveTypeBinding().getQualifiedName();
		} catch(NullPointerException npe) {}

		return typeName;
	}
}
