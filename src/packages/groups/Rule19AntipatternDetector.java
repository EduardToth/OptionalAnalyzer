package packages.groups;

import java.util.List;
import java.util.stream.Collectors;

import optionalanalizer.metamodel.entity.MCompilationUnit;
import optionalanalizer.metamodel.entity.MPackage;
import optionalanalizer.metamodel.entity.MRule19Atom;
import ro.lrg.xcore.metametamodel.Group;
import ro.lrg.xcore.metametamodel.IRelationBuilder;
import ro.lrg.xcore.metametamodel.RelationBuilder;

@RelationBuilder
public class Rule19AntipatternDetector implements IRelationBuilder<MRule19Atom, MPackage>{

	@Override
	public Group<MRule19Atom> buildGroup(MPackage arg0) {
		Group<MRule19Atom> group = new Group<>();

		 List<MRule19Atom> atoms = arg0.compilationUnitDetector()
				.getElements().stream()
				.map(MCompilationUnit::rule19AntipatternDetector)
				.map(Group::getElements)
				.flatMap(List::stream)
				.collect(Collectors.toList());
		 
		 group.addAll(atoms);
		 
		 return group;
	}
}
