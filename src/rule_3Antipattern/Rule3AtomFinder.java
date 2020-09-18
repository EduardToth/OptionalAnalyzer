package rule_3Antipattern;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.IfStatement;
import org.eclipse.jdt.core.dom.MethodInvocation;
import org.eclipse.jdt.core.dom.ReturnStatement;
import org.eclipse.jdt.core.dom.Statement;
import optionalanalizer.metamodel.entity.MRule3Atom;
import optionalanalizer.metamodel.factory.Factory;
import utilities.OptionalInvocationFinder;
import utilities.ToolBoxForIfStatementAnalysis;
import utilities.Unit;

public class Rule3AtomFinder{

	public List<MRule3Atom> getMAtoms(ASTNode astNode) {
		return getAtoms(astNode)
				.stream()
				.map(Rule3Atom::getInstance)
				.filter(Optional::isPresent)
				.map(Optional::get)
				.map(el -> Factory.getInstance().createMRule3Atom(el))
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
				.filter(ToolBoxForIfStatementAnalysis::isParentIfStatement)
				.map(inv -> (IfStatement)inv.getParent())
				.filter(ifStatement -> isAntipattern(ifStatement, invocatorName.getValue0()))
				.collect(Collectors.toList());
	}


	private  boolean isAntipattern(IfStatement ifStatement, String invocatorName) {
		Optional<Statement> thenStatement = Optional.ofNullable(ifStatement.getThenStatement());
		Optional<Statement> elseStatement = Optional.ofNullable(ifStatement.getElseStatement());

		if(!thenStatement.isPresent() || !elseStatement.isPresent()) {
			return false;
		}

		Optional<ReturnStatement> returnStatementForThen = ToolBoxForIfStatementAnalysis.getReturnStatement(thenStatement.get());
		Optional<ReturnStatement> returnStatementForElse = ToolBoxForIfStatementAnalysis.getReturnStatement(elseStatement.get());

		if(!returnStatementForElse.isPresent() || !returnStatementForThen.isPresent()) {
			return false;
		}

		return ToolBoxForIfStatementAnalysis.isSameContext(thenStatement.get(), returnStatementForThen.get(),
				elseStatement.get(), returnStatementForElse.get()) &&
				isAntipattern(returnStatementForThen.get(), returnStatementForElse.get(), invocatorName);

	}

	private boolean isAntipattern(ReturnStatement returnStatementForThen, ReturnStatement returnStatementForElse, String invocatorName) {
		return ToolBoxForIfStatementAnalysis.containsGetFromOptional(returnStatementForThen, invocatorName) &&
				!containsMethodInvocation(returnStatementForElse) ||
				ToolBoxForIfStatementAnalysis.containsGetFromOptional(returnStatementForElse, invocatorName) &&
				!containsMethodInvocation(returnStatementForThen);
	}


	

	private boolean containsMethodInvocation(ReturnStatement returnStatement) {
		String stringExpression = returnStatement.getExpression().toString();
		return stringExpression.matches(".*\\(.*\\).*");
	}
}
