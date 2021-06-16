package workingSets.groups;

import java.util.List;
import java.util.stream.Collectors;

import optionalanalyzer.metamodel.entity.MProject;
import optionalanalyzer.metamodel.entity.MRule10sAntipattern;
import optionalanalyzer.metamodel.entity.MWorkingSet;
import ro.lrg.xcore.metametamodel.Group;
import ro.lrg.xcore.metametamodel.IRelationBuilder;
import ro.lrg.xcore.metametamodel.RelationBuilder;

@RelationBuilder
public class Rule10AntipatternDetector implements IRelationBuilder<MRule10sAntipattern, MWorkingSet>{

	@Override
	public Group<MRule10sAntipattern> buildGroup(MWorkingSet arg0) {
		Group<MRule10sAntipattern> group = new Group<>();

		 List<MRule10sAntipattern> antipatterns = arg0.getComponentProjects()
				.getElements()
				.stream()
				.map(MProject::rule10AntipatternDetector)
				.map(Group::getElements)
				.flatMap(List::stream)
				.collect(Collectors.toList());
		 
		 group.addAll(antipatterns);
		 
		 return group;
	}
}
