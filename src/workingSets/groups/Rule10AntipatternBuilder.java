package workingSets.groups;

import java.util.List;
import java.util.stream.Collectors;

import optionalanalizer.metamodel.entity.MProject;
import optionalanalizer.metamodel.entity.MRule10Atom;
import optionalanalizer.metamodel.entity.MWorkingSet;
import ro.lrg.xcore.metametamodel.Group;
import ro.lrg.xcore.metametamodel.IRelationBuilder;
import ro.lrg.xcore.metametamodel.RelationBuilder;

@RelationBuilder
public class Rule10AntipatternBuilder implements IRelationBuilder<MRule10Atom, MWorkingSet>{

	@Override
	public Group<MRule10Atom> buildGroup(MWorkingSet arg0) {
		Group<MRule10Atom> group = new Group<>();

		 List<MRule10Atom> atoms = arg0.getComponentProjects()
				.getElements().stream()
				.map(MProject::rule10AntipatternBuilder)
				.map(Group::getElements)
				.flatMap(List::stream)
				.collect(Collectors.toList());
		 
		 group.addAll(atoms);
		 
		 return group;
	}
}
