package rule10Antipattern;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.Expression;
import org.eclipse.jdt.core.dom.ITypeBinding;
import org.eclipse.jdt.core.dom.IfStatement;
import org.eclipse.jdt.core.dom.MethodInvocation;
import org.eclipse.jdt.core.dom.ReturnStatement;
import org.eclipse.jdt.core.dom.Statement;

import optionalanalyzer.metamodel.entity.MRule10sAntipattern;
import optionalanalyzer.metamodel.factory.Factory;
import utilities.OptionalInvocationFinder;
import utilities.ToolBoxForIfStatementAnalysis;
import utilities.UtilityClass;

public class Rule10AntipatternFinder {
	public List<MRule10sAntipattern> getMAntipatterns(ASTNode astNode) {

		return  getProblematicIfStatements(astNode).stream()
				.map(Rule10Antipattern::getInstance)
				.flatMap(Optional::stream)
				.map(Factory.getInstance()::createMRule10sAntipattern)
				.collect(Collectors.toList());

	}

	private List<IfStatement> getProblematicIfStatements(ASTNode astNode) {
		OptionalInvocationFinder optionalInvocationFinder = new OptionalInvocationFinder();
		List<MethodInvocation> invocations = optionalInvocationFinder.getInvocations(astNode);

		return getProblematicIfStatements(invocations);
	}

	private List<IfStatement> getProblematicIfStatements(List<MethodInvocation> invocations) {

		return invocations.stream()
				.map(this::getParentIfStatementIfProblematic)
				.flatMap(Optional::stream)
				.collect(Collectors.toList());
	}
	
	private Optional<IfStatement> getParentIfStatementIfProblematic(MethodInvocation methodInvocation) {

		if(ToolBoxForIfStatementAnalysis.isSuperParentIfStatement(methodInvocation)) {
			final IfStatement ifStatement = ToolBoxForIfStatementAnalysis.getIfStatement(methodInvocation);
			return UtilityClass.getInvocatorName(methodInvocation)
					.filter(invName -> isAntipattern(ifStatement, invName))
					.map(invName -> ifStatement);
		}
		
		return Optional.empty();
	}

	private  boolean isAntipattern(IfStatement ifStatement, String invocatorName) {

		Statement thenStatement = ifStatement.getThenStatement();
		Statement elseStatement = ifStatement.getElseStatement();
		
		if(thenStatement != null && elseStatement != null) {
			return bothOfThemContainReturnStatement(ifStatement, elseStatement)
					&& isAntipattern(thenStatement, elseStatement, invocatorName);
		}
		
		return false;
	}

	private boolean bothOfThemContainReturnStatement(Statement ifStatement, Statement elseStatement) {
		return ToolBoxForIfStatementAnalysis.getReturnStatement(ifStatement).isPresent()
				&& ToolBoxForIfStatementAnalysis.getReturnStatement(elseStatement).isPresent();
	}

	private boolean isAntipattern(Statement statementForThen, Statement statementForElse, String invocatorName) {
		
		String typeName = "";
		try {
			typeName = ToolBoxForIfStatementAnalysis.getReturnStatement(statementForThen)
					.map(ReturnStatement::getExpression)
					.map(Expression::resolveTypeBinding)
					.map(ITypeBinding::getQualifiedName)
					.orElse("");
			
		}catch(NullPointerException ignored) {}

		return 	ToolBoxForIfStatementAnalysis.getCyclomaticComplexity(statementForThen) == 1
				&& ToolBoxForIfStatementAnalysis.getCyclomaticComplexity(statementForElse) == 1
				&& ToolBoxForIfStatementAnalysis.isStatementComposedByASimgleAction(statementForThen)
				&& ToolBoxForIfStatementAnalysis.isStatementComposedByASimgleAction(statementForElse) 
				&& UtilityClass.isTypeOptional(typeName);
	}
}
