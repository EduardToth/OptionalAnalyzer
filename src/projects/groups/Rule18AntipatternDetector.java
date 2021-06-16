package projects.groups;

import java.util.List;
import java.util.stream.Collectors;

import optionalanalyzer.metamodel.entity.MCompilationUnit;
import optionalanalyzer.metamodel.entity.MPackage;
import optionalanalyzer.metamodel.entity.MProject;
import optionalanalyzer.metamodel.entity.MRule18sAntipattern;
import ro.lrg.xcore.metametamodel.Group;
import ro.lrg.xcore.metametamodel.IRelationBuilder;
import ro.lrg.xcore.metametamodel.RelationBuilder;

@RelationBuilder
public class Rule18AntipatternDetector implements IRelationBuilder<MRule18sAntipattern, MProject>{

	@Override
	public Group<MRule18sAntipattern> buildGroup(MProject arg0) {
		Group<MRule18sAntipattern> group = new Group<>();

		List<MRule18sAntipattern> antipatterns = arg0.compilationUnitDetector()
				.getElements().parallelStream()
				.unordered()
				.map(MCompilationUnit::rule18AntipatternDetector)
				.map(Group::getElements)
				.flatMap(List::stream)
				.collect(Collectors.toList());

		group.addAll(antipatterns);

		return group;
	}
}