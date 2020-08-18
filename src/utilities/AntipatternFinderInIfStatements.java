package utilities;

import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.IfStatement;
import org.eclipse.jdt.core.dom.MethodInvocation;
import org.eclipse.jdt.core.dom.ReturnStatement;
import org.eclipse.jdt.core.dom.Statement;

public class AntipatternFinderInIfStatements {

	private enum ReturnStatementState {
		VERIFIED_GET,
		CONSTANT_OR_VARIABLE,
		INVOCATION,
		NONE
	}

	public  Antipattern lookForAntipatternInIfStatement(IfStatement ifStatement, String invocatorName) {
		Statement thenStatement = ifStatement.getThenStatement();
		Statement elseStatement = ifStatement.getElseStatement();

		ReturnStatementState stateForThen = analyzeStatement(thenStatement, invocatorName);
		ReturnStatementState stateForElse = analyzeStatement(elseStatement, invocatorName);
		if(isRule3sAntipattern(stateForThen, stateForElse)) {

			return Antipattern.RULE_3_ANTIPATTERN;
		} else if(isRule4sAntipattern(stateForThen, stateForElse)) {
			return Antipattern.RULE4_ANTIPATTERN;
		}


		return Antipattern.NONE_OF_THEM;
	}

	private  boolean isRule4sAntipattern(ReturnStatementState stateForThen, ReturnStatementState stateForElse) {
		return stateForThen == ReturnStatementState.VERIFIED_GET
				&& stateForElse == ReturnStatementState.INVOCATION
				|| stateForThen == ReturnStatementState.INVOCATION
				&& stateForElse == ReturnStatementState.VERIFIED_GET;
	}

	private boolean isRule3sAntipattern(ReturnStatementState stateForThen, ReturnStatementState stateForElse) {
		return stateForThen == ReturnStatementState.VERIFIED_GET
				&& stateForElse == ReturnStatementState.CONSTANT_OR_VARIABLE
				|| stateForThen == ReturnStatementState.CONSTANT_OR_VARIABLE
				&& stateForElse == ReturnStatementState.VERIFIED_GET;
	}

	private ReturnStatementState analyzeStatement(Statement statement, String invocatorName) {
		Unit<ReturnStatementState> antipattern  = new Unit<>(ReturnStatementState.NONE);

		statement.accept(new ASTVisitor() {
			@Override
			public boolean visit(ReturnStatement statement) {
				antipattern.setAt0( analyzeReturnStatement(invocatorName, statement) );
				return super.visit(statement);
			}
		});
		return antipattern.getValue0();
	}

	private ReturnStatementState analyzeReturnStatement(String invocatorName, ReturnStatement statement) {
		final Unit<ReturnStatementState> state = new Unit<>(ReturnStatementState.NONE);
		final Unit<Boolean> entered = new Unit<>(false);
		statement.accept(new ASTVisitor() {
			@Override
			public boolean visit(MethodInvocation invocation) {
				final boolean isVerifiedGet = isVerifiedGet(invocatorName, invocation);
				System.out.println(isVerifiedGet);
				if(isVerifiedGet) {
					entered.setAt0( true );
					state.setAt0(ReturnStatementState.VERIFIED_GET);
				}
				return super.visit(invocation);
			}


			private boolean isVerifiedGet(String invocatorName, MethodInvocation invocation) {
				String invocatorNameInReturnStatement = invocation.getExpression().toString();

				boolean isTheSameInvocator = invocatorNameInReturnStatement.equals(invocatorName);

				String invokedMethodName = invocation.getName().toString();

				boolean isInvocatorOptional = invocation.getExpression() != null &&
						UtilityClass.isInvocatorOfOptionalType(invocation.getExpression()
								.resolveTypeBinding().getQualifiedName());

				final boolean itsFine = isTheSameInvocator && isInvocatorOptional && invokedMethodName.equals("get");
				return itsFine;
			}


		});

		if(!entered.getValue0()) {
			state.setAt0(lookForTheOtherOptions( statement ));
		}
		return state.getValue0();
	}

	private ReturnStatementState lookForTheOtherOptions(ReturnStatement statement) {

		String stringExpression = statement.getExpression().toString();
		if(stringExpression.matches(".*\\(.*\\).*")) {
			return ReturnStatementState.INVOCATION;
		}
		return ReturnStatementState.CONSTANT_OR_VARIABLE;
	}
}
