package rule_7Antipattern;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.IMethodBinding;
import org.eclipse.jdt.core.dom.IfStatement;
import org.eclipse.jdt.core.dom.MethodInvocation;
import org.eclipse.jdt.core.dom.Statement;

import optionalanalizer.metamodel.entity.MRule7Atom;
import optionalanalizer.metamodel.factory.Factory;
import utilities.OptionalInvocationFinder;
import utilities.ToolBoxForIfStatementAnalysis;
import utilities.UtilityClass;

public class Rule7AtomFinder{

	private MethodInvocation firstMethodInvocationForThen = null;
	private MethodInvocation firstMethodInvocationForElse = null;

	public List<MRule7Atom> getMAtoms(ASTNode astNode) {
		OptionalInvocationFinder optionalInvocationFinder = new OptionalInvocationFinder();
		List<MethodInvocation> isPresentInvocationGroup = optionalInvocationFinder.getInvocations(astNode);
		return collectRule7Antipatterns(isPresentInvocationGroup);
	}

	private List<MRule7Atom> collectRule7Antipatterns(List<MethodInvocation> isPresentList) {

		List<MRule7Atom> mIfStatements = isPresentList.stream()
				.filter(ToolBoxForIfStatementAnalysis::isSuperParentIfStatement)
				.map(ToolBoxForIfStatementAnalysis::getIfStatement)
				.filter(ifStm -> isThereSameMethodInvokedInThenElseBlocks(ifStm))
				.filter(ifStm -> isContextTheSame(ifStm))
				.map(Rule7Atom::getInstance)
				.filter(Optional::isPresent)
				.map(Optional::get)
				.map(el -> Factory.getInstance().createMRule7Atom(el))
				.collect(Collectors.toList());

		return mIfStatements;
	}

	private void setMethodInvocations(MethodInvocation methodInvocationForThen, MethodInvocation methodInvocationForElse) {
			this.firstMethodInvocationForThen = methodInvocationForThen;
			this.firstMethodInvocationForElse = methodInvocationForElse;
	}

	private boolean isContextTheSame(IfStatement ifStatement) {
		if(this.firstMethodInvocationForThen == null) {
			return false;
		}
		Statement thenStatement = ifStatement.getThenStatement();
		Statement elseStatement = ifStatement.getElseStatement();
		
		return ToolBoxForIfStatementAnalysis.isSameContext(thenStatement, firstMethodInvocationForThen, elseStatement, firstMethodInvocationForElse);
	}

	private boolean isThereSameMethodInvokedInThenElseBlocks(IfStatement ifStatement) {
		Statement thenStatement = ifStatement.getThenStatement();
		Statement elseStatement = ifStatement.getElseStatement();

		if(elseStatement == null) {
			return false;
		}
		
		final List<MethodInvocation> methodInvocationsForTheStatement = getMethodInvocationFromStatement(thenStatement);
		final List<MethodInvocation> methodInvocationsForElseStatement = getMethodInvocationFromStatement(elseStatement);

		return methodInvocationsForElseStatement.stream()
				.filter(inv -> doesListContainMethodInvocation(methodInvocationsForTheStatement, inv))
				.findFirst()
				.isPresent();
	}

	private boolean doesListContainMethodInvocation(final List<MethodInvocation> methodInvocations, MethodInvocation methodInvocation) {
		return methodInvocations.stream()
				.filter(inv -> areInvokedMethodsTheSame(inv, methodInvocation))
				.peek(inv -> setMethodInvocations(inv, methodInvocation))
				.findAny()
				.isPresent();
	}

	private List<MethodInvocation> getMethodInvocationFromStatement(Statement statement) {
		final List<MethodInvocation> methodInvocations = new LinkedList<>();

		statement.accept(new ASTVisitor() {

			@Override
			public boolean visit(MethodInvocation methodInvocation) {
				methodInvocations.add(methodInvocation);
				return super.visit(methodInvocation);
			}
		});
		return methodInvocations;
	}

	private boolean areInvokedMethodsTheSame(MethodInvocation methodInvocation1, MethodInvocation methodInvocation2) {
		return areThereSameBindings(methodInvocation1, methodInvocation2) &&
				areThereSameInvocators(methodInvocation1, methodInvocation2);
	}

	private boolean areThereSameInvocators(MethodInvocation methodInvocation1, MethodInvocation methodInvocation2) {
		Optional<String> invocatorName1 = UtilityClass.getInvocatorName(methodInvocation1);
		Optional<String> invocatorName2 = UtilityClass.getInvocatorName(methodInvocation2);

		return invocatorName1.equals(invocatorName2);
	}

	private boolean areThereSameBindings(MethodInvocation methodInvocation1, MethodInvocation methodInvocation2) {
		IMethodBinding bindings1 = methodInvocation1.resolveMethodBinding();
		IMethodBinding bindings2 = methodInvocation2.resolveMethodBinding();

		return bindings1.equals(bindings2);
	}

}