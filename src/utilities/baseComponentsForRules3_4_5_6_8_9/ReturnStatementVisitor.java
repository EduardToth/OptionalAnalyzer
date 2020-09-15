package utilities.baseComponentsForRules3_4_5_6_8_9;

import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.MethodInvocation;
import org.eclipse.jdt.core.dom.ReturnStatement;

import utilities.Unit;
import utilities.UtilityClass;


public class ReturnStatementVisitor extends ASTVisitor{
	private Unit<TerminationStatementState> state = new Unit<>(TerminationStatementState.NONE);
	private Unit<Boolean> entered = new Unit<>(false);
	private String invocatorName;
	public ReturnStatementVisitor(Unit<TerminationStatementState> state, String invocatorName) {
		this.state = state;
		this.invocatorName = invocatorName;
	}

	@Override
	public boolean visit(MethodInvocation invocation) {
		final boolean isVerifiedGet = isVerifiedGet(invocatorName, invocation);
		if(isVerifiedGet) {
			state.setAt0(TerminationStatementState.VERIFIED_GET);
			entered.setAt0( true );
		}
		return super.visit(invocation);
	}

	private boolean isVerifiedGet(String invocatorName, MethodInvocation invocation) {
		if(invocation.getExpression() == null) {
			return false;
		}
		String invocatorNameInReturnStatement = invocation.getExpression().toString();

		boolean isTheSameInvocator = invocatorNameInReturnStatement.equals(invocatorName);

		String invokedMethodName = invocation.getName().toString();

		boolean isInvocatorOptional = invocation.getExpression() != null &&
				UtilityClass.isTypeOptional(invocation.getExpression()
						.resolveTypeBinding().getQualifiedName());

		final boolean itsFine = isTheSameInvocator && isInvocatorOptional && invokedMethodName.equals("get");
		return itsFine;
	}

	public boolean isPresentGetCombinationFound() {
		return entered.getValue0();
	}
}
