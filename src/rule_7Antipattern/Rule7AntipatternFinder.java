package rule_7Antipattern;

import java.util.ArrayList;
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

import optionalanalyzer.metamodel.entity.MRule7sAntipattern;
import optionalanalyzer.metamodel.factory.Factory;
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
				.filter(this::isSimpleIfElseStatement)
				.map(Rule7Antipattern::getInstance)
				.flatMap(Optional::stream)
				.map(Factory.getInstance()::createMRule7sAntipattern)
				.collect(Collectors.toList());
	}

	private boolean areStatementsComposedBySingleAction(IfStatement ifStatement) {
		return ToolBoxForIfStatementAnalysis.isStatementComposedByASimgleAction(ifStatement.getThenStatement()) &&
				ToolBoxForIfStatementAnalysis.isStatementComposedByASimgleAction(ifStatement.getElseStatement());
	}

	private boolean isCyclomaticComplexityForBothOne(IfStatement ifStatement) {
		Optional<Statement> thenStatement = Optional.ofNullable(ifStatement.getThenStatement());
		Optional<Statement> elseStatement = Optional.ofNullable(ifStatement.getElseStatement());

		return thenStatement.flatMap(
					themStm -> elseStatement.map(elseStm -> isCyclomaticComplexityForBothOne(themStm, elseStm))
				).orElse(false);

	}

	private boolean isCyclomaticComplexityForBothOne(Statement thenStatement, Statement elseStatement) {
		return ToolBoxForIfStatementAnalysis.getCyclomaticComplexity(thenStatement) == 1 
				&& ToolBoxForIfStatementAnalysis.getCyclomaticComplexity(thenStatement) == 1;
	}

	private boolean isThereSameMethodInvokedInThenElseBlocks(IfStatement ifStatement) {
		Statement thenStatement = ifStatement.getThenStatement();
		Statement elseStatement = ifStatement.getElseStatement();

		if(thenStatement == null || elseStatement == null) {
			return false;
		}

		Optional<MethodInvocation> methodInvocationForThenStatement = getMethodInvocationFromStatement(thenStatement);
		Optional<MethodInvocation> methodInvocationForElseStatement = getMethodInvocationFromStatement(elseStatement);

		return methodInvocationForThenStatement.flatMap(
				methodInvocationInThenStatement -> methodInvocationForElseStatement.map(
						methodInvocationInElseStatement -> areInvokedMethodsTheSame(methodInvocationInThenStatement, methodInvocationInElseStatement)
						)
				).orElse( false );
	}

	private Optional<MethodInvocation> getMethodInvocationFromStatement(Statement statement) {
		final List<MethodInvocation> methodInvocations = new LinkedList<>();

		statement.accept(new ASTVisitor() {

			@Override
			public boolean visit(MethodInvocation methodInvocation) {
				methodInvocations.add(methodInvocation);
				return super.visit(methodInvocation);
			}
		});
		return methodInvocations.stream().findAny();
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

	private boolean isSimpleIfElseStatement(IfStatement ifStatement) {
		Optional<Statement> elseStatement = Optional.ofNullable(ifStatement.getElseStatement());

		return elseStatement.map(this::getIfStatements)
				.map(List::size)
				.map(size -> size == 0)
				.orElse( true );
	}

	private List<IfStatement> getIfStatements(Statement statement) {
		List<IfStatement> ifStatements = new ArrayList<>();

		statement.accept(new ASTVisitor() {
			@Override
			public boolean visit(IfStatement ifStatement) {
				ifStatements.add(ifStatement);
				return super.visit(ifStatement);
			}
		});

		return ifStatements;
	}
}