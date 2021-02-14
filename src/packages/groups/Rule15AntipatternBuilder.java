package packages.groups;

import java.util.List;
import java.util.stream.Collectors;

import optionalanalizer.metamodel.entity.MCompilationUnit;
import optionalanalizer.metamodel.entity.MPackage;
import optionalanalizer.metamodel.entity.MRule15Atom;
import ro.lrg.xcore.metametamodel.Group;
import ro.lrg.xcore.metametamodel.IRelationBuilder;
import ro.lrg.xcore.metametamodel.RelationBuilder;

@RelationBuilder
public class Rule15AntipatternBuilder implements IRelationBuilder<MRule15Atom, MPackage>{

	@Override
	public Group<MRule15Atom> buildGroup(MPackage arg0) {
		Group<MRule15Atom> group = new Group<>();

		 List<MRule15Atom> atoms = arg0.compilationUnitBuilder()
				.getElements().stream()
				.map(MCompilationUnit::rule15AntipatternBuilder)
				.map(Group::getElements)
				.flatMap(List::stream)
				.collect(Collectors.toList());
		 
		 group.addAll(atoms);
		 
		 return group;
	}
}