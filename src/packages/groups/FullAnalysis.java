package packages.groups;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import FullAnalysis.Analysis;
import optionalanalyzer.metamodel.entity.MAnalysis;
import optionalanalyzer.metamodel.entity.MCompilationUnit;
import optionalanalyzer.metamodel.entity.MPackage;
import optionalanalyzer.metamodel.factory.Factory;
import ro.lrg.xcore.metametamodel.Group;
import ro.lrg.xcore.metametamodel.IRelationBuilder;
import ro.lrg.xcore.metametamodel.RelationBuilder;

@RelationBuilder
public class FullAnalysis implements IRelationBuilder<MAnalysis, MPackage>{

	@Override
	public Group<MAnalysis> buildGroup(MPackage arg0) {

		List<MAnalysis> result = arg0.compilationUnitDetector()
				.getElements()
				.stream()
				.map(MCompilationUnit::fullAnalysis)
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
				.map(Factory.getInstance()::createMAnalysis)
				.collect(Collectors.toList());
	}

}
