package projects.groups;

import java.util.List;
import java.util.stream.Collectors;

import optionalanalizer.metamodel.entity.MCompilationUnit;
import optionalanalizer.metamodel.entity.MProject;
import optionalanalizer.metamodel.entity.MRule9Atom;
import ro.lrg.xcore.metametamodel.Group;
import ro.lrg.xcore.metametamodel.IRelationBuilder;
import ro.lrg.xcore.metametamodel.RelationBuilder;

@RelationBuilder
public class Rule_9AntipatternDetector implements IRelationBuilder<MRule9Atom, MProject>{

	@Override
	public Group<MRule9Atom> buildGroup(MProject arg0) {
		Group<MRule9Atom> group = new Group<>();

		 List<MRule9Atom> atoms = arg0.compilationUnitDetector()
				.getElements().stream()
				.map(MCompilationUnit::rule_9AntipatternDetector)
				.map(Group::getElements)
				.flatMap(List::stream)
				.collect(Collectors.toList());
		 
		 group.addAll(atoms);
		 
		 return group;
	}
}