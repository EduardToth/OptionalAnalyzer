package workingSets.groups;

import java.util.List;
import java.util.stream.Collectors;

import optionalanalizer.metamodel.entity.MProject;
import optionalanalizer.metamodel.entity.MRule15Atom;
import optionalanalizer.metamodel.entity.MWorkingSet;
import ro.lrg.xcore.metametamodel.Group;
import ro.lrg.xcore.metametamodel.IRelationBuilder;
import ro.lrg.xcore.metametamodel.RelationBuilder;

@RelationBuilder
public class Rule15AntipatternDetector implements IRelationBuilder<MRule15Atom, MWorkingSet>{

	@Override
	public Group<MRule15Atom> buildGroup(MWorkingSet arg0) {
		Group<MRule15Atom> group = new Group<>();

		 List<MRule15Atom> atoms = arg0.getComponentProjects()
				.getElements().stream()
				.map(MProject::rule15AntipatternDetector)
				.map(Group::getElements)
				.flatMap(List::stream)
				.collect(Collectors.toList());
		 
		 group.addAll(atoms);
		 
		 return group;
	}

}