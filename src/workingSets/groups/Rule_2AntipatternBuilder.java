package workingSets.groups;

import java.util.List;
import java.util.stream.Collectors;

import optionalanalizer.metamodel.entity.MCompilationUnit;
import optionalanalizer.metamodel.entity.MProject;
import optionalanalizer.metamodel.entity.MRule1Atom;
import optionalanalizer.metamodel.entity.MRule2Atom;
import optionalanalizer.metamodel.entity.MWorkingSet;
import ro.lrg.xcore.metametamodel.Group;
import ro.lrg.xcore.metametamodel.IRelationBuilder;
import ro.lrg.xcore.metametamodel.RelationBuilder;

@RelationBuilder
public class Rule_2AntipatternBuilder implements IRelationBuilder<MRule2Atom, MWorkingSet>{

	@Override
	public Group<MRule2Atom> buildGroup(MWorkingSet arg0) {
		Group<MRule2Atom> group = new Group<>();

		 List<MRule2Atom> atoms = arg0.getComponentProjects()
				.getElements().stream()
				.map(MProject::rule_2AntipatternBuilder)
				.map(Group::getElements)
				.flatMap(List::stream)
				.collect(Collectors.toList());
		 
		 group.addAll(atoms);
		 
		 return group;
	}

}