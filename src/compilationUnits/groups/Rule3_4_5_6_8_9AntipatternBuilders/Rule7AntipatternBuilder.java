package compilationUnits.groups.Rule3_4_5_6_8_9AntipatternBuilders;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.IMethodBinding;
import org.eclipse.jdt.core.dom.IfStatement;
import org.eclipse.jdt.core.dom.MethodInvocation;
import org.eclipse.jdt.core.dom.Statement;

import compilationUnits.groups.Rule3_4_5_6AntipatternBuilders.baseComponents.AntipatternFinderInIfStatements;
import optionalanalizer.metamodel.entity.MCompilationUnit;
import optionalanalizer.metamodel.entity.MIfStatement;
import optionalanalizer.metamodel.entity.MInvocation;
import ro.lrg.xcore.metametamodel.Group;
import ro.lrg.xcore.metametamodel.IRelationBuilder;
import ro.lrg.xcore.metametamodel.RelationBuilder;
import utilities.UtilityClass;

@RelationBuilder
public class Rule7AntipatternBuilder extends AntipatternFinderInIfStatements implements IRelationBuilder<MIfStatement, MCompilationUnit>{

	private MethodInvocation firstMethodInvocationForThen = null;
	private MethodInvocation firstMethodInvocationForElse = null;
	@Override
	public Group<MIfStatement> buildGroup(MCompilationUnit arg0) {
		Group<MInvocation> isPresentInvocationGroup = arg0.optionalInvocationsBuilder();
		return collectRule7Antipatterns(isPresentInvocationGroup);
	}

	private Group<MIfStatement> collectRule7Antipatterns(Group<MInvocation> isPresentGroup) {

		Group<MIfStatement> mIfStatementGroup = new Group<>();

		List<MIfStatement> mIfStatements = isPresentGroup.getElements()
				.stream()
				.map(this::convertToMethodInvocation)
				.filter(this::isParentIfStatement)
				.map(inv -> (IfStatement)inv.getParent())
				.filter(ifStm -> isThereSameMethodInvokedInThenElseBlocks(ifStm))
				.filter(ifStm -> isContextTheSame(ifStm))
				.map(this::convertToMIfStatement)
				.collect(Collectors.toList());

		mIfStatementGroup.addAll(mIfStatements);
		return mIfStatementGroup;
	}

	private void setMethodInvocationsIfNotSet(MethodInvocation methodInvocationForThen, MethodInvocation methodInvocationForElse) {
		if(this.firstMethodInvocationForThen == null) {
			this.firstMethodInvocationForThen = methodInvocationForThen;
			this.firstMethodInvocationForElse = methodInvocationForElse;
		}
	}

	private boolean isContextTheSame(IfStatement ifStatement) {
		if(this.firstMethodInvocationForThen == null) {
			return false;
		}
		Statement thenStatement = ifStatement.getThenStatement();
		Statement elseStatement = ifStatement.getElseStatement();

		String contextWithoutInvocationForThen = takeOutInvocation(thenStatement.toString(), firstMethodInvocationForThen.toString());
		String contextWithoutInvocationForElse = takeOutInvocation(elseStatement.toString(), firstMethodInvocationForElse.toString());

		System.out.println(contextWithoutInvocationForElse);
		System.out.println();
		System.out.println(contextWithoutInvocationForThen);
		return contextWithoutInvocationForElse.equals(contextWithoutInvocationForThen);
	}

	private String takeOutInvocation(String context, String invocation) {
		int startIndex = context.indexOf(invocation);
		int endIndex = startIndex + invocation.length();

		return context.substring(0, startIndex) + context.substring(endIndex);
	}

	private boolean isThereSameMethodInvokedInThenElseBlocks(IfStatement ifStatement) {
		Statement thenStatement = ifStatement.getThenStatement();
		Statement elseStatement = ifStatement.getElseStatement();

		final List<MethodInvocation> methodInvocationsForTheStatement = getMethodInvocationFromStatement(thenStatement);
		final List<MethodInvocation> methodInvocationsForElseStatement = getMethodInvocationFromStatement(elseStatement);

		return methodInvocationsForElseStatement.stream()
				.filter(inv -> doesListContainMethodInvocation(methodInvocationsForTheStatement, inv))
				.findFirst()
				.isPresent();
	}

	private boolean doesListContainMethodInvocation(final List<MethodInvocation> methodInvocations, MethodInvocation methodInvocation) {
		return methodInvocations.stream()
				.filter(inv -> areInvokedMethodsTheSame(inv, methodInvocation))
				.peek(inv -> setMethodInvocationsIfNotSet(inv, methodInvocation))
				.findAny()
				.isPresent();
	}

	private List<MethodInvocation> getMethodInvocationFromStatement(Statement statement) {

		final List<MethodInvocation> methodInvocations = new LinkedList<>();

		statement.accept(new ASTVisitor() {

			@Override
			public boolean visit(MethodInvocation methodInvocation) {
				methodInvocations.add(methodInvocation);
				return super.visit(methodInvocation);
			}
		});
		return methodInvocations;
	}

	private boolean areInvokedMethodsTheSame(MethodInvocation methodInvocation1, MethodInvocation methodInvocation2) {
		return areThereSameBindings(methodInvocation1, methodInvocation2) &&
				areThereSameInvocators(methodInvocation1, methodInvocation2);
	}

	private boolean areThereSameInvocators(MethodInvocation methodInvocation1, MethodInvocation methodInvocation2) {
		Optional<String> invocatorName1 = UtilityClass.getInvocatorName(methodInvocation1);
		Optional<String> invocatorName2 = UtilityClass.getInvocatorName(methodInvocation2);

		return invocatorName1.equals(invocatorName2);
	}

	private boolean areThereSameBindings(MethodInvocation methodInvocation1, MethodInvocation methodInvocation2) {
		IMethodBinding bindings1 = methodInvocation1.resolveMethodBinding();
		IMethodBinding bindings2 = methodInvocation2.resolveMethodBinding();

		return bindings1.equals(bindings2);
	}
}