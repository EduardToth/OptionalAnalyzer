package workingSets.groups;

import java.util.List;
import java.util.stream.Collectors;

import optionalanalizer.metamodel.entity.MProject;
import optionalanalizer.metamodel.entity.MRule17Atom;
import optionalanalizer.metamodel.entity.MWorkingSet;
import ro.lrg.xcore.metametamodel.Group;
import ro.lrg.xcore.metametamodel.IRelationBuilder;
import ro.lrg.xcore.metametamodel.RelationBuilder;

@RelationBuilder
public class Rule17AntipatternBuilder implements IRelationBuilder<MRule17Atom, MWorkingSet>{

	@Override
	public Group<MRule17Atom> buildGroup(MWorkingSet arg0) {
		Group<MRule17Atom> group = new Group<>();

		 List<MRule17Atom> atoms = arg0.getComponentProjects()
				.getElements().stream()
				.map(MProject::rule17AntipatternBuilder)
				.map(Group::getElements)
				.flatMap(List::stream)
				.collect(Collectors.toList());
		 
		 group.addAll(atoms);
		 
		 return group;
	}

}
