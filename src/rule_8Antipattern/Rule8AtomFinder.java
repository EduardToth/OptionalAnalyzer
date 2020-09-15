package rule_8Antipattern;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.IfStatement;
import org.eclipse.jdt.core.dom.MethodInvocation;

import optionalanalizer.metamodel.entity.MRule8Atom;
import optionalanalizer.metamodel.factory.Factory;
import utilities.OptionalInvocationFinder;
import utilities.baseComponentsForRules3_4_5_6_8_9.Antipattern;
import utilities.baseComponentsForRules3_4_5_6_8_9.AntipatternFinderInIfStatements;

public class Rule8AtomFinder extends AntipatternFinderInIfStatements{


	public List<MRule8Atom> getMAtoms(ASTNode astNode) {
		
		return getAtoms(astNode).stream()
				.map(Rule8Atom::getInstance)
				.filter(Optional::isPresent)
				.map(Optional::get)
				.map(el -> Factory.getInstance().createMRule8Atom(el))
				.collect(Collectors.toList());
	}

	private List<IfStatement> getAtoms(ASTNode astNode) {
		OptionalInvocationFinder optionalInvocationFinder = new OptionalInvocationFinder();
		List<MethodInvocation> invocations = optionalInvocationFinder.getInvocations(astNode);
		return collectAntipatterns(invocations);
	}

	private List<IfStatement> collectAntipatterns(List<MethodInvocation> invocations) {
		return collectAntipatterns(invocations, Antipattern.RULE_8_ANTIPATTERN);
	}
}