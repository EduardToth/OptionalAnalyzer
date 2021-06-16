package projects.groups;

import java.util.List;
import java.util.stream.Collectors;

import optionalanalyzer.metamodel.entity.MCompilationUnit;
import optionalanalyzer.metamodel.entity.MPackage;
import optionalanalyzer.metamodel.entity.MProject;
import optionalanalyzer.metamodel.entity.MRule1sAntipattern;
import ro.lrg.xcore.metametamodel.Group;
import ro.lrg.xcore.metametamodel.IRelationBuilder;
import ro.lrg.xcore.metametamodel.RelationBuilder;

@RelationBuilder
public class Rule_1AntipatternDetector implements IRelationBuilder<MRule1sAntipattern, MProject>{

	@Override
	public Group<MRule1sAntipattern> buildGroup(MProject arg0) {
		Group<MRule1sAntipattern> group = new Group<>();

		 List<MRule1sAntipattern> antipatterns = arg0.compilationUnitDetector()
				.getElements().parallelStream()
				.unordered()
				.map(MCompilationUnit::rule_1AntipatternDetector)
				.map(Group::getElements)
				.flatMap(List::stream)
				.collect(Collectors.toList());
		 
		 group.addAll(antipatterns);
		 
		 return group;
	}

}
