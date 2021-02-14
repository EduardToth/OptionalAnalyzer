package packages.groups;

import java.util.List;
import java.util.stream.Collectors;

import optionalanalizer.metamodel.entity.MCompilationUnit;
import optionalanalizer.metamodel.entity.MPackage;
import optionalanalizer.metamodel.entity.MRule18Atom;
import ro.lrg.xcore.metametamodel.Group;
import ro.lrg.xcore.metametamodel.IRelationBuilder;
import ro.lrg.xcore.metametamodel.RelationBuilder;

@RelationBuilder
public class Rule18AntipatternBuilder implements IRelationBuilder<MRule18Atom, MPackage>{

	@Override
	public Group<MRule18Atom> buildGroup(MPackage arg0) {
		Group<MRule18Atom> group = new Group<>();

		 List<MRule18Atom> atoms = arg0.compilationUnitBuilder()
				.getElements().stream()
				.map(MCompilationUnit::rule18AntipatternBuilder)
				.map(Group::getElements)
				.flatMap(List::stream)
				.collect(Collectors.toList());
		 
		 group.addAll(atoms);
		 
		 return group;
	}
}