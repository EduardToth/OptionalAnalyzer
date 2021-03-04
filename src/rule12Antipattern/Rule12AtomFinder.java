package rule12Antipattern;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.MethodInvocation;

import optionalanalizer.metamodel.entity.MRule12Atom;
import optionalanalizer.metamodel.factory.Factory;
import utilities.OptionalInvocationFinder;

public class Rule12AtomFinder {

	private final String matchingRegEx = ".*\\.ofNullable\\(.*\\)\\.(orElse\\(.*\\)|orElseGet\\(.*\\))";

	public List<MRule12Atom> getMAtoms(ASTNode astNode) {
		OptionalInvocationFinder optionalInvocationFinder = new OptionalInvocationFinder();
		List<MethodInvocation> ofNullableList = optionalInvocationFinder.getInvocations(astNode, "ofNullable");
		
		return ofNullableList.stream()
				.filter(this::respectsThePattern)
				.map(MethodInvocation::getParent)
				.filter(MethodInvocation.class::isInstance)
				.map(Rule12Atom::getInstance)
				.filter(Optional::isPresent)
				.map(Optional::get)
				.map(Factory.getInstance()::createMRule12Atom)
				.collect(Collectors.toList());
		
	}

	private boolean respectsThePattern(MethodInvocation methodInvocation) {
		return methodInvocation.getParent()
				.toString()
				.matches(matchingRegEx);
	}
}
