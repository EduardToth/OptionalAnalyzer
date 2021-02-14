package packages.groups;

import java.util.List;
import java.util.stream.Collectors;

import optionalanalizer.metamodel.entity.MCompilationUnit;
import optionalanalizer.metamodel.entity.MPackage;
import optionalanalizer.metamodel.entity.MRule2Atom;
import ro.lrg.xcore.metametamodel.Group;
import ro.lrg.xcore.metametamodel.IRelationBuilder;
import ro.lrg.xcore.metametamodel.RelationBuilder;

@RelationBuilder
public class Rule_2AntipatternBuilder implements IRelationBuilder<MRule2Atom, MPackage>{

	@Override
	public Group<MRule2Atom> buildGroup(MPackage arg0) {
		Group<MRule2Atom> group = new Group<>();

		 List<MRule2Atom> atoms = arg0.compilationUnitBuilder()
				.getElements().stream()
				.map(MCompilationUnit::rule_2AntipatternBuilder)
				.map(Group::getElements)
				.flatMap(List::stream)
				.collect(Collectors.toList());
		 
		 group.addAll(atoms);
		 
		 return group;
	}
}