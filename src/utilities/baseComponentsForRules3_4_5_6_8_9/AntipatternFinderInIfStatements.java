package utilities.baseComponentsForRules3_4_5_6_8_9;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.IfStatement;
import org.eclipse.jdt.core.dom.MethodInvocation;
import org.eclipse.jdt.core.dom.ReturnStatement;
import org.eclipse.jdt.core.dom.Statement;
import org.eclipse.jdt.core.dom.ThrowStatement;

import utilities.Unit;
import utilities.UtilityClass;

public class AntipatternFinderInIfStatements {

	public  Antipattern lookForAntipatternInIfStatement(IfStatement ifStatement, String invocatorName) {
		Optional<Statement> thenStatement = Optional.ofNullable(ifStatement.getThenStatement());
		Optional<Statement> elseStatement = Optional.ofNullable(ifStatement.getElseStatement());
		final Unit<TerminationStatementState> stateForThen = new Unit<>(TerminationStatementState.NONE);
		final Unit<TerminationStatementState> stateForElse = new Unit<>(TerminationStatementState.NONE);


		thenStatement.ifPresent((el) ->  stateForThen.setAt0(analyzeStatement(el, invocatorName)));
		elseStatement.ifPresent((el) -> stateForElse.setAt0(analyzeStatement(el, invocatorName)));

		return lookForAntipattern(stateForThen.getValue0(), stateForElse.getValue0());
	}

	protected List<IfStatement> collectAntipatterns(List<MethodInvocation> invocations, Antipattern antipattern) {
		Unit<Optional<String>> invocatorName = new Unit<>(Optional.empty());
		
		return invocations.stream()
				.peek(inv -> setInvocatorName(inv, invocatorName))
				.filter((inv) -> invocatorName.getValue0().isPresent())
				.filter(this::isParentIfStatement)
				.map(inv -> (IfStatement)inv.getParent())
				.filter(ifStatement -> isAntipattern(antipattern, ifStatement, invocatorName.getValue0().get()))
				.collect(Collectors.toList());
	}

	private Antipattern lookForAntipattern(final TerminationStatementState stateForThen,
			final TerminationStatementState stateForElse) {
		if(isRule3sAntipattern(stateForThen, stateForElse)) {
			return Antipattern.RULE_3_ANTIPATTERN;
		} else if(isRule4sAntipattern(stateForThen, stateForElse)) {
			return Antipattern.RULE_4_ANTIPATTERN;
		} else if(isRule5sAntipattern(stateForThen, stateForElse)) {
			return Antipattern.RULE_5_ANTIPATTERN;
		} else if(isRule6sAntipattern(stateForThen, stateForElse)) {
			return Antipattern.RULE_6_ANTIPATTERN;
		} else if(isRule8sAntipattern(stateForThen, stateForElse)) {
			return Antipattern.RULE_8_ANTIPATTERN;
		} else if(isRule9sAntipattern(stateForThen, stateForElse)) {
			return Antipattern.RULE_9_ANTIPATTERN;
		}

		return Antipattern.NONE_OF_THEM;
	}

	private  boolean isRule8sAntipattern(TerminationStatementState stateForThen, TerminationStatementState stateForElse) {
		return stateForThen == TerminationStatementState.VERIFIED_GET
				&& (stateForElse == TerminationStatementState.NONE
				|| stateForElse == TerminationStatementState.EMPTY);
	}

	private  boolean isRule4sAntipattern(TerminationStatementState stateForThen, TerminationStatementState stateForElse) {
		return stateForThen == TerminationStatementState.VERIFIED_GET
				&& stateForElse == TerminationStatementState.INVOCATION
				|| stateForThen == TerminationStatementState.INVOCATION
				&& stateForElse == TerminationStatementState.VERIFIED_GET;
	}

	private  boolean isRule5sAntipattern(TerminationStatementState stateForThen, TerminationStatementState stateForElse) {
		return stateForThen == TerminationStatementState.VERIFIED_GET
				&& stateForElse == TerminationStatementState.NSE_EXCEPTION
				|| stateForThen == TerminationStatementState.NSE_EXCEPTION
				&& stateForElse == TerminationStatementState.VERIFIED_GET;
	}

	private  boolean isRule6sAntipattern(TerminationStatementState stateForThen, TerminationStatementState stateForElse) {
		return stateForThen == TerminationStatementState.VERIFIED_GET
				&& stateForElse == TerminationStatementState.EXCEPTION
				|| stateForThen == TerminationStatementState.EXCEPTION
				&& stateForElse == TerminationStatementState.VERIFIED_GET;
	}

	private boolean isRule3sAntipattern(TerminationStatementState stateForThen, TerminationStatementState stateForElse) {
		return stateForThen == TerminationStatementState.VERIFIED_GET
				&& stateForElse == TerminationStatementState.CONSTANT_OR_VARIABLE
				|| stateForThen == TerminationStatementState.CONSTANT_OR_VARIABLE
				&& stateForElse == TerminationStatementState.VERIFIED_GET;
	}

	private boolean isRule9sAntipattern(TerminationStatementState stateForThen, TerminationStatementState stateForElse) {
		return stateForThen == TerminationStatementState.VERIFIED_GET
				&& stateForElse == TerminationStatementState.SOMETHING
				|| stateForThen == TerminationStatementState.SOMETHING
				&& stateForElse == TerminationStatementState.VERIFIED_GET;
	}

	private TerminationStatementState analyzeStatement(Statement statement, String invocatorName) {
		final Unit<TerminationStatementState> statementState= new Unit<>(TerminationStatementState.NONE);

		statement.accept(new ASTVisitor() {
			@Override
			public boolean visit(ThrowStatement statement) {

				String typeName = statement.getExpression().resolveTypeBinding().getQualifiedName();
				if(typeName.equals("java.util.NoSuchElementException")) {
					statementState.setAt0(TerminationStatementState.NSE_EXCEPTION);
				} else {
					statementState.setAt0(TerminationStatementState.EXCEPTION);
				}

				return super.visit(statement);
			}
		});
		if(statementState.getValue0().equals(TerminationStatementState.NONE)) {
			statement.accept(new ASTVisitor() {
				@Override
				public boolean visit(ReturnStatement statement) {
					statementState.setAt0( analyzeReturnStatement(invocatorName, statement) );
					return super.visit(statement);
				}
			});
		}

		if(statementState.getValue0().equals(TerminationStatementState.NONE)) {
			final Unit<TerminationStatementState> state = new Unit<>(TerminationStatementState.NONE);
			ReturnStatementVisitor visitor = new ReturnStatementVisitor(state, invocatorName);
			statement.accept( visitor );
			if( state.getValue0() != TerminationStatementState.NONE) {
				statementState.setAt0( state.getValue0() );
			}
		}

		if(statementState.getValue0().equals(TerminationStatementState.NONE)) {

			String blockInStringFormWhithoutWhitespaces = removeWhiteSpaces(statement.toString());
			if(blockInStringFormWhithoutWhitespaces.equals("{}")) {
				statementState.setAt0( TerminationStatementState.EMPTY);
			} else {
				statementState.setAt0( TerminationStatementState.SOMETHING);
			}

		}

		return statementState.getValue0();
	}

	private String removeWhiteSpaces(String str) {

		String result = "";
		for(int i=0; i < str.length(); i++) {
			final char ch = str.charAt( i );
			if(!Character.isWhitespace( ch ) ) {
				result += ch;
			}
		}
		return result;
	}

	private TerminationStatementState analyzeReturnStatement(String invocatorName, ReturnStatement statement) {
		final Unit<TerminationStatementState> state = new Unit<>(TerminationStatementState.NONE);
		ReturnStatementVisitor visitor = new ReturnStatementVisitor( state, invocatorName);
		statement.accept(visitor);

		if(!visitor.isPresentGetCombinationFound()) {

			state.setAt0(lookForTheOtherOptions(statement));
		}

		return state.getValue0();
	}


	protected boolean isParentIfStatement(MethodInvocation methodInvocation) {
		return methodInvocation.getParent() instanceof IfStatement;
	}

	protected void setInvocatorName(MethodInvocation invocation, Unit<Optional<String>> invocatorName) {
		invocatorName.setAt0( UtilityClass.getInvocatorName(invocation));
	}

	protected boolean isAntipattern(Antipattern antipattern, IfStatement ifStatement, String invocatorName) {
		AntipatternFinderInIfStatements finder = new AntipatternFinderInIfStatements();
		Antipattern foundAntipattern = finder.lookForAntipatternInIfStatement(ifStatement, invocatorName);

		return foundAntipattern  == antipattern;
	}

	private TerminationStatementState lookForTheOtherOptions(ReturnStatement statement) {

		String stringExpression = statement.getExpression().toString();
		if(stringExpression.matches(".*\\(.*\\).*")) {
			return TerminationStatementState.INVOCATION;
		}
		return TerminationStatementState.CONSTANT_OR_VARIABLE;
	}
}
