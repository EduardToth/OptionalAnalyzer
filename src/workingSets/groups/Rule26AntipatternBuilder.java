package workingSets.groups;

import java.util.List;
import java.util.stream.Collectors;

import optionalanalizer.metamodel.entity.MProject;
import optionalanalizer.metamodel.entity.MRule26Atom;
import optionalanalizer.metamodel.entity.MWorkingSet;
import ro.lrg.xcore.metametamodel.Group;
import ro.lrg.xcore.metametamodel.IRelationBuilder;
import ro.lrg.xcore.metametamodel.RelationBuilder;

@RelationBuilder
public class Rule26AntipatternBuilder implements IRelationBuilder<MRule26Atom, MWorkingSet>{

	@Override
	public Group<MRule26Atom> buildGroup(MWorkingSet arg0) {
		Group<MRule26Atom> group = new Group<>();

		 List<MRule26Atom> atoms = arg0.getComponentProjects()
				.getElements().stream()
				.map(MProject::rule26AntipatternBuilder)
				.map(Group::getElements)
				.flatMap(List::stream)
				.collect(Collectors.toList());
		 
		 group.addAll(atoms);
		 
		 return group;
	}

}