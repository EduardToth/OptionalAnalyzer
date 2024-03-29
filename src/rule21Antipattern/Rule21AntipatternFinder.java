package rule21Antipattern;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.regex.PatternSyntaxException;
import java.util.Optional;
import java.util.stream.Collectors;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.MethodInvocation;

import optionalanalyzer.metamodel.entity.MRule21sAntipattern;
import optionalanalyzer.metamodel.factory.Factory;
import utilities.Antipattern;
import utilities.OptionalInvocationFinder;

public class Rule21AntipatternFinder {

	public List<MRule21sAntipattern> getMAntipatterns(ASTNode astNode) {
		OptionalInvocationFinder optionalInvocationFinder = new OptionalInvocationFinder();
		List<MethodInvocation> equalsInvocationsFromOptional = optionalInvocationFinder.getInvocations(astNode, "get");
		Collection<Rule21Antipattern> uniqueAntipatternCollection = getUniqueAntipatternCollection(equalsInvocationsFromOptional);
		
		return uniqueAntipatternCollection.stream()
				.map(Factory.getInstance()::createMRule21sAntipattern)
				.collect(Collectors.toList());

	}

	private Collection<Rule21Antipattern> getUniqueAntipatternCollection(
			List<MethodInvocation> equalsInvocationsFromOptional) {
		return equalsInvocationsFromOptional.stream()
				.map(this::getAntipatternOccurencies)
				.flatMap(Collection::stream)
				.map(Rule21Antipattern::getInstance)
				.flatMap(Optional::stream)
				.collect(Collectors.toMap(Antipattern::getStartingPosition, Function.identity(), (existing, newOne) -> existing))
				.values();
	}

	private List<MethodInvocation> getAntipatternOccurencies(MethodInvocation methodInvocation) {
		String invocationPattern = methodInvocation.toString() + "\\.equals(.*)";

		invocationPattern = normalyzeRegEx(invocationPattern);

		try {
			if(!methodInvocation.getParent().toString().matches(invocationPattern)) {
				return Collections.emptyList();
			}
		}catch(PatternSyntaxException ex) {
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
