package workingSets.groups;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import FullAnalysis.Analysis;
import optionalanalizer.metamodel.entity.MAnalysis;
import optionalanalizer.metamodel.entity.MProject;
import optionalanalizer.metamodel.entity.MWorkingSet;
import optionalanalizer.metamodel.factory.Factory;
import ro.lrg.xcore.metametamodel.Group;
import ro.lrg.xcore.metametamodel.IRelationBuilder;
import ro.lrg.xcore.metametamodel.RelationBuilder;

@RelationBuilder
public class FullAnalisys implements IRelationBuilder<MAnalysis, MWorkingSet>{

	@Override
	public Group<MAnalysis> buildGroup(MWorkingSet arg0) {

	
		
		List<Analysis> analyses = arg0.getComponentProjects()
				.getElements()
				.stream()
				.map(MProject::fullAnalysis)
				.map(Group::getElements)
				.flatMap(List::stream)
				.map(MAnalysis::getUnderlyingObject)
				.filter(Analysis.class::isInstance)
				.map(Analysis.class::cast)
				.collect(Collectors.toList());

		List<MAnalysis> mAnalyses = removeDuplicates(analyses).stream()
				.map(Factory.getInstance()::createMAnalysis)
				.collect(Collectors.toList());
		
		Group<MAnalysis> group = new Group<>();
		
		group.addAll(mAnalyses);
		
		return group;
	}

	private Set<Analysis> removeDuplicates(List<Analysis> analyses) {
		Map<String, Analysis> map = new HashMap<>();

		for(Analysis tmp : analyses) {
			map.put(tmp.getRuleName(), tmp);
		}

		return map.values()
				.stream()
				.collect(Collectors.toSet());
	}
}
