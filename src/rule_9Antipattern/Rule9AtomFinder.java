package rule_9Antipattern;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.IfStatement;
import org.eclipse.jdt.core.dom.MethodInvocation;
import org.eclipse.jdt.core.dom.Statement;
import org.javatuples.Pair;

import optionalanalizer.metamodel.entity.MRule9Atom;
import optionalanalizer.metamodel.factory.Factory;
import rule_7Antipattern.Rule7Atom;
import rule_7Antipattern.Rule7AtomFinder;
import utilities.Atom;
import utilities.OptionalInvocationFinder;
import utilities.ToolBoxForIfStatementAnalysis;
import utilities.Unit;

public class Rule9AtomFinder{

	public List<MRule9Atom> getMAtoms(ASTNode astNode) {

		List<? extends Atom> rule7Atoms = getRule7Atoms(astNode);
		List<? extends Atom > rule9Atoms =  getAtoms(astNode).stream()
				.map(Rule9Atom::getInstance)
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

	private List<Rule7Atom> getRule7Atoms(ASTNode astNode) {
		Rule7AtomFinder rule7AtomFinder = new Rule7AtomFinder();

		return rule7AtomFinder.getMAtoms(astNode).stream()
				.map(mAtom -> (Rule7Atom)mAtom.getUnderlyingObject())
				.collect(Collectors.toList());
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
				.filter(pair -> pair.getValue0() != null && pair.getValue1() != null)
				.allMatch(pair -> isAntipattern(pair.getValue0(), pair.getValue1(), invocatorName));
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
