package workingSets.groups;

import java.util.List;
import java.util.stream.Collectors;

import optionalanalyzer.metamodel.entity.MProject;
import optionalanalyzer.metamodel.entity.MRule17sAntipattern;
import optionalanalyzer.metamodel.entity.MWorkingSet;
import ro.lrg.xcore.metametamodel.Group;
import ro.lrg.xcore.metametamodel.IRelationBuilder;
import ro.lrg.xcore.metametamodel.RelationBuilder;

@RelationBuilder
public class Rule17AntipatternDetector implements IRelationBuilder<MRule17sAntipattern, MWorkingSet>{

	@Override
	public Group<MRule17sAntipattern> buildGroup(MWorkingSet arg0) {
		Group<MRule17sAntipattern> group = new Group<>();

		 List<MRule17sAntipattern> antipatterns = arg0.getComponentProjects()
				.getElements().stream()
				.map(MProject::rule17AntipatternDetector)
				.map(Group::getElements)
				.flatMap(List::stream)
				.collect(Collectors.toList());
		 
		 group.addAll(antipatterns);
		 
		 return group;
	}

}
