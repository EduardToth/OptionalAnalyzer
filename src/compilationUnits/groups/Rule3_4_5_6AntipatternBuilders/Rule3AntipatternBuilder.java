package compilationUnits.groups.Rule3_4_5_6AntipatternBuilders;

import java.util.Optional;

import org.eclipse.jdt.core.dom.IfStatement;
import org.eclipse.jdt.core.dom.MethodInvocation;

import compilationUnits.groups.OptionalInvocationsBuilder;
import compilationUnits.groups.Rule3_4_5_6AntipatternBuilders.baseComponents.Antipattern;
import compilationUnits.groups.Rule3_4_5_6AntipatternBuilders.baseComponents.AntipatternFinderInIfStatements;
import optionalanalizer.metamodel.entity.MCompilationUnit;
import optionalanalizer.metamodel.entity.MIfStatement;
import optionalanalizer.metamodel.entity.MInvocation;
import optionalanalizer.metamodel.factory.Factory;
import ro.lrg.xcore.metametamodel.Group;
import ro.lrg.xcore.metametamodel.IRelationBuilder;
import ro.lrg.xcore.metametamodel.RelationBuilder;
import utilities.Unit;
import utilities.UtilityClass;

@RelationBuilder
public class Rule3AntipatternBuilder extends AntipatternFinderInIfStatements implements IRelationBuilder<MIfStatement, MCompilationUnit>{

	@Override
	public Group<MIfStatement> buildGroup(MCompilationUnit arg0) {
		Group<MInvocation> isPresentInvocationGroup = arg0.optionalInvocationsBuilder();
		return collectRule3Antipatterns(isPresentInvocationGroup);
	}

	private Group<MIfStatement> collectRule3Antipatterns(Group<MInvocation> isPresentGroup) {

		Unit<Optional<String>> invocatorName = new Unit<>(Optional.empty());
		Group<MIfStatement> mIfStatementGroup = new Group<>();
		
		isPresentGroup.getElements()
		.stream()
		.map(this::convertToMethodInvocation)
		.peek(inv -> setInvocatorName(inv, invocatorName))
		.filter((inv) -> invocatorName.getValue0().isPresent())
		.filter(this::isParentIfStatement)
		.map(inv -> (IfStatement)inv.getParent())
		.filter(ifStatement -> isAntipattern(Antipattern.RULE_3_ANTIPATTERN, ifStatement, invocatorName.getValue0().get()))
		.map(this::convertToMIfStatement)
		.forEach(mIfStatement -> mIfStatementGroup.add(mIfStatement));

		return mIfStatementGroup;
	}


}
