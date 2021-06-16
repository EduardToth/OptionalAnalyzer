package workingSets.groups;

import java.util.List;
import java.util.stream.Collectors;

import optionalanalyzer.metamodel.entity.MProject;
import optionalanalyzer.metamodel.entity.MRule21sAntipattern;
import optionalanalyzer.metamodel.entity.MWorkingSet;
import ro.lrg.xcore.metametamodel.Group;
import ro.lrg.xcore.metametamodel.IRelationBuilder;
import ro.lrg.xcore.metametamodel.RelationBuilder;

@RelationBuilder
public class Rule21AntipatternDetector implements IRelationBuilder<MRule21sAntipattern, MWorkingSet>{

	@Override
	public Group<MRule21sAntipattern> buildGroup(MWorkingSet arg0) {
		Group<MRule21sAntipattern> group = new Group<>();

		 List<MRule21sAntipattern> antipatterns = arg0.getComponentProjects()
				.getElements()
				.stream()
				.map(MProject::rule21AntipatternDetector)
				.map(Group::getElements)
				.flatMap(List::stream)
				.collect(Collectors.toList());
		 
		 group.addAll(antipatterns);
		 
		 return group;
	}

}