package rule_6Antipattern;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.IfStatement;
import org.eclipse.jdt.core.dom.MethodInvocation;
import org.eclipse.jdt.core.dom.ReturnStatement;
import org.eclipse.jdt.core.dom.Statement;
import org.eclipse.jdt.core.dom.ThrowStatement;
import org.javatuples.Pair;

import optionalanalizer.metamodel.entity.MRule6Atom;
import optionalanalizer.metamodel.factory.Factory;
import utilities.OptionalInvocationFinder;
import utilities.ToolBoxForIfStatementAnalysis;
import utilities.UtilityClass;

public class Rule6AtomFinder {

	public List<MRule6Atom> getMAtoms(ASTNode astNode) {
		return getAtoms(astNode)
				.stream()
				.map(Rule6Atom::getInstance)
				.filter(Optional::isPresent)
				.map(Optional::get)
				.map(Factory.getInstance()::createMRule6Atom)
				.collect(Collectors.toList());
	}

	private List<IfStatement> getAtoms(ASTNode astNode) {
		OptionalInvocationFinder optionalInvocationFinder = new OptionalInvocationFinder();
		List<MethodInvocation> invocations = optionalInvocationFinder.getInvocations(astNode);

		return collectAntipatterns(invocations);
	}

	private List<IfStatement> collectAntipatterns(List<MethodInvocation> invocations) {

		return invocations.stream()
				.map(this::getWrapperIfStatementIfThereIsAntipatternThere)
				.filter(Optional::isPresent)
				.map(Optional::get)
				.collect(Collectors.toList());

	}
	
	private Optional<IfStatement> getWrapperIfStatementIfThereIsAntipatternThere(MethodInvocation methodInvocation) {
		String invocatorName = UtilityClass.getInvocatorName(methodInvocation).orElse("");
		IfStatement ifStatement = null;
		if(ToolBoxForIfStatementAnalysis.isSuperParentIfStatement(methodInvocation)) {
			ifStatement = ToolBoxForIfStatementAnalysis.getIfStatement(methodInvocation);
			if(!isAntipattern(ifStatement, invocatorName)) {
				ifStatement = null;
			}
		}
		
		return Optional.ofNullable(ifStatement);
	}

	private  boolean isAntipattern(IfStatement ifStatement, String invocatorName) {
		Pair<Statement, Statement> statementPair = Pair.with(ifStatement.getThenStatement(), ifStatement.getElseStatement());

		if(statementPair.getValue0() != null && statementPair.getValue1() != null) {
			return isAntipattern(statementPair.getValue0(), statementPair.getValue1(), invocatorName);
		}

		return false;
		
	}

	private boolean isAntipattern(Statement statementForThen, Statement statementForElse, String invocatorName) {

		return ToolBoxForIfStatementAnalysis.getCyclomaticComplexity(statementForThen) == 1
				&& ToolBoxForIfStatementAnalysis.getCyclomaticComplexity(statementForElse) == 1
				&& ToolBoxForIfStatementAnalysis.isStatementComposedByASimgleAction(statementForThen)
				&& ToolBoxForIfStatementAnalysis.isStatementComposedByASimgleAction(statementForElse)
				&& (containsGetFromOptional(statementForThen, invocatorName) &&
						containsExceptionDifferentFromNoSuchElementException(statementForElse) ||
						containsGetFromOptional(statementForElse, invocatorName) &&
						containsExceptionDifferentFromNoSuchElementException(statementForThen));
	}

	private boolean containsGetFromOptional(Statement statement, String invocatorName) {
		Optional<ReturnStatement> returnStatement = ToolBoxForIfStatementAnalysis.getReturnStatement(statement);

		
		return returnStatement
				.map(retStm -> ToolBoxForIfStatementAnalysis.containsGetFromOptional(retStm, invocatorName))
				.orElse(false);
	}

	private boolean containsExceptionDifferentFromNoSuchElementException(Statement statement) {
		final AtomicBoolean contains = new AtomicBoolean(false);

		statement.accept(new ASTVisitor() {

			@Override
			public boolean visit(ThrowStatement throwStatement) {
				
				String typeName = "";
				try {
					typeName = throwStatement.getExpression().resolveTypeBinding().getQualifiedName();
				}catch(NullPointerException npe) {}

				contains.set(!typeName.equals("java.util.NoSuchElementException"));
				return super.visit(throwStatement);
			}
		});

		return contains.get();
	}
}
