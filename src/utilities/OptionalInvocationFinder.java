package utilities;

import java.util.List;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.MethodInvocation;

public class OptionalInvocationFinder {
	private String invokedMethodName = "isPresent";

	public List<MethodInvocation> getInvocations(ASTNode astNode) {
		MethodInvokedFromOptionalVisitor methodInvokedFromOptionalVisitor = 
				new MethodInvokedFromOptionalVisitor(invokedMethodName);

		astNode.accept(methodInvokedFromOptionalVisitor);

		return methodInvokedFromOptionalVisitor.getInvocations();
	}

	public List<MethodInvocation> getInvocations(ASTNode astNode, String invokedMethodName) {
		setInvokedMethodName( invokedMethodName );
		return getInvocations(astNode);
	}

	private void setInvokedMethodName(String invokedMethodName) {
		this.invokedMethodName = invokedMethodName;
	}
}
