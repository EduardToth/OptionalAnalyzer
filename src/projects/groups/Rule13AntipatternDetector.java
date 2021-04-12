package projects.groups;

import java.util.List;
import java.util.stream.Collectors;

import optionalanalyzer.metamodel.entity.MPackage;
import optionalanalyzer.metamodel.entity.MProject;
import optionalanalyzer.metamodel.entity.MRule13sAntipattern;
import ro.lrg.xcore.metametamodel.Group;
import ro.lrg.xcore.metametamodel.IRelationBuilder;
import ro.lrg.xcore.metametamodel.RelationBuilder;

@RelationBuilder
public class Rule13AntipatternDetector implements IRelationBuilder<MRule13sAntipattern, MProject>{

	@Override
	public Group<MRule13sAntipattern> buildGroup(MProject arg0) {
		Group<MRule13sAntipattern> group = new Group<>();

		 List<MRule13sAntipattern> antipatterns = arg0.packageDetector()
				.getElements().stream()
				.map(MPackage::rule13AntipatternDetector)
				.map(Group::getElements)
				.flatMap(List::stream)
				.collect(Collectors.toList());
		 
		 group.addAll(antipatterns);
		 return group;
	}
}
