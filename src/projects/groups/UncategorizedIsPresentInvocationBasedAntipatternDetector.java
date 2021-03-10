package projects.groups;

import java.util.List;
import java.util.stream.Collectors;

import optionalanalizer.metamodel.entity.MCompilationUnit;
import optionalanalizer.metamodel.entity.MProject;
import optionalanalizer.metamodel.entity.MUncategorizedIsPresentAtom;
import ro.lrg.xcore.metametamodel.Group;
import ro.lrg.xcore.metametamodel.IRelationBuilder;
import ro.lrg.xcore.metametamodel.RelationBuilder;

@RelationBuilder
public class UncategorizedIsPresentInvocationBasedAntipatternDetector 
implements IRelationBuilder<MUncategorizedIsPresentAtom, MProject>{

	@Override
	public Group<MUncategorizedIsPresentAtom> buildGroup(MProject arg0) {
		Group<MUncategorizedIsPresentAtom> group = new Group<>();

		List<MUncategorizedIsPresentAtom> atoms = arg0.compilationUnitDetector()
				.getElements().stream()
				.map(MCompilationUnit::uncategorizedIsPresentInvocationBasedAntipatternDetector)
				.map(Group::getElements)
				.flatMap(List::stream)
				.collect(Collectors.toList());

		group.addAll(atoms);

		return group;
	}
}