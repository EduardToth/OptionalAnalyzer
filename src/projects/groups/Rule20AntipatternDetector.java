package projects.groups;

import java.util.List;
import java.util.stream.Collectors;

import optionalanalyzer.metamodel.entity.MPackage;
import optionalanalyzer.metamodel.entity.MProject;
import optionalanalyzer.metamodel.entity.MRule20sAntipattern;
import ro.lrg.xcore.metametamodel.Group;
import ro.lrg.xcore.metametamodel.IRelationBuilder;
import ro.lrg.xcore.metametamodel.RelationBuilder;

@RelationBuilder
public class Rule20AntipatternDetector implements IRelationBuilder<MRule20sAntipattern, MProject>{

	@Override
	public Group<MRule20sAntipattern> buildGroup(MProject arg0) {
		Group<MRule20sAntipattern> group = new Group<>();

		 List<MRule20sAntipattern> antipatterns = arg0.packageDetector()
				.getElements().stream()
				.map(MPackage::rule20AntipatternDetector)
				.map(Group::getElements)
				.flatMap(List::stream)
				.collect(Collectors.toList());
		 
		 group.addAll(antipatterns);
		 
		 return group;
	}
}