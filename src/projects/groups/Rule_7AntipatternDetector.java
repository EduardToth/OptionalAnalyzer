package projects.groups;

import java.util.List;
import java.util.stream.Collectors;

import optionalanalyzer.metamodel.entity.MCompilationUnit;
import optionalanalyzer.metamodel.entity.MProject;
import optionalanalyzer.metamodel.entity.MRule7sAntipattern;
import ro.lrg.xcore.metametamodel.Group;
import ro.lrg.xcore.metametamodel.IRelationBuilder;
import ro.lrg.xcore.metametamodel.RelationBuilder;

@RelationBuilder
public class Rule_7AntipatternDetector implements IRelationBuilder<MRule7sAntipattern, MProject>{

	@Override
	public Group<MRule7sAntipattern> buildGroup(MProject arg0) {
		Group<MRule7sAntipattern> group = new Group<>();

		 List<MRule7sAntipattern> antipatterns = arg0.compilationUnitDetector()
				.getElements().stream()
				.map(MCompilationUnit::rule_7AntipatternDetector)
				.map(Group::getElements)
				.flatMap(List::stream)
				.collect(Collectors.toList());
		 
		 group.addAll(antipatterns);
		 
		 return group;
	}
}
