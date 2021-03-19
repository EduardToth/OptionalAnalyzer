package packages.groups;

import java.util.List;
import java.util.stream.Collectors;

import optionalanalyzer.metamodel.entity.MCompilationUnit;
import optionalanalyzer.metamodel.entity.MPackage;
import optionalanalyzer.metamodel.entity.MRule2sAntipattern;
import ro.lrg.xcore.metametamodel.Group;
import ro.lrg.xcore.metametamodel.IRelationBuilder;
import ro.lrg.xcore.metametamodel.RelationBuilder;

@RelationBuilder
public class Rule_2AntipatternDetector implements IRelationBuilder<MRule2sAntipattern, MPackage>{

	@Override
	public Group<MRule2sAntipattern> buildGroup(MPackage arg0) {
		Group<MRule2sAntipattern> group = new Group<>();

		 List<MRule2sAntipattern> antipatterns = arg0.compilationUnitDetector()
				.getElements().stream()
				.map(MCompilationUnit::rule_2AntipatternDetector)
				.map(Group::getElements)
				.flatMap(List::stream)
				.collect(Collectors.toList());
		 
		 group.addAll(antipatterns);
		 
		 return group;
	}
}