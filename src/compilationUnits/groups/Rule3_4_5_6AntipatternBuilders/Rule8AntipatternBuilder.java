package compilationUnits.groups.Rule3_4_5_6AntipatternBuilders;

import java.util.Optional;

import org.eclipse.jdt.core.dom.IfStatement;

import compilationUnits.groups.Rule3_4_5_6AntipatternBuilders.baseComponents.Antipattern;
import compilationUnits.groups.Rule3_4_5_6AntipatternBuilders.baseComponents.AntipatternFinderInIfStatements;
import optionalanalizer.metamodel.entity.MCompilationUnit;
import optionalanalizer.metamodel.entity.MIfStatement;
import optionalanalizer.metamodel.entity.MInvocation;
import ro.lrg.xcore.metametamodel.Group;
import ro.lrg.xcore.metametamodel.IRelationBuilder;
import ro.lrg.xcore.metametamodel.RelationBuilder;
import utilities.Unit;

@RelationBuilder
public class Rule8AntipatternBuilder extends AntipatternFinderInIfStatements implements IRelationBuilder<MIfStatement, MCompilationUnit>{

	@Override
	public Group<MIfStatement> buildGroup(MCompilationUnit arg0) {
		Group<MInvocation> isPresentInvocationGroup = arg0.optionalInvocationsBuilder();
		return collectRule6Antipatterns(isPresentInvocationGroup);
	}
	
	private Group<MIfStatement> collectRule6Antipatterns(Group<MInvocation> isPresentGroup) {

		Unit<Optional<String>> invocatorName = new Unit<>(Optional.empty());
		Group<MIfStatement> mIfStatementGroup = new Group<>();
		
		isPresentGroup.getElements()
		.stream()
		.map(this::convertToMethodInvocation)
		.peek(inv -> setInvocatorName(inv, invocatorName))
		.filter(this::isParentIfStatement)
		.filter((inv) -> invocatorName.getValue0().isPresent())
		.map(inv -> (IfStatement)inv.getParent())
		.filter(ifStatement -> isAntipattern(Antipattern.RULE_8_ANTIPATTERN, ifStatement, invocatorName.getValue0().get()))
		.map(this::convertToMIfStatement)
		.forEach(mIfStatement -> mIfStatementGroup.add(mIfStatement));

		return mIfStatementGroup;
	}
}