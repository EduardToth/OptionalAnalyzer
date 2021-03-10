package packages.groups;

import java.util.List;
import java.util.stream.Collectors;

import optionalanalizer.metamodel.entity.MCompilationUnit;
import optionalanalizer.metamodel.entity.MPackage;
import optionalanalizer.metamodel.entity.MRule14Atom;
import ro.lrg.xcore.metametamodel.Group;
import ro.lrg.xcore.metametamodel.IRelationBuilder;
import ro.lrg.xcore.metametamodel.RelationBuilder;

@RelationBuilder
public class Rule14AntipatternDetector implements IRelationBuilder<MRule14Atom, MPackage>{

	@Override
	public Group<MRule14Atom> buildGroup(MPackage arg0) {
		Group<MRule14Atom> group = new Group<>();

		 List<MRule14Atom> atoms = arg0.compilationUnitDetector()
				.getElements().stream()
				.map(MCompilationUnit::rule14AntipatternDetector)
				.map(Group::getElements)
				.flatMap(List::stream)
				.collect(Collectors.toList());
		 
		 group.addAll(atoms);
		 
		 return group;
	}
}