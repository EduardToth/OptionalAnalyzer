package projects.groups;

import java.util.List;
import java.util.stream.Collectors;

import optionalanalyzer.metamodel.entity.MCompilationUnit;
import optionalanalyzer.metamodel.entity.MProject;
import optionalanalyzer.metamodel.entity.MRule6sAntipattern;
import ro.lrg.xcore.metametamodel.Group;
import ro.lrg.xcore.metametamodel.IRelationBuilder;
import ro.lrg.xcore.metametamodel.RelationBuilder;

@RelationBuilder
public class Rule_6AntipatternDetector implements IRelationBuilder<MRule6sAntipattern, MProject>{

	@Override
	public Group<MRule6sAntipattern> buildGroup(MProject arg0) {
		Group<MRule6sAntipattern> group = new Group<>();

		List<MRule6sAntipattern> antipatterns = arg0.compilationUnitDetector()
				.getElements().parallelStream()
				.unordered()
				.map(MCompilationUnit::rule_6AntipatternDetector)
				.map(Group::getElements)
				.flatMap(List::stream)
				.collect(Collectors.toList());

		group.addAll(antipatterns);

		return group;
	}
}