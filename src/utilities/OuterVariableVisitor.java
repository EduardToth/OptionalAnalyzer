package utilities;

import java.util.List;

import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.Assignment;
import org.eclipse.jdt.core.dom.PostfixExpression;
import org.eclipse.jdt.core.dom.PrefixExpression;

public class OuterVariableVisitor extends ASTVisitor {

	private List<String> variableNames;
	private boolean thereAreModificationsToOuterElements;
	
	public OuterVariableVisitor(List<String> variableNames) {
		super();
		this.variableNames = variableNames;
		this.thereAreModificationsToOuterElements = false;
	}

	@Override
	public boolean visit(Assignment assignment) {
		String leftHandSideVariable = assignment.getLeftHandSide().toString();
		if(!variableNames.contains(leftHandSideVariable)) {
			thereAreModificationsToOuterElements = true;
		}
		return super.visit(assignment);
	}
	
	@Override
	public boolean visit(PrefixExpression prefixExpression) {
		
		PrefixExpression.Operator operator = prefixExpression.getOperator();
		boolean thereIsAProblem = (operator == PrefixExpression.Operator.INCREMENT ||
				operator == PrefixExpression.Operator.DECREMENT) &&
		!variableNames.contains(prefixExpression.getOperand().toString());
		if(thereIsAProblem) {
			thereAreModificationsToOuterElements = true;
		}
		return super.visit(prefixExpression);
	}
	
	@Override
	public boolean visit(PostfixExpression postfixExpression) {
	PostfixExpression.Operator operator = postfixExpression.getOperator();
		boolean thereIsAProblem = (operator == PostfixExpression.Operator.INCREMENT ||
				operator == PostfixExpression.Operator.DECREMENT) &&
		!variableNames.contains(postfixExpression.getOperand().toString());
		if(thereIsAProblem) {
			thereAreModificationsToOuterElements = true;
		}
		return super.visit(postfixExpression);
	}
	
	public boolean thereAreModificationsToOuterElements() {
		return thereAreModificationsToOuterElements;
	}
}
