package projects.groups;


import java.util.List;
import java.util.stream.Collectors;

import optionalanalyzer.metamodel.entity.MCompilationUnit;
import optionalanalyzer.metamodel.entity.MProject;
import optionalanalyzer.metamodel.entity.MRule12sAntipattern;
import ro.lrg.xcore.metametamodel.Group;
import ro.lrg.xcore.metametamodel.IRelationBuilder;
import ro.lrg.xcore.metametamodel.RelationBuilder;

@RelationBuilder
public class Rule12AntipatternDetector implements IRelationBuilder<MRule12sAntipattern, MProject>{

	@Override
	public Group<MRule12sAntipattern> buildGroup(MProject arg0) {
		Group<MRule12sAntipattern> group = new Group<>();

		 List<MRule12sAntipattern> antipatterns = arg0.compilationUnitDetector()
				.getElements().stream()
				.map(MCompilationUnit::rule12AntipatternDetector)
				.map(Group::getElements)
				.flatMap(List::stream)
				.collect(Collectors.toList());
		 
		 group.addAll(antipatterns);
		 
		 return group;
	}
}