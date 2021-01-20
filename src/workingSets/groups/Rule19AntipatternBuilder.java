package workingSets.groups;

import java.util.List;
import java.util.stream.Collectors;

import optionalanalizer.metamodel.entity.MProject;
import optionalanalizer.metamodel.entity.MRule19Atom;
import optionalanalizer.metamodel.entity.MWorkingSet;
import ro.lrg.xcore.metametamodel.Group;
import ro.lrg.xcore.metametamodel.IRelationBuilder;
import ro.lrg.xcore.metametamodel.RelationBuilder;

@RelationBuilder
public class Rule19AntipatternBuilder implements IRelationBuilder<MRule19Atom, MWorkingSet>{

	@Override
	public Group<MRule19Atom> buildGroup(MWorkingSet arg0) {
		Group<MRule19Atom> group = new Group<>();

		 List<MRule19Atom> atoms = arg0.getComponentProjects()
				.getElements().stream()
				.map(MProject::rule19AntipatternBuilder)
				.map(Group::getElements)
				.flatMap(List::stream)
				.collect(Collectors.toList());
		 
		 group.addAll(atoms);
		 
		 return group;
	}

}
