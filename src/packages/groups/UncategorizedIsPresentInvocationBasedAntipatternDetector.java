package packages.groups;

import java.util.List;
import java.util.stream.Collectors;

import optionalanalizer.metamodel.entity.MCompilationUnit;
import optionalanalizer.metamodel.entity.MPackage;
import optionalanalizer.metamodel.entity.MUncategorizedIsPresentPossibleAntipattern;
import ro.lrg.xcore.metametamodel.Group;
import ro.lrg.xcore.metametamodel.IRelationBuilder;
import ro.lrg.xcore.metametamodel.RelationBuilder;

@RelationBuilder
public class UncategorizedIsPresentInvocationBasedAntipatternDetector 
implements IRelationBuilder<MUncategorizedIsPresentPossibleAntipattern, MPackage>{

	@Override
	public Group<MUncategorizedIsPresentPossibleAntipattern> buildGroup(MPackage arg0) {
		Group<MUncategorizedIsPresentPossibleAntipattern> group = new Group<>();

		List<MUncategorizedIsPresentPossibleAntipattern> antipatterns = arg0.compilationUnitDetector()
				.getElements().stream()
				.map(MCompilationUnit::uncategorizedIsPresentInvocationBasedAntipatternDetector)
				.map(Group::getElements)
				.flatMap(List::stream)
				.collect(Collectors.toList());

		group.addAll(antipatterns);

		return group;
	}
}