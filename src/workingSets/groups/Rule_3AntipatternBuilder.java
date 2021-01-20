package workingSets.groups;

import java.util.List;
import java.util.stream.Collectors;

import optionalanalizer.metamodel.entity.MProject;
import optionalanalizer.metamodel.entity.MRule3Atom;
import optionalanalizer.metamodel.entity.MWorkingSet;
import ro.lrg.xcore.metametamodel.Group;
import ro.lrg.xcore.metametamodel.IRelationBuilder;
import ro.lrg.xcore.metametamodel.RelationBuilder;

@RelationBuilder
public class Rule_3AntipatternBuilder implements IRelationBuilder<MRule3Atom, MWorkingSet>{

	@Override
	public Group<MRule3Atom> buildGroup(MWorkingSet arg0) {
		Group<MRule3Atom> group = new Group<>();

		 List<MRule3Atom> atoms = arg0.getComponentProjects()
				.getElements().stream()
				.map(MProject::rule_3AntipatternBuilder)
				.map(Group::getElements)
				.flatMap(List::stream)
				.collect(Collectors.toList());
		 
		 group.addAll(atoms);
		 
		 return group;
	}

}