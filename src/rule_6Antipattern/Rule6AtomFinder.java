package rule_6Antipattern;

import java.io.IOException;
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

public class Rule6AtomFinder{
	
	private String f() throws IOException {
		Optional<String> o = Optional.empty();
		
		return o.orElseThrow(IOException::new);
	}

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

		return invocations.parallelStream()
				.map(inv -> Pair.with(inv, UtilityClass.getInvocatorName(inv).orElse("")))
				.filter(invocationAndInvocatorName -> !invocationAndInvocatorName.getValue1().equals(""))
				.filter(invocationAndInvocatorName 
						-> ToolBoxForIfStatementAnalysis.isSuperParentIfStatement(invocationAndInvocatorName.getValue0()))
				.map(invocationAndInvocatorName 
						-> Pair.with(ToolBoxForIfStatementAnalysis.getIfStatement(invocationAndInvocatorName.getValue0()),
								invocationAndInvocatorName.getValue1()))
				.filter(ifStatementAndInvocatorName 
						-> isAntipattern(ifStatementAndInvocatorName.getValue0(),
								ifStatementAndInvocatorName.getValue1()))
				.map(ifStatementAndInvocatorName -> ifStatementAndInvocatorName.getValue0())
				.collect(Collectors.toList());

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
				
				System.out.println("Throw statement: " + throwStatement.toString());
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
