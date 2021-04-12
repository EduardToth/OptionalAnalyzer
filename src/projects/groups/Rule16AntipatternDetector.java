package projects.groups;

import java.util.List;
import java.util.stream.Collectors;

import optionalanalyzer.metamodel.entity.MPackage;
import optionalanalyzer.metamodel.entity.MProject;
import optionalanalyzer.metamodel.entity.MRule16sAntipattern;
import ro.lrg.xcore.metametamodel.Group;
import ro.lrg.xcore.metametamodel.IRelationBuilder;
import ro.lrg.xcore.metametamodel.RelationBuilder;

@RelationBuilder
public class Rule16AntipatternDetector implements IRelationBuilder<MRule16sAntipattern, MProject>{

	@Override
	public Group<MRule16sAntipattern> buildGroup(MProject arg0) {
		Group<MRule16sAntipattern> group = new Group<>();

		 List<MRule16sAntipattern> antipatterns = arg0.packageDetector()
				.getElements().stream()
				.map(MPackage::rule16AntipatternDetector)
				.map(Group::getElements)
				.flatMap(List::stream)
				.collect(Collectors.toList());
		 
		 group.addAll(antipatterns);
		 
		 return group;
	}
}