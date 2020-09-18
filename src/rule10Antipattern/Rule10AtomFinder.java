package rule10Antipattern;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.IfStatement;
import org.eclipse.jdt.core.dom.MethodInvocation;
import org.eclipse.jdt.core.dom.ReturnStatement;
import org.eclipse.jdt.core.dom.Statement;

import optionalanalizer.metamodel.entity.MRule10Atom;
import optionalanalizer.metamodel.factory.Factory;
import utilities.OptionalInvocationFinder;
import utilities.ToolBoxForIfStatementAnalysis;
import utilities.Unit;
import utilities.UtilityClass;

public class Rule10AtomFinder {
	public List<MRule10Atom> getMAtoms(ASTNode astNode) {
		List<ReturnStatement> problematicReturnStatements = getProblematicReturnStatements(astNode);
		List<IfStatement> problematicIfStatements = getProblematicIfStatements(astNode);
		
		return Stream.of(problematicIfStatements, problematicReturnStatements)
				.flatMap(List::stream)
				.map(Rule10Atom::getInstance)
				.filter(Optional::isPresent)
				.map(Optional::get)
				.map(el -> Factory.getInstance().createMRule10Atom(el))
				.collect(Collectors.toList());
	}

	private List<ReturnStatement> getProblematicReturnStatements(ASTNode astNode) {
		final List<ReturnStatement> suspectReturnStatements = new LinkedList<>();
		//Look for return statements that return an Optional 
		astNode.accept(new ASTVisitor() {
			@Override
			public boolean visit(ReturnStatement returnStatement) {
				String typeOfExpression = returnStatement.getExpression()
						.resolveTypeBinding()
						.getQualifiedName();
				if(UtilityClass.isTypeOptional(typeOfExpression)) {
					suspectReturnStatements.add(returnStatement);
				}
				return super.visit(returnStatement);
			}
		});
		
		return suspectReturnStatements.stream()
				.filter(this::isProblematicReturnStatement)
				.collect(Collectors.toList());
	}
	
	private boolean isProblematicReturnStatement(ReturnStatement returnStatement) {
		return returnStatement.getExpression().toString().matches(".*\\.orElseGet\\(.*\\)");
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
				.filter(ToolBoxForIfStatementAnalysis::isParentIfStatement)
				.map(inv -> (IfStatement)inv.getParent())
				.filter(ifStatement -> isIfStatementAntipattern(ifStatement, invocatorName.getValue0()))
				.collect(Collectors.toList());
	}


	private  boolean isIfStatementAntipattern(IfStatement ifStatement, String invocatorName) {
		Optional<Statement> thenStatementOptional = Optional.ofNullable(ifStatement.getThenStatement());
		Optional<Statement> elseStatementOptional = Optional.ofNullable(ifStatement.getElseStatement());
		Statement thenStatement = null;
		Statement elseStatement = null;
		boolean areComponentsPresent = thenStatementOptional.isPresent() && elseStatementOptional.isPresent();

		if(areComponentsPresent) {
			thenStatement = thenStatementOptional.get();
			elseStatement = elseStatementOptional.get();
		} else {
			return false;
		}

		return isIfStatementAntipattern(thenStatement, elseStatement, invocatorName);
	}



	private boolean isIfStatementAntipattern(Statement thenStatement, Statement elseStatement, String invocatorName) {

		Optional<ReturnStatement> returnStatementForThenOptional = ToolBoxForIfStatementAnalysis.getReturnStatement(thenStatement);
		Optional<ReturnStatement> returnStatementForElseOptional = ToolBoxForIfStatementAnalysis.getReturnStatement(elseStatement);
		ReturnStatement returnStatementForThen = null;
		ReturnStatement returnStatementForElse = null;
		if(returnStatementForThenOptional.isPresent() && returnStatementForElseOptional.isPresent()) {
			returnStatementForThen = returnStatementForThenOptional.get();
			returnStatementForElse = returnStatementForElseOptional.get();
		} else {
			return false;
		}

		/*
		 * All the return statements should be of type Optional in this case, but it is enough to verify that only 
		 * with one return statement, because their type are the same
		 */
		return isIfStatementAntipattern(thenStatement, elseStatement,
				returnStatementForThen, returnStatementForElse, invocatorName);
	}

	private boolean isIfStatementAntipattern(Statement statementForThen, Statement statementForElse,
			ReturnStatement returnStatementForThen, ReturnStatement returnStatementForElse, String invocatorName) {

		return UtilityClass.isTypeOptional(returnStatementForThen.getExpression().resolveTypeBinding().getQualifiedName()) &&
				(containsSingleReturnStatement(statementForThen, returnStatementForThen, invocatorName) &&
						ToolBoxForIfStatementAnalysis
						.statementDoesNotContainNonConsumerElementsExceptReturnStatements(statementForElse) ||
						containsSingleReturnStatement(statementForElse, returnStatementForElse, invocatorName) &&
						ToolBoxForIfStatementAnalysis
						.statementDoesNotContainNonConsumerElementsExceptReturnStatements(statementForThen));

	}



	private boolean containsSingleReturnStatement(Statement statement, ReturnStatement returnStatement, String invocatorName) {
		String context = ToolBoxForIfStatementAnalysis.takeOutStatement(statement, returnStatement);
		String rawContext = ToolBoxForIfStatementAnalysis.removeWhiteSpaces(context);
		return (rawContext.equals("{}") || rawContext.equals("{;}")) && returnStatement.getExpression().toString().equals(invocatorName);
	}
}
