package rule_8Antipattern;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.IfStatement;
import org.eclipse.jdt.core.dom.MethodInvocation;
import org.eclipse.jdt.core.dom.Statement;

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
				.map(el -> Factory.getInstance().createMRule8Atom(el))
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
		Optional<Statement> thenStatementOptional = Optional.ofNullable(ifStatement.getThenStatement());
		Optional<Statement> elseStatementOptional = Optional.ofNullable(ifStatement.getElseStatement());

		if(elseStatementOptional.isPresent() && !ToolBoxForIfStatementAnalysis.isEmptyStatement(elseStatementOptional.get())) {
			return false;
		}

		Statement thenStatement = null;

		if(!thenStatementOptional.isPresent()) {
			return false;
		} else {
			thenStatement = thenStatementOptional.get();
		}
		return isAntipattern(thenStatement, invocatorName);

	}


	private boolean isAntipattern(Statement statementForThen, String invocatorName) {
		return ToolBoxForIfStatementAnalysis.containsGetFromOptional(statementForThen, invocatorName) &&
				ToolBoxForIfStatementAnalysis.statementDoesNotContainNonConsumerElements(statementForThen);
	}

}