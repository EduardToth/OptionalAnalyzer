package compilationUnits.groups.rule21AntipatternBuilder;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.jdt.core.dom.MethodInvocation;
import org.javatuples.Pair;
import org.javatuples.Unit;
import compilationUnits.groups.OptionalInvocationsBuilder;
import optionalanalizer.metamodel.entity.MCompilationUnit;
import optionalanalizer.metamodel.entity.MInvocation;
import ro.lrg.xcore.metametamodel.Group;
import ro.lrg.xcore.metametamodel.IRelationBuilder;
import ro.lrg.xcore.metametamodel.RelationBuilder;

@RelationBuilder
public class Rule21AntipatternBuilder implements IRelationBuilder<MInvocation, MCompilationUnit>{

	@Override
	public Group<MInvocation> buildGroup(MCompilationUnit arg0) {
		OptionalInvocationsBuilder optionalInvocationsBuilder = new OptionalInvocationsBuilder();
		Group<MInvocation> equalsInvocationFromOptionalGroup = optionalInvocationsBuilder.buildGroup(arg0, "get");

		Group<MInvocation> group = new Group<>();
		equalsInvocationFromOptionalGroup.getElements().stream()
		.map(this::getAntipatternOccurencies)
		.forEach(mInvocations -> group.addAll(mInvocations));
		//return this.removeDuplicates(group);
		return group;
	}

	private List<MInvocation> getAntipatternOccurencies(MInvocation mInvocation) {
		MethodInvocation methodInvocation = (MethodInvocation) mInvocation.getUnderlyingObject();
		String invocationPattern = methodInvocation.toString() + ".equals(.*)";

		invocationPattern = normalyzeRegEx(invocationPattern);

		if(!methodInvocation.getParent().toString().matches(invocationPattern)) {
			return Collections.emptyList();
		}

		return getAntipatternOccurencies(methodInvocation);
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

	private List<MInvocation> getAntipatternOccurencies(MethodInvocation methodInvocation) {
		The21stAntipatternVisitor the21stAntipatternVisitor =
				new The21stAntipatternVisitor();
		methodInvocation.getParent().accept(the21stAntipatternVisitor);

		return the21stAntipatternVisitor.getMInvocations();
	}

	private  Group<MInvocation> removeDuplicates(Group<MInvocation> group) {
		Map<Pair<Integer, Integer>, MInvocation> myMap = new HashMap<>();
		Unit<MInvocation> currentMInvocation = new Unit<>(null);

		group.getElements().stream()
		.peek(mInv -> currentMInvocation.setAt0(mInv))
		.map(this::getDimentions)
		.filter(pair -> !myMap.containsKey(pair))
		.forEach(pair -> myMap.put(pair, currentMInvocation.getValue0()));

		Group<MInvocation> mInvocationGroup = new Group<>();
		mInvocationGroup.addAll(myMap.values());
		return mInvocationGroup;
	}

	private Pair<Integer, Integer> getDimentions(MInvocation mInvocation) {
		MethodInvocation methodInvocation = (MethodInvocation) mInvocation.getUnderlyingObject();
		int startPosition = methodInvocation.getStartPosition();
		int endPosition = methodInvocation.getLength();

		return new Pair<Integer, Integer>(startPosition, endPosition);
	}
}