package rule12Antipattern;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.MethodInvocation;

import optionalanalizer.metamodel.entity.MRule12Atom;
import optionalanalizer.metamodel.factory.Factory;
import utilities.OptionalInvocationFinder;

public class Rule12AtomFinder {

	private final String matchingRegEx = ".*\\.ofNullable\\(.*\\)\\.(orElse\\(.*\\)|orElseGet\\(.*\\))";

	public List<MRule12Atom> getMAtoms(ASTNode astNode) {
		OptionalInvocationFinder optionalInvocationFinder = new OptionalInvocationFinder();
		List<MethodInvocation> ofNullableList = optionalInvocationFinder.getInvocations(astNode, "ofNullable");
		List<MRule12Atom> antipatternList = ofNullableList.stream()
				.filter(this::respectsThePattern)
				.map(methodInvocation -> methodInvocation.getParent())
				.filter(el -> el instanceof MethodInvocation)
				.map(Rule12Atom::getInstance)
				.filter(Optional::isPresent)
				.map(Optional::get)
				.map(el -> Factory.getInstance().createMRule12Atom(el))
				.collect(Collectors.toList());
		
		return antipatternList;
	}

	private boolean respectsThePattern(MethodInvocation methodInvocation) {
		return methodInvocation.getParent().toString().matches(matchingRegEx);
	}
}
