package rule21Antipattern;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.stream.Collectors;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.MethodInvocation;

import optionalanalizer.metamodel.entity.MRule21Atom;
import optionalanalizer.metamodel.factory.Factory;
import utilities.Atom;
import utilities.OptionalInvocationFinder;

public class Rule21AtomFinder {

	public List<MRule21Atom> getMAtoms(ASTNode astNode) {
		OptionalInvocationFinder optionalInvocationFinder = new OptionalInvocationFinder();
		List<MethodInvocation> equalsInvocationsFromOptional = optionalInvocationFinder.getInvocations(astNode, "get");

		return equalsInvocationsFromOptional.stream()
				.map(this::getAntipatternOccurencies)
				.flatMap(Collection::stream)
				.map(Rule21Atom::getInstance)
				.filter(Optional::isPresent)
				.map(Optional::get)
				.collect(Collectors.groupingBy(Atom::getStartingPosition))
				.entrySet()
				.stream()
				.map(Entry::getValue)
				.map(myList -> myList.get( 0 ))
				.map(Factory.getInstance()::createMRule21Atom)
				.collect(Collectors.toList());
		
	}

	private List<MethodInvocation> getAntipatternOccurencies(MethodInvocation methodInvocation) {
		String invocationPattern = methodInvocation.toString() + "\\.equals(.*)";

		invocationPattern = normalyzeRegEx(invocationPattern);

		try {
			if(!methodInvocation.getParent().toString().matches(invocationPattern)) {
				return Collections.emptyList();
			}
		}catch( java.util.regex.PatternSyntaxException ex) {
			return Collections.emptyList();
		}
		return getAntipatternOccurencies2(methodInvocation);
	}

	private String normalyzeRegEx(String invocationPattern) {
		String tmp = "";
		for(int i=0; i < invocationPattern.length(); i++) {
			if(invocationPattern.charAt( i ) == '(' 
					|| invocationPattern.charAt( i ) == ')') {
				tmp += "\\" + invocationPattern.charAt( i );
			}
			else {
				tmp += invocationPattern.charAt( i );
			}
		}

		return tmp;
	}

	private List<MethodInvocation> getAntipatternOccurencies2(MethodInvocation methodInvocation) {
		The21stAntipatternVisitor the21stAntipatternVisitor =
				new The21stAntipatternVisitor();
		methodInvocation.getParent().accept(the21stAntipatternVisitor);

		return the21stAntipatternVisitor.getMethodInvocations();
	}
}
