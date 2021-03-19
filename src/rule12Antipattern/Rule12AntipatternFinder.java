package rule12Antipattern;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.MethodInvocation;

import optionalanalyzer.metamodel.entity.MRule12sAntipattern;
import optionalanalyzer.metamodel.factory.Factory;
import utilities.OptionalInvocationFinder;

public class Rule12AntipatternFinder {

	private final String matchingRegEx = ".*\\.ofNullable\\(.*\\)\\.(orElse\\(.*\\)|orElseGet\\(.*\\))";

	public List<MRule12sAntipattern> getMAntipatterns(ASTNode astNode) {
		OptionalInvocationFinder optionalInvocationFinder = new OptionalInvocationFinder();
		List<MethodInvocation> ofNullableList = optionalInvocationFinder.getInvocations(astNode, "ofNullable");
		
		return ofNullableList.stream()
				.filter(this::respectsThePattern)
				.map(MethodInvocation::getParent)
				.filter(MethodInvocation.class::isInstance)
				.map(Rule12Antipattern::getInstance)
				.flatMap(Optional::stream)
				.map(Factory.getInstance()::createMRule12sAntipattern)
				.collect(Collectors.toList());
		
	}

	private boolean respectsThePattern(MethodInvocation methodInvocation) {
		return methodInvocation.getParent()
				.toString()
				.matches(matchingRegEx);
	}
}
