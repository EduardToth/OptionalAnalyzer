package rule_9Antipattern;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.IfStatement;
import org.eclipse.jdt.core.dom.MethodInvocation;
import org.eclipse.jdt.core.dom.Statement;

import optionalanalyzer.metamodel.entity.MRule9sAntipattern;
import optionalanalyzer.metamodel.factory.Factory;
import rule_7Antipattern.Rule7Antipattern;
import rule_7Antipattern.Rule7AntipatternFinder;
import utilities.Antipattern;
import utilities.OptionalInvocationFinder;
import utilities.ToolBoxForIfStatementAnalysis;
import utilities.UtilityClass;

public class Rule9AntipatternFinder{

	public List<MRule9sAntipattern> getMAntipatterns(ASTNode astNode) {

		List<? extends Antipattern> rule7Antipatterns = getRule7Antipatterns(astNode);
		List<? extends Antipattern> rule9Antipatterns =  getAntipatterns(astNode).stream()
				.map(Rule9Antipattern::getInstance)
				.flatMap(Optional::stream)
				.collect(Collectors.toList());

		rule9Antipatterns.removeAll(rule7Antipatterns);

		return rule9Antipatterns.stream()
				.map(Factory.getInstance()::createMRule9sAntipattern)
				.collect(Collectors.toList());
	}

	private List<IfStatement> getAntipatterns(ASTNode astNode) {
		OptionalInvocationFinder optionalInvocationFinder = new OptionalInvocationFinder();
		List<MethodInvocation> invocations = optionalInvocationFinder.getInvocations(astNode);

		return collectAntipatterns(invocations);
	}

	private List<Rule7Antipattern> getRule7Antipatterns(ASTNode astNode) {
		Rule7AntipatternFinder rule7AntipatternFinder = new Rule7AntipatternFinder();

		return rule7AntipatternFinder.getMAntipatterns(astNode).stream()
				.map(mAntipattern -> (Rule7Antipattern)mAntipattern.getUnderlyingObject())
				.collect(Collectors.toList());
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
