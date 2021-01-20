package workingSets.groups;

import java.util.List;
import java.util.stream.Collectors;

import optionalanalizer.metamodel.entity.MProject;
import optionalanalizer.metamodel.entity.MRule13Atom;
import optionalanalizer.metamodel.entity.MWorkingSet;
import ro.lrg.xcore.metametamodel.Group;
import ro.lrg.xcore.metametamodel.IRelationBuilder;
import ro.lrg.xcore.metametamodel.RelationBuilder;

@RelationBuilder
public class Rule13AntipatternBuilder implements IRelationBuilder<MRule13Atom, MWorkingSet>{

	@Override
	public Group<MRule13Atom> buildGroup(MWorkingSet arg0) {
		Group<MRule13Atom> group = new Group<>();

		 List<MRule13Atom> atoms = arg0.getComponentProjects()
				.getElements().stream()
				.map(MProject::rule13AntipatternBuilder)
				.map(Group::getElements)
				.flatMap(List::stream)
				.collect(Collectors.toList());
		 System.out.println(">>>>>>>>>>>>>>>>>>>>>>> " + atoms);
		 group.addAll(atoms);
		 
		 return group;
	}
}
