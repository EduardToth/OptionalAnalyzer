package workingSets.groups;

import java.util.List;
import java.util.stream.Collectors;

import optionalanalizer.metamodel.entity.MProject;
import optionalanalizer.metamodel.entity.MUncategorizedIsPresentPossibleAntipattern;
import optionalanalizer.metamodel.entity.MWorkingSet;
import ro.lrg.xcore.metametamodel.Group;
import ro.lrg.xcore.metametamodel.IRelationBuilder;
import ro.lrg.xcore.metametamodel.RelationBuilder;

@RelationBuilder
public class UncategorizedIsPresentInvocationBasedAntipatternDetector 
implements IRelationBuilder<MUncategorizedIsPresentPossibleAntipattern, MWorkingSet>{

	@Override
	public Group<MUncategorizedIsPresentPossibleAntipattern> buildGroup(MWorkingSet arg0) {
		Group<MUncategorizedIsPresentPossibleAntipattern> group = new Group<>();

		 List<MUncategorizedIsPresentPossibleAntipattern> antipatterns = arg0.getComponentProjects()
				.getElements()
				.stream()
				.map(MProject::uncategorizedIsPresentInvocationBasedAntipatternDetector)
				.map(Group::getElements)
				.flatMap(List::stream)
				.collect(Collectors.toList());
		 
		 group.addAll(antipatterns);
		 
		 return group;
	}
}