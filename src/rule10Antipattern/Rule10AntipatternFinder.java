package rule10Antipattern;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.IfStatement;
import org.eclipse.jdt.core.dom.MethodInvocation;
import org.eclipse.jdt.core.dom.Statement;
import org.javatuples.Pair;

import optionalanalizer.metamodel.entity.MRule10Atom;
import optionalanalizer.metamodel.factory.Factory;
import utilities.OptionalInvocationFinder;
import utilities.ToolBoxForIfStatementAnalysis;
import utilities.Unit;
import utilities.UtilityClass;

public class Rule10AntipatternFinder {
	public List<MRule10Atom> getMAtoms(ASTNode astNode) {

		return  getProblematicIfStatements(astNode).stream()
				.map(Rule10Antipattern::getInstance)
				.filter(Optional::isPresent)
				.map(Optional::get)
				.map(Factory.getInstance()::createMRule10Atom)
				.collect(Collectors.toList());

	}

	private List<IfStatement> getProblematicIfStatements(ASTNode astNode) {
		OptionalInvocationFinder optionalInvocationFinder = new OptionalInvocationFinder();
		List<MethodInvocation> invocations = optionalInvocationFinder.getInvocations(astNode);
		
		return getProblematicIfStatements(invocations);
	}

	private List<IfStatement> getProblematicIfStatements(List<MethodInvocation> invocations) {
		final Unit<String> invocatorName = new Unit<>(null);

		return invocations.stream()
				.peek(inv -> ToolBoxForIfStatementAnalysis.setInvocatorName(inv, invocatorName))
				.filter(el -> invocatorName.getValue0() != null)
				.filter(ToolBoxForIfStatementAnalysis::isSuperParentIfStatement)
				.map(ToolBoxForIfStatementAnalysis::getIfStatement)
				.filter(ifStatement -> isAntipattern(ifStatement, invocatorName.getValue0()))
				.collect(Collectors.toList());
	}

	private  boolean isAntipattern(IfStatement ifStatement, String invocatorName) {
		
		return Optional.of(Pair.with(ifStatement.getThenStatement(), ifStatement.getElseStatement()))
				.filter(pair -> pair.getValue0() != null && pair.getValue1() != null)
				.filter(this::bothOfThemContainReturnStatement)
				.map(pair -> isAntipattern(pair.getValue0(), pair.getValue1(), invocatorName))
				.orElse(false);
	}

	private boolean bothOfThemContainReturnStatement(Pair<Statement, Statement> statementPair) {
		return ToolBoxForIfStatementAnalysis.getReturnStatement(statementPair.getValue0()).isPresent()
				&& ToolBoxForIfStatementAnalysis.getReturnStatement(statementPair.getValue1()).isPresent();
	}

	private boolean isAntipattern(Statement statementForThen, Statement statementForElse, String invocatorName) {

		String typeName = "";
		try {
			typeName = ToolBoxForIfStatementAnalysis.getReturnStatement(statementForThen)
					.get()
					.getExpression()
					.resolveTypeBinding()
					.getQualifiedName();
		}catch(NullPointerException npe) {}
		
		return 	ToolBoxForIfStatementAnalysis.getCyclomaticComplexity(statementForThen) == 1
				&& ToolBoxForIfStatementAnalysis.getCyclomaticComplexity(statementForElse) == 1
				&& ToolBoxForIfStatementAnalysis.isStatementComposedByASimgleAction(statementForThen)
				&& ToolBoxForIfStatementAnalysis.isStatementComposedByASimgleAction(statementForElse) 
				&& UtilityClass.isTypeOptional(typeName);
	}
}
