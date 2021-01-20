package workingSets.groups;

import java.util.List;
import java.util.stream.Collectors;

import optionalanalizer.metamodel.entity.MProject;
import optionalanalizer.metamodel.entity.MRule16Atom;
import optionalanalizer.metamodel.entity.MWorkingSet;
import ro.lrg.xcore.metametamodel.Group;
import ro.lrg.xcore.metametamodel.IRelationBuilder;
import ro.lrg.xcore.metametamodel.RelationBuilder;

@RelationBuilder
public class Rule16AntipatternBuilder implements IRelationBuilder<MRule16Atom, MWorkingSet>{

	@Override
	public Group<MRule16Atom> buildGroup(MWorkingSet arg0) {
		Group<MRule16Atom> group = new Group<>();

		 List<MRule16Atom> atoms = arg0.getComponentProjects()
				.getElements().stream()
				.map(MProject::rule16AntipatternBuilder)
				.map(Group::getElements)
				.flatMap(List::stream)
				.collect(Collectors.toList());
		 
		 group.addAll(atoms);
		 
		 return group;
	}

}