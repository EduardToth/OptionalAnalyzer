package rule_7Antipattern;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.IMethodBinding;
import org.eclipse.jdt.core.dom.IfStatement;
import org.eclipse.jdt.core.dom.MethodInvocation;
import org.eclipse.jdt.core.dom.Statement;
import org.javatuples.Pair;

import optionalanalizer.metamodel.entity.MRule7sAntipattern;
import optionalanalizer.metamodel.factory.Factory;
import utilities.OptionalInvocationFinder;
import utilities.ToolBoxForIfStatementAnalysis;
import utilities.UtilityClass;

public class Rule7AntipatternFinder{


	public List<MRule7sAntipattern> getMAntipatterns(ASTNode astNode) {
		OptionalInvocationFinder optionalInvocationFinder = new OptionalInvocationFinder();
		List<MethodInvocation> isPresentInvocationGroup = optionalInvocationFinder.getInvocations(astNode);
		
		return collectRule7Antipatterns(isPresentInvocationGroup);
	}

	private List<MRule7sAntipattern> collectRule7Antipatterns(List<MethodInvocation> isPresentList) {

		return isPresentList.stream()
				.filter(ToolBoxForIfStatementAnalysis::isSuperParentIfStatement)
				.map(ToolBoxForIfStatementAnalysis::getIfStatement)
				.filter(this::isThereSameMethodInvokedInThenElseBlocks)
				.filter(this::isCyclomaticComplexityForBothOne)
				.filter(this::areStatementsComposedBySingleAction)
				.map(Rule7Antipattern::getInstance)
				.filter(Optional::isPresent)
				.map(Optional::get)
				.map(Factory.getInstance()::createMRule7sAntipattern)
				.collect(Collectors.toList());
	}
	
	private boolean areStatementsComposedBySingleAction(IfStatement ifStatement) {
		return ToolBoxForIfStatementAnalysis.isStatementComposedByASimgleAction(ifStatement.getThenStatement()) &&
				ToolBoxForIfStatementAnalysis.isStatementComposedByASimgleAction(ifStatement.getElseStatement());
	}

	private boolean isCyclomaticComplexityForBothOne(IfStatement ifStatement) {
		return Optional.of(Pair.with(ifStatement.getThenStatement(), ifStatement.getElseStatement()))
				.filter(pair -> pair.getValue0() != null && pair.getValue1() != null)
				.map(this::isCyclomaticComplexityForBothOne)
				.orElse(false);
	}
	
	private boolean isCyclomaticComplexityForBothOne(Pair<Statement,Statement> statementPair) {
		return ToolBoxForIfStatementAnalysis.getCyclomaticComplexity(statementPair.getValue0()) == 1 
				&& ToolBoxForIfStatementAnalysis.getCyclomaticComplexity(statementPair.getValue1()) == 1;
	}

	private boolean isThereSameMethodInvokedInThenElseBlocks(IfStatement ifStatement) {
		Statement thenStatement = ifStatement.getThenStatement();
		Statement elseStatement = ifStatement.getElseStatement();

		if(thenStatement == null || elseStatement == null) {
			return false;
		}

		final List<MethodInvocation> methodInvocationsForThenStatement = getMethodInvocationFromStatement(thenStatement);
		final List<MethodInvocation> methodInvocationsForElseStatement = getMethodInvocationFromStatement(elseStatement);

		return methodInvocationsForElseStatement.stream()
				.filter(inv -> doesListContainMethodInvocation(methodInvocationsForThenStatement, inv))
				.findAny()
				.isPresent();
	}

	private boolean doesListContainMethodInvocation(final List<MethodInvocation> methodInvocations, MethodInvocation methodInvocation) {
		return methodInvocations.stream()
				.anyMatch(inv -> areInvokedMethodsTheSame(inv, methodInvocation));
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