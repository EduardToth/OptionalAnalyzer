package workingSets.groups;

import java.util.List;
import java.util.stream.Collectors;

import optionalanalyzer.metamodel.entity.MProject;
import optionalanalyzer.metamodel.entity.MRule5sAntipattern;
import optionalanalyzer.metamodel.entity.MWorkingSet;
import ro.lrg.xcore.metametamodel.Group;
import ro.lrg.xcore.metametamodel.IRelationBuilder;
import ro.lrg.xcore.metametamodel.RelationBuilder;

@RelationBuilder
public class Rule_5AntipatternDetector implements IRelationBuilder<MRule5sAntipattern, MWorkingSet>{

	@Override
	public Group<MRule5sAntipattern> buildGroup(MWorkingSet arg0) {
		Group<MRule5sAntipattern> group = new Group<>();

		 List<MRule5sAntipattern> antipatterns = arg0.getComponentProjects()
				.getElements().stream()
				.map(MProject::rule_5AntipatternDetector)
				.map(Group::getElements)
				.flatMap(List::stream)
				.collect(Collectors.toList());
		 
		 group.addAll(antipatterns);
		 
		 return group;
	}

}