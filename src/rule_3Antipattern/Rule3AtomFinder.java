package rule_3Antipattern;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.IfStatement;
import org.eclipse.jdt.core.dom.MethodInvocation;

import optionalanalizer.metamodel.entity.MRule3Atom;
import optionalanalizer.metamodel.factory.Factory;
import utilities.OptionalInvocationFinder;
import utilities.baseComponentsForRules3_4_5_6_8_9.Antipattern;
import utilities.baseComponentsForRules3_4_5_6_8_9.AntipatternFinderInIfStatements;

public class Rule3AtomFinder extends AntipatternFinderInIfStatements{

	public List<MRule3Atom> getMAtoms(ASTNode astNode) {
		return getAtoms(astNode)
				.stream()
				.map(Rule3Atom::getInstance)
				.filter(Optional::isPresent)
				.map(Optional::get)
				.map(el -> Factory.getInstance().createMRule3Atom(el))
				.collect(Collectors.toList());
	}

	private List<IfStatement> getAtoms(ASTNode astNode) {
		OptionalInvocationFinder optionalInvocationFinder = new OptionalInvocationFinder();
		List<MethodInvocation> invocations = optionalInvocationFinder.getInvocations(astNode);
		return collectRule3Antipatterns(invocations);
	}

	private List<IfStatement> collectRule3Antipatterns(List<MethodInvocation> invocations) {
		return collectAntipatterns(invocations, Antipattern.RULE_3_ANTIPATTERN);
	}


}
