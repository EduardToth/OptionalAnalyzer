package compilationUnits.groups.Rule3_4_5_6AntipatternBuilders.baseComponents;

import java.util.Optional;

import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.IfStatement;
import org.eclipse.jdt.core.dom.MethodInvocation;
import org.eclipse.jdt.core.dom.ReturnStatement;
import org.eclipse.jdt.core.dom.Statement;
import org.eclipse.jdt.core.dom.ThrowStatement;

import optionalanalizer.metamodel.entity.MIfStatement;
import optionalanalizer.metamodel.entity.MInvocation;
import optionalanalizer.metamodel.factory.Factory;
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
		
		System.out.println(stateForElse.getValue0() + " " + stateForThen.getValue0());

		return lookForAntipattern(stateForThen.getValue0(), stateForElse.getValue0());
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
		}

		return Antipattern.NONE_OF_THEM;
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

	private TerminationStatementState analyzeStatement(Statement statement, String invocatorName) {
		final Unit<TerminationStatementState> antipattern  = new Unit<>(TerminationStatementState.NONE);

		statement.accept(new ASTVisitor() {
			@Override
			public boolean visit(ThrowStatement statement) {
			
				String typeName = statement.getExpression().resolveTypeBinding().getQualifiedName();
				System.out.println("$$$$$$$ " + typeName);
				if(typeName.equals("java.util.NoSuchElementException")) {
					antipattern.setAt0(TerminationStatementState.NSE_EXCEPTION);
				} else {
					antipattern.setAt0(TerminationStatementState.EXCEPTION);
				}

				return super.visit(statement);
			}
		});
		if(antipattern.getValue0().equals(TerminationStatementState.NONE)) {
			statement.accept(new ASTVisitor() {
				@Override
				public boolean visit(ReturnStatement statement) {
					antipattern.setAt0( analyzeReturnStatement(invocatorName, statement) );
					return super.visit(statement);
				}
			});
		}
		return antipattern.getValue0();
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

	protected MIfStatement convertToMIfStatement(IfStatement ifStatement) {
		return Factory.getInstance().createMIfStatement(ifStatement);
	}

	protected void setInvocatorName(MethodInvocation invocation, Unit<Optional<String>> invocatorName) {
		invocatorName.setAt0( UtilityClass.getInvocatorName(invocation));
	}

	protected MethodInvocation convertToMethodInvocation(MInvocation mInvocation) {
		return (MethodInvocation)mInvocation.getUnderlyingObject();
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
