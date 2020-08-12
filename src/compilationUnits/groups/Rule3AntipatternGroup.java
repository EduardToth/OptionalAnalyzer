package compilationUnits.groups;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.IfStatement;
import org.eclipse.jdt.core.dom.MethodInvocation;
import org.eclipse.jdt.core.dom.ReturnStatement;

import optionalanalizer.metamodel.entity.MCompilationUnit;
import optionalanalizer.metamodel.entity.MIfStatement;
import optionalanalizer.metamodel.entity.MInvocation;
import optionalanalizer.metamodel.factory.Factory;
import ro.lrg.xcore.metametamodel.Group;
import ro.lrg.xcore.metametamodel.IRelationBuilder;
import ro.lrg.xcore.metametamodel.RelationBuilder;
import utilities.UtilityClass;

@RelationBuilder
public class Rule3AntipatternGroup implements IRelationBuilder<MIfStatement, MCompilationUnit>{


	@Override
	public Group<MIfStatement> buildGroup(MCompilationUnit arg0) {
		Group<MIfStatement> group = new Group<>();
		OptionalIsPresentInvocationBuilder isPresentInvocationBuilder = 
				new OptionalIsPresentInvocationBuilder();
		Group<MInvocation> isPresentGroup = isPresentInvocationBuilder.buildGroup(arg0);

		collectAntipatterns(group, isPresentGroup);

		return group;
	}

	private void collectAntipatterns(Group<MIfStatement> group, Group<MInvocation> isPresentGroup) {
		for(MInvocation invocation : isPresentGroup.getElements()) {
			MethodInvocation methodInvocation = ((MethodInvocation)invocation.getUnderlyingObject());
			String invocatorName = "";
			if(methodInvocation.getExpression() != null) {
				invocatorName = methodInvocation.getExpression().toString();
			}
			if(isTheSecondsRuleAntipattern(invocation, invocatorName)) {
				IfStatement ifStatement = (IfStatement) methodInvocation.getParent();
				group.add(Factory.getInstance().createMIfStatement(ifStatement));
			}
		}
	}

	private boolean isTheSecondsRuleAntipattern(MInvocation mInvocation, String invocatorName) {

		boolean itsFine = false;
		MethodInvocation invocation = (MethodInvocation)mInvocation.getUnderlyingObject();
		if(invocation.getParent() instanceof IfStatement) {
			IfStatement ifStatement = (IfStatement)invocation.getParent();
			itsFine = analyzeIfBody(invocatorName, ifStatement);
		}

		return itsFine;
	}

	private boolean analyzeIfBody(String invocatorName,  IfStatement ifStatement) {
		final List<Boolean> itsFineList = new ArrayList<>();
		itsFineList.add( false );
		ifStatement.accept(new ASTVisitor() {
			@Override
			public boolean visit(ReturnStatement statement) {

				if(analyzeReturnStatement(invocatorName, statement)) {
					itsFineList.set(0, true);
				}
				return super.visit(statement);
			}

		});
		return itsFineList.size() != 0 && itsFineList.get( 0 ) == true;
	}

	private boolean analyzeReturnStatement(String invocatorName, ReturnStatement statement) {
		final List<Boolean> itsFineList = new ArrayList<>();
		itsFineList.add( false );
		statement.accept(new ASTVisitor() {
			@Override
			public boolean visit(MethodInvocation invocation) {
				String invocatorNameInReturnStatement = invocation.getExpression().toString();
				boolean isTheSameInvocator = invocatorNameInReturnStatement.equals(invocatorName);
				String invokedMethodName = invocation.getName().toString();
				boolean isInvocatorOptional = invocation.getExpression() != null &&
						UtilityClass.isInvocatorOfOptionalType(invocation.getExpression()
								.resolveTypeBinding().getQualifiedName());
				final boolean itsFine = isTheSameInvocator && isInvocatorOptional && invokedMethodName.equals("get");
				itsFineList.set(0, itsFine);

				return super.visit(invocation);
			}
		});

		return itsFineList.get( 0 );
	}

}
