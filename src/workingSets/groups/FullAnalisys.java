package workingSets.groups;

import java.util.List;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import FullAnalysis.Analysis;
import optionalanalyzer.metamodel.entity.MAnalysis;
import optionalanalyzer.metamodel.entity.MProject;
import optionalanalyzer.metamodel.entity.MWorkingSet;
import optionalanalyzer.metamodel.factory.Factory;
import ro.lrg.xcore.metametamodel.Group;
import ro.lrg.xcore.metametamodel.IRelationBuilder;
import ro.lrg.xcore.metametamodel.RelationBuilder;

@RelationBuilder
public class FullAnalisys implements IRelationBuilder<MAnalysis, MWorkingSet>{

	@Override
	public Group<MAnalysis> buildGroup(MWorkingSet arg0) {

		List<MAnalysis> antipatterns = arg0.getComponentProjects()
				.getElements()
				.stream()
				.map(MProject::fullAnalysis)
				.map(Group::getElements)
				.flatMap(List::stream)
				.map(MAnalysis::getUnderlyingObject)
				.filter(Analysis.class::isInstance)
				.map(Analysis.class::cast)
				.collect(Collectors.groupingBy(Analysis::getRuleName))
				.entrySet()
				.stream()
				.map(Entry::getValue)
				.map(myList -> myList.get( 0 ))
				.map(Factory.getInstance()::createMAnalysis)
				.collect(Collectors.toList());


		Group<MAnalysis> analysisGroup = new Group<>();
		analysisGroup.addAll(antipatterns);
		
		return analysisGroup;
	}
}
