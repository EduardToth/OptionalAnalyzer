package compilationUnits.groups;

import java.util.List;

import optionalanalizer.metamodel.entity.MCompilationUnit;
import optionalanalizer.metamodel.entity.MUncategorizedIsPresentAtom;
import ro.lrg.xcore.metametamodel.Group;
import ro.lrg.xcore.metametamodel.IRelationBuilder;
import ro.lrg.xcore.metametamodel.RelationBuilder;
import uncategorizedIsPresentUsage.UncategorizedIsPresentAtomFinder;

@RelationBuilder
public class UncategorizedIsPresentInvocationBasedAntipatternBuilder 
implements IRelationBuilder<MUncategorizedIsPresentAtom, MCompilationUnit>{

	@Override
	public Group<MUncategorizedIsPresentAtom> buildGroup(MCompilationUnit arg0) {
		MCompilationUnit mCompilationUnit = arg0;
		UncategorizedIsPresentAtomFinder uncategorizedIsPresentAtomFinder = new UncategorizedIsPresentAtomFinder();
		Group<MUncategorizedIsPresentAtom> group = new Group<>();
		List<MUncategorizedIsPresentAtom> atoms = uncategorizedIsPresentAtomFinder.getMAtoms(mCompilationUnit);
		
		group.addAll(atoms);

		return group;
	}
}