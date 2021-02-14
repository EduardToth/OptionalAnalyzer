package packages.groups;

import java.util.List;
import java.util.stream.Collectors;

import optionalanalizer.metamodel.entity.MCompilationUnit;
import optionalanalizer.metamodel.entity.MPackage;
import optionalanalizer.metamodel.entity.MUncategorizedIsPresentAtom;
import ro.lrg.xcore.metametamodel.Group;
import ro.lrg.xcore.metametamodel.IRelationBuilder;
import ro.lrg.xcore.metametamodel.RelationBuilder;

@RelationBuilder
public class UncategorizedIsPresentInvocationBasedAntipatternBuilder 
implements IRelationBuilder<MUncategorizedIsPresentAtom, MPackage>{

	@Override
	public Group<MUncategorizedIsPresentAtom> buildGroup(MPackage arg0) {
		Group<MUncategorizedIsPresentAtom> group = new Group<>();

		List<MUncategorizedIsPresentAtom> atoms = arg0.compilationUnitBuilder()
				.getElements().stream()
				.map(MCompilationUnit::uncategorizedIsPresentInvocationBasedAntipatternBuilder)
				.map(Group::getElements)
				.flatMap(List::stream)
				.collect(Collectors.toList());

		group.addAll(atoms);

		return group;
	}
}