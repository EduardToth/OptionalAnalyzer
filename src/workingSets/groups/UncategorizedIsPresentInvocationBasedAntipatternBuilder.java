package workingSets.groups;

import java.util.List;
import java.util.stream.Collectors;

import optionalanalizer.metamodel.entity.MProject;
import optionalanalizer.metamodel.entity.MUncategorizedIsPresentAtom;
import optionalanalizer.metamodel.entity.MWorkingSet;
import ro.lrg.xcore.metametamodel.Group;
import ro.lrg.xcore.metametamodel.IRelationBuilder;
import ro.lrg.xcore.metametamodel.RelationBuilder;

@RelationBuilder
public class UncategorizedIsPresentInvocationBasedAntipatternBuilder 
implements IRelationBuilder<MUncategorizedIsPresentAtom, MWorkingSet>{

	@Override
	public Group<MUncategorizedIsPresentAtom> buildGroup(MWorkingSet arg0) {
		Group<MUncategorizedIsPresentAtom> group = new Group<>();

		 List<MUncategorizedIsPresentAtom> atoms = arg0.getComponentProjects()
				.getElements().stream()
				.map(MProject::uncategorizedIsPresentInvocationBasedAntipatternBuilder)
				.map(Group::getElements)
				.flatMap(List::stream)
				.collect(Collectors.toList());
		 
		 group.addAll(atoms);
		 
		 return group;
	}

}