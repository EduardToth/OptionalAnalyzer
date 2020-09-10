package compilationUnits.groups;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.MethodInvocation;
import org.eclipse.jdt.core.dom.PrefixExpression;

import optionalanalizer.metamodel.entity.MCompilationUnit;
import optionalanalizer.metamodel.entity.MInvocation;
import optionalanalizer.metamodel.entity.MPrefixExpression;
import optionalanalizer.metamodel.factory.Factory;
import ro.lrg.xcore.metametamodel.Group;
import ro.lrg.xcore.metametamodel.IRelationBuilder;
import ro.lrg.xcore.metametamodel.RelationBuilder;

@RelationBuilder
public class Rule26AntipatternBuilder implements IRelationBuilder<MPrefixExpression, MCompilationUnit>{


	@Override
	public Group<MPrefixExpression> buildGroup(MCompilationUnit arg0) {
		Group<MInvocation> isPresentInvocations = arg0.optionalInvocationsBuilder();
		Group<MPrefixExpression> group = new Group<>();
		List<MPrefixExpression> mPrefixExpressions = isPresentInvocations.getElements().stream()
				.map(this::getParentPrefixExpression)
				.filter(Optional::isPresent)
				.map(Optional::get)
				.filter(this::isOperatorNegation)
				.map(expr -> Factory.getInstance().createMPrefixExpression(expr))
				.collect(Collectors.toList());
		group.addAll(mPrefixExpressions);
		return group;
	}

	private Optional<PrefixExpression> getParentPrefixExpression(MInvocation mInvocation) {
		MethodInvocation methodInvocation = (MethodInvocation) mInvocation.getUnderlyingObject();
		ASTNode astNode = methodInvocation;

		while(astNode != null && !(astNode instanceof PrefixExpression)) {
			astNode = astNode.getParent();
		}

		return Optional.ofNullable((PrefixExpression)astNode);
	}

	private boolean isOperatorNegation(PrefixExpression prefixExpression) {
		String stringForm = prefixExpression.getOperator().toString();
		PrefixExpression.Operator operator = PrefixExpression.Operator.toOperator(stringForm);

		return operator == PrefixExpression.Operator.NOT;
	}

}