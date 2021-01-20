package workingSets.groups;


import java.util.List;
import java.util.stream.Collectors;

import optionalanalizer.metamodel.entity.MProject;
import optionalanalizer.metamodel.entity.MRule12Atom;
import optionalanalizer.metamodel.entity.MWorkingSet;
import ro.lrg.xcore.metametamodel.Group;
import ro.lrg.xcore.metametamodel.IRelationBuilder;
import ro.lrg.xcore.metametamodel.RelationBuilder;

@RelationBuilder
public class Rule12AntipatternBuilder implements IRelationBuilder<MRule12Atom, MWorkingSet>{

	@Override
	public Group<MRule12Atom> buildGroup(MWorkingSet arg0) {
		Group<MRule12Atom> group = new Group<>();

		 List<MRule12Atom> atoms = arg0.getComponentProjects()
				.getElements().stream()
				.map(MProject::rule12AntipatternBuilder)
				.map(Group::getElements)
				.flatMap(List::stream)
				.collect(Collectors.toList());
		 
		 group.addAll(atoms);
		 
		 return group;
	}

}