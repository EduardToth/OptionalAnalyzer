package compilationUnits.groups.rule21AntipatternBuilder;

import java.util.LinkedList;
import java.util.List;

import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.Expression;
import org.eclipse.jdt.core.dom.MethodInvocation;

import optionalanalizer.metamodel.entity.MInvocation;
import optionalanalizer.metamodel.factory.Factory;
import utilities.MethodInvokedFromOptionalVisitor;

public class The21stAntipatternVisitor extends ASTVisitor {

	private boolean itRespects = false;	

	private List<MInvocation> mInvocations = new LinkedList<>();
	@Override
	public boolean visit(MethodInvocation methInvocation) {

		if(methInvocation.getName().toString().equals("equals")
				&& methInvocation.arguments().size() == 1) {
			for(Object obj : methInvocation.arguments()) {
				if(obj instanceof Expression) {
					Expression expr = (Expression) obj;
					if(containsAGetMethodFromAnOptionalInside(expr)) {
						MInvocation mInvocation = Factory.getInstance().createMInvocation(methInvocation);
						mInvocations.add(mInvocation);
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
	
	public List<MInvocation> getMInvocations() {
		return this.mInvocations;
	}
}
