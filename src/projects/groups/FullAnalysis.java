package projects.groups;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import FullAnalysis.Analysis;
import optionalanalizer.metamodel.entity.MAnalysis;
import optionalanalizer.metamodel.entity.MCompilationUnit;
import optionalanalizer.metamodel.entity.MProject;
import optionalanalizer.metamodel.factory.Factory;
import ro.lrg.xcore.metametamodel.Group;
import ro.lrg.xcore.metametamodel.IRelationBuilder;
import ro.lrg.xcore.metametamodel.RelationBuilder;

@RelationBuilder
public class FullAnalysis implements IRelationBuilder<MAnalysis, MProject>{

	@Override
	public Group<MAnalysis> buildGroup(MProject arg0) {

		List<MAnalysis> result = arg0.compilationUnitBuilder()
				.getElements()
				.stream()
				.map(MCompilationUnit::fullAnalysisGroupBuilder)
				.map(Group::getElements)
				.flatMap(List::stream)
				.collect(Collectors.toList());

		Group<MAnalysis> group = new Group<>();
		group.addAll(removeDuplicates(result));

		return group;

	}

	private List<MAnalysis> removeDuplicates(List<MAnalysis> list) {
		Set<String> ruleNames = new HashSet<>();
		List<Analysis> newList = new ArrayList<>();

		for(MAnalysis mAnalysis : list) {
			Analysis analysis = (Analysis) mAnalysis.getUnderlyingObject();
			if(!ruleNames.contains(analysis.getRuleName())) {
				newList.add(analysis);
				ruleNames.add(analysis.getRuleName());
			}
		}

		return newList.stream()
				.map(analysis -> Factory.getInstance().createMAnalysis(analysis))
				.collect(Collectors.toList());
	}

}
