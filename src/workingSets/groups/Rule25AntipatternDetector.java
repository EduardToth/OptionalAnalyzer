package workingSets.groups;

import java.util.List;
import java.util.stream.Collectors;

import optionalanalyzer.metamodel.entity.MProject;
import optionalanalyzer.metamodel.entity.MRule25sAntipattern;
import optionalanalyzer.metamodel.entity.MWorkingSet;
import ro.lrg.xcore.metametamodel.Group;
import ro.lrg.xcore.metametamodel.IRelationBuilder;
import ro.lrg.xcore.metametamodel.RelationBuilder;

@RelationBuilder
public class Rule25AntipatternDetector implements IRelationBuilder<MRule25sAntipattern, MWorkingSet>{

	@Override
	public Group<MRule25sAntipattern> buildGroup(MWorkingSet arg0) {
		Group<MRule25sAntipattern> group = new Group<>();

		 List<MRule25sAntipattern> antipatterns = arg0.getComponentProjects()
				.getElements()
				.stream()
				.map(MProject::rule25AntipatternDetector)
				.map(Group::getElements)
				.flatMap(List::stream)
				.collect(Collectors.toList());
		 
		 group.addAll(antipatterns);
		 
		 return group;
	}

}