package rule_8Antipattern;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.IfStatement;
import org.eclipse.jdt.core.dom.MethodInvocation;
import org.eclipse.jdt.core.dom.Statement;
import org.javatuples.Pair;

import optionalanalizer.metamodel.entity.MRule8Atom;
import optionalanalizer.metamodel.factory.Factory;
import utilities.OptionalInvocationFinder;
import utilities.ToolBoxForIfStatementAnalysis;
import utilities.Unit;

public class Rule8AtomFinder{

	public List<MRule8Atom> getMAtoms(ASTNode astNode) {

		return getAtoms(astNode).stream()
				.map(Rule8Atom::getInstance)
				.filter(Optional::isPresent)
				.map(Optional::get)
				.map(Factory.getInstance()::createMRule8Atom)
				.collect(Collectors.toList());
	}

	private List<IfStatement> getAtoms(ASTNode astNode) {
		OptionalInvocationFinder optionalInvocationFinder = new OptionalInvocationFinder();
		List<MethodInvocation> invocations = optionalInvocationFinder.getInvocations(astNode);
		
		return collectAntipatterns(invocations);
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

	private  boolean isAntipattern(IfStatement ifStatement, String invocatorName) {
		
		 return Stream.of(Pair.with(ifStatement.getThenStatement(), ifStatement.getElseStatement()))
				.filter(this::containsOnlyThenBlock)
				.map(Pair::getValue0)
				.allMatch(thenStatement -> isAntipattern(thenStatement, invocatorName));

	}
	
	private boolean containsOnlyThenBlock(Pair<Statement, Statement> statementPair) {
		return  statementPair.getValue0() != null && (statementPair.getValue1() == null);
	}


	private boolean isAntipattern(Statement statementForThen, String invocatorName) {
		return ToolBoxForIfStatementAnalysis.getCyclomaticComplexity(statementForThen) == 1
				&& ToolBoxForIfStatementAnalysis.isStatementComposedByASimgleAction(statementForThen)
				&& ToolBoxForIfStatementAnalysis.containsGetFromOptional(statementForThen, invocatorName) 
				&& ToolBoxForIfStatementAnalysis.statementDoesNotContainNonConsumerElements(statementForThen);
	}

}