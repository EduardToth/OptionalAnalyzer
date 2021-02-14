package packages.groups;

import java.util.List;
import java.util.stream.Collectors;

import optionalanalizer.metamodel.entity.MCompilationUnit;
import optionalanalizer.metamodel.entity.MPackage;
import optionalanalizer.metamodel.entity.MRule3Atom;
import ro.lrg.xcore.metametamodel.Group;
import ro.lrg.xcore.metametamodel.IRelationBuilder;
import ro.lrg.xcore.metametamodel.RelationBuilder;

@RelationBuilder
public class Rule_3AntipatternBuilder implements IRelationBuilder<MRule3Atom, MPackage>{

	@Override
	public Group<MRule3Atom> buildGroup(MPackage arg0) {
		Group<MRule3Atom> group = new Group<>();

		 List<MRule3Atom> atoms = arg0.compilationUnitBuilder()
				.getElements().stream()
				.map(MCompilationUnit::rule_3AntipatternBuilder)
				.map(Group::getElements)
				.flatMap(List::stream)
				.collect(Collectors.toList());
		 
		 group.addAll(atoms);
		 
		 return group;
	}
}