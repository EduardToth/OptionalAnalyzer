package packages.groups;

import java.util.List;
import java.util.stream.Collectors;

import optionalanalizer.metamodel.entity.MCompilationUnit;
import optionalanalizer.metamodel.entity.MPackage;
import optionalanalizer.metamodel.entity.MRule13Atom;
import ro.lrg.xcore.metametamodel.Group;
import ro.lrg.xcore.metametamodel.IRelationBuilder;
import ro.lrg.xcore.metametamodel.RelationBuilder;

@RelationBuilder
public class Rule13AntipatternBuilder implements IRelationBuilder<MRule13Atom, MPackage>{

	@Override
	public Group<MRule13Atom> buildGroup(MPackage arg0) {
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
