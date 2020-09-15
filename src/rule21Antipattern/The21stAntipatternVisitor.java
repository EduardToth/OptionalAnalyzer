package rule21Antipattern;

import java.util.LinkedList;
import java.util.List;

import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.Expression;
import org.eclipse.jdt.core.dom.MethodInvocation;

import utilities.MethodInvokedFromOptionalVisitor;

public class The21stAntipatternVisitor extends ASTVisitor {

	private boolean itRespects = false;	

	private List<MethodInvocation> methodInvocations = new LinkedList<>();
	@Override
	public boolean visit(MethodInvocation methInvocation) {

		if(methInvocation.getName().toString().equals("equals")
				&& methInvocation.arguments().size() == 1) {
			for(Object obj : methInvocation.arguments()) {
				if(obj instanceof Expression) {
					Expression expr = (Expression) obj;
					if(containsAGetMethodFromAnOptionalInside(expr)) {
						methodInvocations.add(methInvocation);
						this.itRespects = true;
					}
				}
			}
		}
		return super.visit(methInvocation);
	}
	
	private boolean containsAGetMethodFromAnOptionalInside(Expression expression) {
		
		MethodInvokedFromOptionalVisitor methodInvokedFromOptionalVisitor = 
				new MethodInvokedFromOptionalVisitor("get");
		expression.accept(methodInvokedFromOptionalVisitor);
		
		return methodInvokedFromOptionalVisitor.getInvocations().size() != 0;
	}
	
	public boolean itRespectedTheAntipatter() {
		return this.itRespects;
	}
	
	public List<MethodInvocation> getMethodInvocations() {
		return this.methodInvocations;
	}
}
