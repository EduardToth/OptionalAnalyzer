package packages.groups;

import java.util.List;
import java.util.stream.Collectors;

import optionalanalyzer.metamodel.entity.MCompilationUnit;
import optionalanalyzer.metamodel.entity.MPackage;
import optionalanalyzer.metamodel.entity.MRule21sAntipattern;
import ro.lrg.xcore.metametamodel.Group;
import ro.lrg.xcore.metametamodel.IRelationBuilder;
import ro.lrg.xcore.metametamodel.RelationBuilder;

@RelationBuilder
public class Rule21AntipatternDetector implements IRelationBuilder<MRule21sAntipattern, MPackage>{

	@Override
	public Group<MRule21sAntipattern> buildGroup(MPackage arg0) {
		Group<MRule21sAntipattern> group = new Group<>();

		 List<MRule21sAntipattern> antipatterns = arg0.compilationUnitDetector()
				.getElements().stream()
				.map(MCompilationUnit::rule21AntipatternDetector)
				.map(Group::getElements)
				.flatMap(List::stream)
				.collect(Collectors.toList());
		 
		 group.addAll(antipatterns);
		 
		 return group;
	}
}