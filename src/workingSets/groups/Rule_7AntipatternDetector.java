package workingSets.groups;

import java.util.List;
import java.util.stream.Collectors;

import optionalanalizer.metamodel.entity.MCompilationUnit;
import optionalanalizer.metamodel.entity.MProject;
import optionalanalizer.metamodel.entity.MRule1sAntipattern;
import optionalanalizer.metamodel.entity.MRule7sAntipattern;
import optionalanalizer.metamodel.entity.MWorkingSet;
import ro.lrg.xcore.metametamodel.Group;
import ro.lrg.xcore.metametamodel.IRelationBuilder;
import ro.lrg.xcore.metametamodel.RelationBuilder;

@RelationBuilder
public class Rule_7AntipatternDetector implements IRelationBuilder<MRule7sAntipattern, MWorkingSet>{

	@Override
	public Group<MRule7sAntipattern> buildGroup(MWorkingSet arg0) {
		Group<MRule7sAntipattern> group = new Group<>();

		 List<MRule7sAntipattern> antipatterns = arg0.getComponentProjects()
				.getElements().stream()
				.map(MProject::rule_7AntipatternDetector)
				.map(Group::getElements)
				.flatMap(List::stream)
				.collect(Collectors.toList());
		 
		 group.addAll(antipatterns);
		 
		 return group;
	}

}
