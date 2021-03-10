package workingSets.groups;

import java.util.List;
import java.util.stream.Collectors;

import optionalanalizer.metamodel.entity.MProject;
import optionalanalizer.metamodel.entity.MRule1Atom;
import optionalanalizer.metamodel.entity.MWorkingSet;
import ro.lrg.xcore.metametamodel.Group;
import ro.lrg.xcore.metametamodel.IRelationBuilder;
import ro.lrg.xcore.metametamodel.RelationBuilder;

@RelationBuilder
public class Rule_1AntipatternDetector implements IRelationBuilder<MRule1Atom, MWorkingSet>{

	@Override
	public Group<MRule1Atom> buildGroup(MWorkingSet arg0) {
		Group<MRule1Atom> group = new Group<>();

		 List<MRule1Atom> atoms = arg0.getComponentProjects()
				.getElements().stream()
				.map(MProject::rule_1AntipatternDetector)
				.map(Group::getElements)
				.flatMap(List::stream)
				.collect(Collectors.toList());
		 
		 group.addAll(atoms);
		 
		 return group;
	}

}
