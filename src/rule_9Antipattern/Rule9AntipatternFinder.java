package rule_9Antipattern;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.IfStatement;
import org.eclipse.jdt.core.dom.MethodInvocation;
import org.eclipse.jdt.core.dom.Statement;
import org.javatuples.Pair;

import optionalanalizer.metamodel.entity.MRule9Atom;
import optionalanalizer.metamodel.factory.Factory;
import rule_7Antipattern.Rule7Antipattern;
import rule_7Antipattern.Rule7AntipatternFinder;
import utilities.Antipattern;
import utilities.OptionalInvocationFinder;
import utilities.ToolBoxForIfStatementAnalysis;
import utilities.UtilityClass;

public class Rule9AntipatternFinder{

	public List<MRule9Atom> getMAtoms(ASTNode astNode) {

		List<? extends Antipattern> rule7Atoms = getRule7Atoms(astNode);
		List<? extends Antipattern> rule9Atoms =  getAtoms(astNode).stream()
				.map(Rule9Antipattern::getInstance)
				.filter(Optional::isPresent)
				.map(Optional::get)
				.collect(Collectors.toList());

		rule9Atoms.removeAll(rule7Atoms);

		return rule9Atoms.stream()
				.map(Factory.getInstance()::createMRule9Atom)
				.collect(Collectors.toList());
	}

	private List<IfStatement> getAtoms(ASTNode astNode) {
		OptionalInvocationFinder optionalInvocationFinder = new OptionalInvocationFinder();
		List<MethodInvocation> invocations = optionalInvocationFinder.getInvocations(astNode);

		return collectAntipatterns(invocations);
	}

	private List<Rule7Antipattern> getRule7Atoms(ASTNode astNode) {
		Rule7AntipatternFinder rule7AtomFinder = new Rule7AntipatternFinder();

		return rule7AtomFinder.getMAtoms(astNode).stream()
				.map(mAtom -> (Rule7Antipattern)mAtom.getUnderlyingObject())
				.collect(Collectors.toList());
	}

	private List<IfStatement> collectAntipatterns(List<MethodInvocation> invocations) {

		return invocations.stream()
				.map(inv -> Pair.with(inv, UtilityClass.getInvocatorName(inv).orElse("")))
				.filter(invocationAndInvocatorName 
						-> !invocationAndInvocatorName.getValue1().equals(""))
				.filter(invocationAndInvocatorName 
						-> ToolBoxForIfStatementAnalysis
						.isSuperParentIfStatement(invocationAndInvocatorName.getValue0()))
				.map(invocationAndInvocatorName 
						-> Pair.with(ToolBoxForIfStatementAnalysis
								.getIfStatement(invocationAndInvocatorName.getValue0()),
								invocationAndInvocatorName.getValue1()))
				.filter(ifStatementAndInvocatorName 
						-> isAntipattern(ifStatementAndInvocatorName.getValue0(),
								ifStatementAndInvocatorName.getValue1()))
				.map(ifStatementAndInvocatorName -> ifStatementAndInvocatorName.getValue0())
				.collect(Collectors.toList());
	}

	private  boolean isAntipattern(IfStatement ifStatement, String invocatorName) {

		Optional<Statement> thenStatement = Optional.ofNullable(ifStatement.getThenStatement());
		Optional<Statement> elseStatement = Optional.ofNullable(ifStatement.getElseStatement());

		return thenStatement.flatMap(
						thenStm -> elseStatement.map(elseStm -> isAntipattern(thenStm, elseStm, invocatorName))
				).orElse(false);
		
	}

	private boolean isAntipattern(Statement thenStatement, Statement elseStatement, String invocatorName) {
		return		ToolBoxForIfStatementAnalysis.getCyclomaticComplexity(thenStatement) == 1
				&& ToolBoxForIfStatementAnalysis.getCyclomaticComplexity(elseStatement) == 1
				&& ToolBoxForIfStatementAnalysis.isStatementComposedByASimgleAction(thenStatement)
				&& ToolBoxForIfStatementAnalysis.isStatementComposedByASimgleAction(elseStatement)
				&& ToolBoxForIfStatementAnalysis.statementDoesNotContainNonConsumerElements(thenStatement) 
				&& ToolBoxForIfStatementAnalysis.statementDoesNotContainNonConsumerElements(elseStatement)
				&& (ToolBoxForIfStatementAnalysis.containsGetFromOptional(thenStatement, invocatorName) 
						&& !ToolBoxForIfStatementAnalysis.containsGetFromOptional(elseStatement, invocatorName) 
						|| !ToolBoxForIfStatementAnalysis.containsGetFromOptional(thenStatement, invocatorName) 
						&& ToolBoxForIfStatementAnalysis.containsGetFromOptional(elseStatement, invocatorName));
	}
}
