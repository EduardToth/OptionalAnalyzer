package workingSets.groups;

import java.util.List;
import java.util.stream.Collectors;

import optionalanalizer.metamodel.entity.MProject;
import optionalanalizer.metamodel.entity.MRule9Atom;
import optionalanalizer.metamodel.entity.MWorkingSet;
import ro.lrg.xcore.metametamodel.Group;
import ro.lrg.xcore.metametamodel.IRelationBuilder;
import ro.lrg.xcore.metametamodel.RelationBuilder;

@RelationBuilder
public class Rule_9AntipatternBuilder implements IRelationBuilder<MRule9Atom, MWorkingSet>{

	@Override
	public Group<MRule9Atom> buildGroup(MWorkingSet arg0) {
		Group<MRule9Atom> group = new Group<>();

		 List<MRule9Atom> atoms = arg0.getComponentProjects()
				.getElements().stream()
				.map(MProject::rule_9AntipatternBuilder)
				.map(Group::getElements)
				.flatMap(List::stream)
				.collect(Collectors.toList());
		 
		 group.addAll(atoms);
		 
		 return group;
	}

}