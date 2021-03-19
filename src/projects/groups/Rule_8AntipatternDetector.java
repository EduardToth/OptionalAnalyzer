package projects.groups;

import java.util.List;
import java.util.stream.Collectors;

import optionalanalyzer.metamodel.entity.MCompilationUnit;
import optionalanalyzer.metamodel.entity.MProject;
import optionalanalyzer.metamodel.entity.MRule8sAntipattern;
import ro.lrg.xcore.metametamodel.Group;
import ro.lrg.xcore.metametamodel.IRelationBuilder;
import ro.lrg.xcore.metametamodel.RelationBuilder;

@RelationBuilder
public class Rule_8AntipatternDetector implements IRelationBuilder<MRule8sAntipattern, MProject>{

	@Override
	public Group<MRule8sAntipattern> buildGroup(MProject arg0) {
		Group<MRule8sAntipattern> group = new Group<>();

		 List<MRule8sAntipattern> antipatterns = arg0.compilationUnitDetector()
				.getElements()
				.stream()
				.map(MCompilationUnit::rule_8AntipatternDetector)
				.map(Group::getElements)
				.flatMap(List::stream)
				.collect(Collectors.toList());
		 
		 group.addAll(antipatterns);
		 
		 return group;
	}
}