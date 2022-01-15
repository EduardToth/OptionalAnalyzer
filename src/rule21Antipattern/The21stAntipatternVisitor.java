package rule21Antipattern;

import java.util.LinkedList;
import java.util.List;

import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.Expression;
import org.eclipse.jdt.core.dom.MethodInvocation;

import utilities.MethodInvokedFromOptionalVisitor;

public class The21stAntipatternVisitor extends ASTVisitor {

	private boolean antipatternFound = false;	

	private List<MethodInvocation> methodInvocations = new LinkedList<>();
	@Override
	public boolean visit(MethodInvocation methInvocation) {
		
		if(isTheEqualsMethodInvoked(methInvocation)) {
			List<?> arguments = methInvocation.arguments();
			
			arguments.stream()
				.filter(Expression.class::isInstance)
				.map(Expression.class::cast)
				.filter(The21stAntipatternVisitor.this::containsAGetMethodFromAnOptionalInside)
				.findFirst()
				.ifPresent(ignored -> setObjectStateToAntipatternFound(methInvocation));
		}
		
		return super.visit(methInvocation);
	}

	private boolean isTheEqualsMethodInvoked(MethodInvocation methInvocation) {
		return methInvocation.getName()
				.toString()
				.equals("equals") &&
				methInvocation.arguments().size() == 1;
	}
	
	private void setObjectStateToAntipatternFound(MethodInvocation methodInvocation) {
		antipatternFound = true;
		methodInvocations.add(methodInvocation);
	}
	

	private boolean containsAGetMethodFromAnOptionalInside(Expression expression) {

		var methodInvokedFromOptionalVisitor = new MethodInvokedFromOptionalVisitor("get");
		expression.accept(methodInvokedFromOptionalVisitor);

		return methodInvokedFromOptionalVisitor.getInvocations().size() != 0;
	}

	public boolean itRespectedTheAntipatter() {
		return this.antipatternFound;
	}

	public List<MethodInvocation> getMethodInvocations() {
		return this.methodInvocations;
	}
}
