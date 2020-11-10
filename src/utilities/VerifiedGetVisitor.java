package utilities;

import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.MethodInvocation;


public class VerifiedGetVisitor extends ASTVisitor{
	private Unit<Boolean> itIs = new Unit<>(false);
	private String invocatorName;
	
	public VerifiedGetVisitor(String invocatorName) {
		this.invocatorName = invocatorName;
	}

	@Override
	public boolean visit(MethodInvocation invocation) {
		itIs.setAt0(false);
		final boolean isVerifiedGet = isVerifiedGet(invocatorName, invocation);
		if(isVerifiedGet) {
			itIs.setAt0(true);
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

		String typeName = "";
		try {
			typeName = invocation.getExpression()
					.resolveTypeBinding().getQualifiedName();
		} catch(NullPointerException npe) {}
		
		boolean isInvocatorOptional = UtilityClass.isTypeOptional(typeName);

		return isTheSameInvocator && isInvocatorOptional && (invokedMethodName.equals("get") || 
				invokedMethodName.equals("getAsInt") || invokedMethodName.equals("getAsLong") ||
				invokedMethodName.equals("getAsDouble"));
	}

	public boolean properGetInvocationFound() {
		return itIs.getValue0();
	}
}
