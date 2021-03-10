package workingSets.groups;

import java.util.List;
import java.util.stream.Collectors;

import optionalanalizer.metamodel.entity.MProject;
import optionalanalizer.metamodel.entity.MRule25Atom;
import optionalanalizer.metamodel.entity.MWorkingSet;
import ro.lrg.xcore.metametamodel.Group;
import ro.lrg.xcore.metametamodel.IRelationBuilder;
import ro.lrg.xcore.metametamodel.RelationBuilder;

@RelationBuilder
public class Rule25AntipatternDetector implements IRelationBuilder<MRule25Atom, MWorkingSet>{

	@Override
	public Group<MRule25Atom> buildGroup(MWorkingSet arg0) {
		Group<MRule25Atom> group = new Group<>();

		 List<MRule25Atom> atoms = arg0.getComponentProjects()
				.getElements().stream()
				.map(MProject::rule25AntipatternDetector)
				.map(Group::getElements)
				.flatMap(List::stream)
				.collect(Collectors.toList());
		 
		 group.addAll(atoms);
		 
		 return group;
	}

}