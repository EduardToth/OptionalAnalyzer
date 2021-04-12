package projects.groups;

import java.util.List;
import java.util.stream.Collectors;


import optionalanalyzer.metamodel.entity.MPackage;
import optionalanalyzer.metamodel.entity.MProject;
import optionalanalyzer.metamodel.entity.MRule4sAntipattern;
import ro.lrg.xcore.metametamodel.Group;
import ro.lrg.xcore.metametamodel.IRelationBuilder;
import ro.lrg.xcore.metametamodel.RelationBuilder;

@RelationBuilder
public class Rule_4AntipatternDetector implements IRelationBuilder<MRule4sAntipattern, MProject>{

	@Override
	public Group<MRule4sAntipattern> buildGroup(MProject arg0) {
		Group<MRule4sAntipattern> group = new Group<>();

		 List<MRule4sAntipattern> antipatterns = arg0.packageDetector()
				.getElements().stream()
				.map(MPackage::rule_4AntipatternDetector)
				.map(Group::getElements)
				.flatMap(List::stream)
				.collect(Collectors.toList());
		 
		 group.addAll(antipatterns);
		 
		 return group;
	}
}