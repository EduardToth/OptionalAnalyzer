package packages.groups;

import java.util.List;
import java.util.stream.Collectors;

import optionalanalyzer.metamodel.entity.MCompilationUnit;
import optionalanalyzer.metamodel.entity.MPackage;
import optionalanalyzer.metamodel.entity.MRule15sAntipattern;
import ro.lrg.xcore.metametamodel.Group;
import ro.lrg.xcore.metametamodel.IRelationBuilder;
import ro.lrg.xcore.metametamodel.RelationBuilder;

@RelationBuilder
public class Rule15AntipatternDetector implements IRelationBuilder<MRule15sAntipattern, MPackage>{

	@Override
	public Group<MRule15sAntipattern> buildGroup(MPackage arg0) {
		Group<MRule15sAntipattern> group = new Group<>();

		 List<MRule15sAntipattern> antipatterns = arg0.compilationUnitDetector()
				.getElements().stream()
				.map(MCompilationUnit::rule15AntipatternDetector)
				.map(Group::getElements)
				.flatMap(List::stream)
				.collect(Collectors.toList());
		 
		 group.addAll(antipatterns);
		 
		 return group;
	}
}