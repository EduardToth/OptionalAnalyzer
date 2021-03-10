package packages.groups;

import java.util.List;
import java.util.stream.Collectors;

import optionalanalizer.metamodel.entity.MCompilationUnit;
import optionalanalizer.metamodel.entity.MPackage;
import optionalanalizer.metamodel.entity.MRule8Atom;
import ro.lrg.xcore.metametamodel.Group;
import ro.lrg.xcore.metametamodel.IRelationBuilder;
import ro.lrg.xcore.metametamodel.RelationBuilder;

@RelationBuilder
public class Rule_8AntipatternDetector implements IRelationBuilder<MRule8Atom, MPackage>{

	@Override
	public Group<MRule8Atom> buildGroup(MPackage arg0) {
		Group<MRule8Atom> group = new Group<>();

		 List<MRule8Atom> atoms = arg0.compilationUnitDetector()
				.getElements()
				.stream()
				.map(MCompilationUnit::rule_8AntipatternDetector)
				.map(Group::getElements)
				.flatMap(List::stream)
				.collect(Collectors.toList());
		 
		 group.addAll(atoms);
		 
		 return group;
	}
}