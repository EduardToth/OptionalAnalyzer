package workingSets.groups;

import java.util.List;
import java.util.stream.Collectors;

import optionalanalizer.metamodel.entity.MProject;
import optionalanalizer.metamodel.entity.MRule5Atom;
import optionalanalizer.metamodel.entity.MWorkingSet;
import ro.lrg.xcore.metametamodel.Group;
import ro.lrg.xcore.metametamodel.IRelationBuilder;
import ro.lrg.xcore.metametamodel.RelationBuilder;

@RelationBuilder
public class Rule_5AntipatternDetector implements IRelationBuilder<MRule5Atom, MWorkingSet>{

	@Override
	public Group<MRule5Atom> buildGroup(MWorkingSet arg0) {
		Group<MRule5Atom> group = new Group<>();

		 List<MRule5Atom> atoms = arg0.getComponentProjects()
				.getElements().stream()
				.map(MProject::rule_5AntipatternDetector)
				.map(Group::getElements)
				.flatMap(List::stream)
				.collect(Collectors.toList());
		 
		 group.addAll(atoms);
		 
		 return group;
	}

}