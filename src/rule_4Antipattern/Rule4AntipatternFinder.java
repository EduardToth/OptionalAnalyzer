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

import optionalanalizer.metamodel.entity.MRule4Atom;
import optionalanalizer.metamodel.factory.Factory;
import utilities.OptionalInvocationFinder;
import utilities.ToolBoxForIfStatementAnalysis;
import utilities.Unit;

public class Rule4AntipatternFinder{
	public List<MRule4Atom> getMAtoms(ASTNode astNode) {
		List<MRule4Atom> ifStatementMAtoms = getAtoms(astNode)
				.stream()
				.map(Rule4Antipattern::getInstance)
				.filter(Optional::isPresent)
				.map(Optional::get)
				.map(Factory.getInstance()::createMRule4Atom)
				.collect(Collectors.toList());

		List<MRule4Atom> returnStatementMAtoms = getProblematicReturnStatements(astNode)
				.stream()
				.map(Rule4Antipattern::getInstance)
				.filter(Optional::isPresent)
				.map(Optional::get)
				.map(Factory.getInstance()::createMRule4Atom)
				.collect(Collectors.toList());
		
		return Stream.of(ifStatementMAtoms, returnStatementMAtoms)
				.flatMap(List::stream)
				.collect(Collectors.toList());
		
	}

	private List<IfStatement> getAtoms(ASTNode astNode) {
		OptionalInvocationFinder optionalInvocationFinder = new OptionalInvocationFinder();
		getProblematicReturnStatements(astNode);
		List<MethodInvocation> invocations = optionalInvocationFinder.getInvocations(astNode);
		return collectAntipatterns(invocations);
	}

	private List<ReturnStatement> getProblematicReturnStatements(ASTNode astNode) {
		OptionalInvocationFinder optionalInvocationFinder = new OptionalInvocationFinder();
		List<MethodInvocation> orElseInvocations = optionalInvocationFinder.getInvocations(astNode, "orElse");

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

	private List<IfStatement> collectAntipatterns(List<MethodInvocation> invocations) {
		final Unit<String> invocatorName = new Unit<>(null);

		return invocations.stream()
				.peek(inv -> ToolBoxForIfStatementAnalysis.setInvocatorName(inv, invocatorName))
				.filter(el -> invocatorName.getValue0() != null)
				.filter(ToolBoxForIfStatementAnalysis::isSuperParentIfStatement)
				.map(ToolBoxForIfStatementAnalysis::getIfStatement)
				.filter(ifStatement -> isAntipattern(ifStatement, invocatorName.getValue0()))
				.collect(Collectors.toList());
	}

	private Optional<Pair<ReturnStatement, ReturnStatement>> getReturnStatements(Pair<Statement, Statement> statements) {

		Statement thenStatement = statements.getValue0();
		Statement elseStatement = statements.getValue1();
		Optional<ReturnStatement> returnStatementForThen = ToolBoxForIfStatementAnalysis.getReturnStatement(thenStatement);
		Optional<ReturnStatement> returnStatementForElse = ToolBoxForIfStatementAnalysis.getReturnStatement(elseStatement);

		return returnStatementForThen.flatMap(
					returnStmForThen -> returnStatementForElse.map(returnStmForElse -> Pair.with(returnStmForThen, returnStmForElse))
				);

	}


	private  boolean isAntipattern(IfStatement ifStatement, String invocatorName) {
		Optional<Statement> thenStatement = Optional.ofNullable(ifStatement.getThenStatement());
		Optional<Statement> elseStatement = Optional.ofNullable(ifStatement.getElseStatement());

		return thenStatement.flatMap(
					thenStm -> elseStatement.map(elseStm -> Pair.with(thenStm, elseStm))
				)
				.filter(this::isCyclomaticComplexityForBothOne)
				.filter(this::areStatementsComposedByASingleAction)
				.flatMap(this::getReturnStatements)
				.map(returnStatementPair -> isAntipattern(returnStatementPair, invocatorName))
				.orElse(false);
	}

	private boolean areStatementsComposedByASingleAction(Pair<Statement, Statement> statementPair) {
		Statement thenStatement = statementPair.getValue0();
		Statement elseStatement = statementPair.getValue1();

		return ToolBoxForIfStatementAnalysis.isStatementComposedByASimgleAction(thenStatement)
				&& ToolBoxForIfStatementAnalysis.isStatementComposedByASimgleAction(elseStatement);
	}

	private boolean isCyclomaticComplexityForBothOne(Pair<Statement, Statement> statementPair) {
		Statement thenStatement = statementPair.getValue0();
		Statement elseStatement = statementPair.getValue1();
		return ToolBoxForIfStatementAnalysis.getCyclomaticComplexity(thenStatement) == 1 
				&& ToolBoxForIfStatementAnalysis.getCyclomaticComplexity(elseStatement) == 1;
	}

	private boolean isAntipattern(Pair<ReturnStatement, ReturnStatement> returnStatementPair, String invocatorName) {
		ReturnStatement returnStatementForThen = returnStatementPair.getValue0();
		ReturnStatement returnStatementForElse = returnStatementPair.getValue1();
		return ToolBoxForIfStatementAnalysis.containsGetFromOptional(returnStatementForThen, invocatorName)
				&& ToolBoxForIfStatementAnalysis.containsMethodInvocation(returnStatementForElse)
				|| ToolBoxForIfStatementAnalysis.containsGetFromOptional(returnStatementForElse, invocatorName)
				&& ToolBoxForIfStatementAnalysis.containsMethodInvocation(returnStatementForThen);
	}
}
