package utilities;

import java.util.LinkedList;
import java.util.List;

import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.MethodInvocation;

public class MethodInvokedFromOptionalVisitor extends ASTVisitor{

	private String invokedMethodName;

	private List<MethodInvocation> invocations = new LinkedList<>();
	
	public MethodInvokedFromOptionalVisitor(String invokedMethod) {
		super();
		this.invokedMethodName = invokedMethod;
	}

	@Override
	public boolean visit(MethodInvocation invocation) {
		String invokedMethodName = invocation.getName().toString();
		if(itsFine(invocation, invokedMethodName)) {
			invocations.add(invocation);
		}
		return super.visit( invocation );
	}

	public List<MethodInvocation> getInvocations() {
		return invocations;
	}

	private boolean itsFine(MethodInvocation invocation, String invokedMethodName) {
		String typeName = "";
		try {
			typeName = invocation.getExpression().resolveTypeBinding().getQualifiedName();
		} catch(NullPointerException npe) {}

		return invocation.getExpression() != null &&
				UtilityClass.isTypeOptional(typeName)
				&& invokedMethodName.equals(this.invokedMethodName);

	}
}
