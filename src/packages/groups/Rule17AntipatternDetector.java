package packages.groups;

import java.util.List;
import java.util.stream.Collectors;

import optionalanalyzer.metamodel.entity.MCompilationUnit;
import optionalanalyzer.metamodel.entity.MPackage;
import optionalanalyzer.metamodel.entity.MRule17sAntipattern;
import ro.lrg.xcore.metametamodel.Group;
import ro.lrg.xcore.metametamodel.IRelationBuilder;
import ro.lrg.xcore.metametamodel.RelationBuilder;

@RelationBuilder
public class Rule17AntipatternDetector implements IRelationBuilder<MRule17sAntipattern, MPackage>{

	@Override
	public Group<MRule17sAntipattern> buildGroup(MPackage arg0) {
		Group<MRule17sAntipattern> group = new Group<>();

		 List<MRule17sAntipattern> antipatterns = arg0.compilationUnitDetector()
				.getElements().parallelStream()
				.unordered()
				.map(MCompilationUnit::rule17AntipatternDetector)
				.map(Group::getElements)
				.flatMap(List::stream)
				.collect(Collectors.toList());
		 
		 group.addAll(antipatterns);
		 
		 return group;
	}
}
