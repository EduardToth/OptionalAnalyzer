package projects.groups;

import java.util.List;
import java.util.stream.Collectors;

import optionalanalizer.metamodel.entity.MCompilationUnit;
import optionalanalizer.metamodel.entity.MProject;
import optionalanalizer.metamodel.entity.MRule13Atom;
import ro.lrg.xcore.metametamodel.Group;
import ro.lrg.xcore.metametamodel.IRelationBuilder;
import ro.lrg.xcore.metametamodel.RelationBuilder;

@RelationBuilder
public class Rule13AntipatternBuilder implements IRelationBuilder<MRule13Atom, MProject>{

	@Override
	public Group<MRule13Atom> buildGroup(MProject arg0) {
		Group<MRule13Atom> group = new Group<>();

		 List<MRule13Atom> atoms = arg0.compilationUnitBuilder()
				.getElements().stream()
				.map(MCompilationUnit::rule13AntipatternBuilder)
				.map(Group::getElements)
				.flatMap(List::stream)
				.collect(Collectors.toList());
		 
		 group.addAll(atoms);
		 return group;
	}
}
