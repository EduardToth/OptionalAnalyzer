package compilationUnits.groups;

import java.util.LinkedList;
import java.util.List;

import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.Assignment;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.InfixExpression;
import org.eclipse.jdt.core.dom.InfixExpression.Operator;
import org.eclipse.jdt.core.dom.VariableDeclarationFragment;

import optionalanalizer.metamodel.entity.MAssignment;
import optionalanalizer.metamodel.entity.MCompilationUnit;
import optionalanalizer.metamodel.entity.MInfixExpression;
import optionalanalizer.metamodel.factory.Factory;
import ro.lrg.xcore.metametamodel.Group;
import ro.lrg.xcore.metametamodel.IRelationBuilder;
import ro.lrg.xcore.metametamodel.RelationBuilder;
import utilities.UtilityClass;

@RelationBuilder
public class Rule25AntipatternBuilder implements IRelationBuilder<MInfixExpression, MCompilationUnit>{

	@Override
	public Group<MInfixExpression> buildGroup(MCompilationUnit arg0) {
		
		ICompilationUnit iCompilationUnit = (ICompilationUnit)arg0.getUnderlyingObject();
		CompilationUnit compilationUnit = UtilityClass.parse(iCompilationUnit);
		final List<InfixExpression> infixExpressions = getAllInfixExpressions(compilationUnit);
		
		Group<MInfixExpression> group = new Group<>();
		infixExpressions.stream()
		.filter(this::isFine)
		.map(expr -> Factory.getInstance().createMInfixExpression(expr))
		.forEach(mInfixExpression -> group.add(mInfixExpression));
		
		return group;
	}
	
	private List<InfixExpression> getAllInfixExpressions(CompilationUnit compilationUnit) {
		
		final List<InfixExpression> infixExpressions = new LinkedList<>();
		
		compilationUnit.accept(new ASTVisitor() {
			
			@Override
			public boolean visit(InfixExpression infixExpression) {
				infixExpressions.add(infixExpression);
				return super.visit(infixExpression);
			}
		});
		
		return infixExpressions;
	}
	
	private boolean isFine(final InfixExpression infixExpression) {
		String leftHandSideOperandType = infixExpression.getLeftOperand().resolveTypeBinding().getQualifiedName();
		String rightHandSideOperandType = infixExpression.getRightOperand().resolveTypeBinding().getQualifiedName();
		Operator operator = infixExpression.getOperator();
	
		return UtilityClass.isTypeOptional(leftHandSideOperandType) 
				&& UtilityClass.isTypeOptional(rightHandSideOperandType)
				&& operator.equals(Operator.EQUALS);
	}
}