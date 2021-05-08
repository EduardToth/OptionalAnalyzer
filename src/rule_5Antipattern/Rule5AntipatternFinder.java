package rule_5Antipattern;

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

import optionalanalyzer.metamodel.entity.MRule5sAntipattern;
import optionalanalyzer.metamodel.factory.Factory;
import utilities.OptionalInvocationFinder;
import utilities.ToolBoxForIfStatementAnalysis;
import utilities.UtilityClass;

public class Rule5AntipatternFinder {

	public List<MRule5sAntipattern> getMAntipatterns(ASTNode astNode) {

		return getAntipatterns(astNode)
				.stream()
				.map(Rule5Antipattern::getInstance)
				.flatMap(Optional::stream)
				.map(Factory.getInstance()::createMRule5sAntipattern)
				.collect(Collectors.toList());
	}

	private List<IfStatement> getAntipatterns(ASTNode astNode) {
		OptionalInvocationFinder optionalInvocationFinder = new OptionalInvocationFinder();
		List<MethodInvocation> invocations = optionalInvocationFinder.getInvocations(astNode);

		return collectAntipatterns(invocations);
	}

	private List<IfStatement> collectAntipatterns(List<MethodInvocation> invocations) {

		return invocations.stream()
				.map(this::getParentIfStatementIfProblematic)
				.flatMap(Optional::stream)
				.collect(Collectors.toList());
	}

	private Optional<IfStatement> getParentIfStatementIfProblematic(MethodInvocation methodInvocation) {

		if(ToolBoxForIfStatementAnalysis.isSuperParentIfStatement(methodInvocation)) {
			final IfStatement ifStatement = ToolBoxForIfStatementAnalysis.getIfStatement(methodInvocation);
			Optional<String> invocatorName = UtilityClass.getInvocatorName(methodInvocation);
			return invocatorName.filter(invName -> isAntipattern(ifStatement, invName))
					.map(invName -> ifStatement);
		}
		return Optional.empty();
	}


	private  boolean isAntipattern(IfStatement ifStatement, String invocatorName) {

		Optional<Statement> thenStatement = Optional.ofNullable(ifStatement.getThenStatement());
		Optional<Statement> elseStatement = Optional.ofNullable(ifStatement.getElseStatement());

		return thenStatement.flatMap(thenStm -> { 
			return elseStatement.map(elseStm -> isAntipattern(thenStm, elseStm, invocatorName));
		}).orElse(false);
	}

	private boolean isAntipattern(Statement statementForThen, Statement statementForElse, String invocatorName) {

		return 	ToolBoxForIfStatementAnalysis.getCyclomaticComplexity(statementForThen) == 1
				&& ToolBoxForIfStatementAnalysis.getCyclomaticComplexity(statementForElse) == 1
				&& ToolBoxForIfStatementAnalysis.isStatementComposedByASimgleAction(statementForThen)
				&& ToolBoxForIfStatementAnalysis.isStatementComposedByASimgleAction(statementForElse)
				&& (containsGetFromOptional(statementForThen, invocatorName) 
						&& containsNoSuchElementExceptionThrow(statementForElse) 
						|| containsGetFromOptional(statementForElse, invocatorName) 
						&& containsNoSuchElementExceptionThrow(statementForThen));
	}

	private boolean containsGetFromOptional(Statement statement, String invocatorName) {
		Optional<ReturnStatement> returnStatement = ToolBoxForIfStatementAnalysis.getReturnStatement(statement);

		return returnStatement
				.map(retStm -> ToolBoxForIfStatementAnalysis.containsGetFromOptional(retStm, invocatorName))
				.orElse(false);
	}


	private boolean containsNoSuchElementExceptionThrow(Statement statement) {
		final AtomicBoolean contains = new AtomicBoolean(false);

		statement.accept(new ASTVisitor() {

			@Override
			public boolean visit(ThrowStatement throwStatement) {
				String typeName = "";
				try {
					typeName = throwStatement.getExpression().resolveTypeBinding().getQualifiedName();
				} catch(NullPointerException npe) {}
				contains.set(typeName.equals("java.util.NoSuchElementException"));
				return super.visit(throwStatement);
			}
		});

		return contains.get();
	}
}
