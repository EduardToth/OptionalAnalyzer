package rule_4Antipattern;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.IfStatement;
import org.eclipse.jdt.core.dom.MethodInvocation;
import org.eclipse.jdt.core.dom.ReturnStatement;
import org.eclipse.jdt.core.dom.Statement;
import org.javatuples.Pair;

import optionalanalyzer.metamodel.entity.MRule4sAntipattern;
import optionalanalyzer.metamodel.factory.Factory;
import utilities.OptionalInvocationFinder;
import utilities.ToolBoxForIfStatementAnalysis;
import utilities.UtilityClass;

public class Rule4AntipatternFinder{
	public List<MRule4sAntipattern> getMAntipatterns(ASTNode astNode) {
		var ifStatementMAntipatterns = getAntipatterns(astNode)
				.stream()
				.map(Rule4Antipattern::getInstance)
				.flatMap(Optional::stream)
				.map(Factory.getInstance()::createMRule4sAntipattern)
				.collect(Collectors.toList());

		var returnStatementMAntipatterns = getProblematicReturnStatements(astNode)
				.stream()
				.map(Rule4Antipattern::getInstance)
				.flatMap(Optional::stream)
				.map(Factory.getInstance()::createMRule4sAntipattern)
				.collect(Collectors.toList());

		return Stream.of(ifStatementMAntipatterns, returnStatementMAntipatterns)
				.flatMap(List::stream)
				.collect(Collectors.toList());

	}

	private List<IfStatement> getAntipatterns(ASTNode astNode) {
		var optionalInvocationFinder = new OptionalInvocationFinder();
		var invocations = optionalInvocationFinder.getInvocations(astNode);
		
		return collectAntipatterns(invocations);
	}

	private List<ReturnStatement> getProblematicReturnStatements(ASTNode astNode) {
		var optionalInvocationFinder = new OptionalInvocationFinder();
		var orElseInvocations = optionalInvocationFinder.getInvocations(astNode, "orElse");

		return orElseInvocations.stream()
				.filter(methodInvocation -> methodInvocation.getParent() instanceof ReturnStatement)
				.filter(methodInvocation -> isArgumentInvocation(methodInvocation.arguments()))
				.map(methodInvocation -> (ReturnStatement)methodInvocation.getParent())
				.collect(Collectors.toList());
	}

	private boolean isArgumentInvocation(List<?> arguments) {
		final String regex = ".*\\(.*\\).*";

		return arguments.size() == 1 && arguments.get( 0 )
				.toString()
				.matches(regex);
	}

	private Optional<IfStatement> getParentIfStatementIfProblematic(MethodInvocation methodInvocation) {

		if(ToolBoxForIfStatementAnalysis.isSuperParentIfStatement(methodInvocation)) {
			var ifStatement = ToolBoxForIfStatementAnalysis.getIfStatement(methodInvocation);
			var invocatorName = UtilityClass.getInvocatorName(methodInvocation);
			return invocatorName
					.filter(invName -> isAntipattern(ifStatement, invName))
					.map(invName -> ifStatement);
		}
		return Optional.empty();
	}

	private List<IfStatement> collectAntipatterns(List<MethodInvocation> invocations) {

		return invocations.stream()
				.map(this::getParentIfStatementIfProblematic)
				.flatMap(Optional::stream)
				.collect(Collectors.toList());
	}

	private Optional<Pair<ReturnStatement, ReturnStatement>> getReturnStatements(Pair<Statement, Statement> statements) {

		var thenStatement = statements.getValue0();
		var elseStatement = statements.getValue1();
		var returnStatementForThen = ToolBoxForIfStatementAnalysis.getReturnStatement(thenStatement);
		var returnStatementForElse = ToolBoxForIfStatementAnalysis.getReturnStatement(elseStatement);

		return returnStatementForThen.flatMap(returnStmForThen 
				-> returnStatementForElse.map(returnStmForElse 
						-> Pair.with(returnStmForThen, returnStmForElse)));

	}

	private  boolean isAntipattern(IfStatement ifStatement, String invocatorName) {
		Optional<Statement> thenStatement = Optional.ofNullable(ifStatement.getThenStatement());
		Optional<Statement> elseStatement = Optional.ofNullable(ifStatement.getElseStatement());

		return thenStatement.flatMap(
				thenStm -> elseStatement.map(elseStm 
						-> Pair.with(thenStm, elseStm))
				)
				.filter(this::isCyclomaticComplexityForBothOne)
				.filter(this::areStatementsComposedByASingleAction)
				.flatMap(this::getReturnStatements)
				.map(returnStatementPair -> isAntipattern(returnStatementPair, invocatorName))
				.orElse(false);
	}

	private boolean areStatementsComposedByASingleAction(Pair<Statement, Statement> statementPair) {
		var thenStatement = statementPair.getValue0();
		var elseStatement = statementPair.getValue1();

		return ToolBoxForIfStatementAnalysis.isStatementComposedByASimgleAction(thenStatement)
				&& ToolBoxForIfStatementAnalysis.isStatementComposedByASimgleAction(elseStatement);
	}

	private boolean isCyclomaticComplexityForBothOne(Pair<Statement, Statement> statementPair) {
		var thenStatement = statementPair.getValue0();
		var elseStatement = statementPair.getValue1();
		return ToolBoxForIfStatementAnalysis.getCyclomaticComplexity(thenStatement) == 1 
				&& ToolBoxForIfStatementAnalysis.getCyclomaticComplexity(elseStatement) == 1;
	}

	private boolean isAntipattern(Pair<ReturnStatement, ReturnStatement> returnStatementPair, String invocatorName) {
		var returnStatementForThen = returnStatementPair.getValue0();
		var returnStatementForElse = returnStatementPair.getValue1();
		
		return ToolBoxForIfStatementAnalysis.containsGetFromOptional(returnStatementForThen, invocatorName)
				&& ToolBoxForIfStatementAnalysis.containsMethodInvocation(returnStatementForElse)
				|| ToolBoxForIfStatementAnalysis.containsGetFromOptional(returnStatementForElse, invocatorName)
				&& ToolBoxForIfStatementAnalysis.containsMethodInvocation(returnStatementForThen);
	}
}
