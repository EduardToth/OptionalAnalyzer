package projects.groups;

import java.util.List;
import java.util.stream.Collectors;

import optionalanalyzer.metamodel.entity.MCompilationUnit;
import optionalanalyzer.metamodel.entity.MProject;
import optionalanalyzer.metamodel.entity.MRule19sAntipattern;
import ro.lrg.xcore.metametamodel.Group;
import ro.lrg.xcore.metametamodel.IRelationBuilder;
import ro.lrg.xcore.metametamodel.RelationBuilder;

@RelationBuilder
public class Rule19AntipatternDetector implements IRelationBuilder<MRule19sAntipattern, MProject>{

	@Override
	public Group<MRule19sAntipattern> buildGroup(MProject arg0) {
		Group<MRule19sAntipattern> group = new Group<>();

		 List<MRule19sAntipattern> antipatterns = arg0.compilationUnitDetector()
				.getElements().stream()
				.map(MCompilationUnit::rule19AntipatternDetector)
				.map(Group::getElements)
				.flatMap(List::stream)
				.collect(Collectors.toList());
		 
		 group.addAll(antipatterns);
		 
		 return group;
	}
}
