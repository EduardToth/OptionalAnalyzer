package projects.groups;

import java.util.List;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import FullAnalysis.Analysis;
import optionalanalyzer.metamodel.entity.MAnalysis;
import optionalanalyzer.metamodel.entity.MCompilationUnit;
import optionalanalyzer.metamodel.entity.MProject;
import optionalanalyzer.metamodel.factory.Factory;
import ro.lrg.xcore.metametamodel.Group;
import ro.lrg.xcore.metametamodel.IRelationBuilder;
import ro.lrg.xcore.metametamodel.RelationBuilder;

@RelationBuilder
public class FullAnalysis implements IRelationBuilder<MAnalysis, MProject>{

	@Override
	public Group<MAnalysis> buildGroup(MProject arg0) {

		List<MAnalysis> result = arg0.compilationUnitDetector()
				.getElements()
				.stream()
				.map(MCompilationUnit::fullAnalysis)
				.map(Group::getElements)
				.flatMap(List::stream)
				.map(MAnalysis::getUnderlyingObject)
				.filter(Analysis.class::isInstance)
				.map(Analysis.class::cast)
				.collect(Collectors.groupingBy(Analysis::getRuleName))
				.entrySet()
				.stream()
				.map(Entry::getValue)
				.map(equivalentElementsList -> equivalentElementsList.get( 0 ))
				.map(Factory.getInstance()::createMAnalysis)
				.collect(Collectors.toList());
				
		Group<MAnalysis> group = new Group<>();
		group.addAll(result);

		return group;
	}
}
