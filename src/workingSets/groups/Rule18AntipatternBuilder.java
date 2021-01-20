package workingSets.groups;

import java.util.List;
import java.util.stream.Collectors;

import optionalanalizer.metamodel.entity.MProject;
import optionalanalizer.metamodel.entity.MRule18Atom;
import optionalanalizer.metamodel.entity.MWorkingSet;
import ro.lrg.xcore.metametamodel.Group;
import ro.lrg.xcore.metametamodel.IRelationBuilder;
import ro.lrg.xcore.metametamodel.RelationBuilder;

@RelationBuilder
public class Rule18AntipatternBuilder implements IRelationBuilder<MRule18Atom, MWorkingSet>{

	@Override
	public Group<MRule18Atom> buildGroup(MWorkingSet arg0) {
		Group<MRule18Atom> group = new Group<>();

		 List<MRule18Atom> atoms = arg0.getComponentProjects()
				.getElements().stream()
				.map(MProject::rule18AntipatternBuilder)
				.map(Group::getElements)
				.flatMap(List::stream)
				.collect(Collectors.toList());
		 
		 group.addAll(atoms);
		 
		 return group;
	}

}