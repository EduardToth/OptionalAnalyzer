package rule_5Antipattern;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.IfStatement;
import org.eclipse.jdt.core.dom.MethodInvocation;
import org.eclipse.jdt.core.dom.ReturnStatement;
import org.eclipse.jdt.core.dom.Statement;
import org.eclipse.jdt.core.dom.ThrowStatement;

import optionalanalizer.metamodel.entity.MRule5Atom;
import optionalanalizer.metamodel.factory.Factory;
import utilities.OptionalInvocationFinder;
import utilities.ToolBoxForIfStatementAnalysis;
import utilities.Unit;

public class Rule5AtomFinder {
	public List<MRule5Atom> getMAtoms(ASTNode astNode) {
		return getAtoms(astNode)
				.stream()
				.map(Rule5Atom::getInstance)
				.filter(Optional::isPresent)
				.map(Optional::get)
				.map(el -> Factory.getInstance().createMRule5Atom(el))
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
		Statement thenStatement = null;
		Statement elseStatement = null;

		if(!thenStatementOptional.isPresent() || !elseStatementOptional.isPresent()) {
			return false;
		} else {
			thenStatement = thenStatementOptional.get();
			elseStatement = elseStatementOptional.get();
		}

		return isAntipattern(thenStatement, elseStatement, invocatorName);

	}

	private boolean areStatementContextsTheSame(Statement statementForThen, Statement statementForElse) {
		return areContextsTheSameForReturnThrow(statementForThen, statementForElse) || 
				areContextsTheSameForThrowReturn(statementForThen, statementForElse);
	}

	private boolean areContextsTheSameForReturnThrow(Statement statementForThen, Statement statementForElse) {
		Optional<ReturnStatement> returnStatement = ToolBoxForIfStatementAnalysis.getReturnStatement(statementForThen);
		Optional<ThrowStatement> throwStatement = ToolBoxForIfStatementAnalysis.getThrowStatement(statementForElse);

		if(returnStatement.isPresent() && throwStatement.isPresent()) {
			return ToolBoxForIfStatementAnalysis.isSameContext(statementForThen, returnStatement.get(), statementForElse, throwStatement.get());
		}

		return false;
	}

	private boolean areContextsTheSameForThrowReturn(Statement statementForThen, Statement statementForElse) {
		Optional<ThrowStatement> throwStatement = ToolBoxForIfStatementAnalysis.getThrowStatement(statementForThen);
		Optional<ReturnStatement> returnStatement = ToolBoxForIfStatementAnalysis.getReturnStatement(statementForElse);

		if(returnStatement.isPresent() && throwStatement.isPresent()) {
			return ToolBoxForIfStatementAnalysis.isSameContext(statementForThen, throwStatement.get(),
					statementForElse, returnStatement.get());
		}

		return false;
	}

	private boolean isAntipattern(Statement statementForThen, Statement statementForElse, String invocatorName) {
		return areStatementContextsTheSame(statementForThen, statementForElse) &&
				(containsGetFromOptional(statementForThen, invocatorName) &&
						containsNoSuchElementExceptionThrow(statementForElse) ||
						containsGetFromOptional(statementForElse, invocatorName) &&
						containsNoSuchElementExceptionThrow(statementForThen));
	}

	private boolean containsGetFromOptional(Statement statement, String invocatorName) {
		Optional<ReturnStatement> returnStatement = ToolBoxForIfStatementAnalysis.getReturnStatement(statement);
		return returnStatement.map(retStm -> ToolBoxForIfStatementAnalysis.containsGetFromOptional(retStm, invocatorName)).orElse(false);
	}


	private boolean containsNoSuchElementExceptionThrow(Statement statement) {
		final Unit<Boolean> contains = new Unit<>(false);
		statement.accept(new ASTVisitor() {

			@Override
			public boolean visit(ThrowStatement throwStatement) {
				String typeName = "";
				try {
					typeName = throwStatement.getExpression().resolveTypeBinding().getQualifiedName();
				} catch(NullPointerException npe) {}
				contains.setAt0(typeName.equals("java.util.NoSuchElementException"));
				return super.visit(throwStatement);
			}
		});
		return contains.getValue0();
	}
}
