package workingSets.groups;


import java.util.List;
import java.util.stream.Collectors;

import optionalanalyzer.metamodel.entity.MProject;
import optionalanalyzer.metamodel.entity.MRule12sAntipattern;
import optionalanalyzer.metamodel.entity.MWorkingSet;
import ro.lrg.xcore.metametamodel.Group;
import ro.lrg.xcore.metametamodel.IRelationBuilder;
import ro.lrg.xcore.metametamodel.RelationBuilder;

@RelationBuilder
public class Rule12AntipatternDetector implements IRelationBuilder<MRule12sAntipattern, MWorkingSet>{

	@Override
	public Group<MRule12sAntipattern> buildGroup(MWorkingSet arg0) {
		Group<MRule12sAntipattern> group = new Group<>();

		 List<MRule12sAntipattern> antipatterns = arg0.getComponentProjects()
				.getElements().stream()
				.map(MProject::rule12AntipatternDetector)
				.map(Group::getElements)
				.flatMap(List::stream)
				.collect(Collectors.toList());
		 
		 group.addAll(antipatterns);
		 
		 return group;
	}

}