package compilationUnits.groups;

import java.util.List;

import org.eclipse.jdt.core.ICompilationUnit;

import optionalanalizer.metamodel.entity.MCompilationUnit;
import optionalanalizer.metamodel.entity.MRule15Atom;
import ro.lrg.xcore.metametamodel.Group;
import ro.lrg.xcore.metametamodel.IRelationBuilder;
import ro.lrg.xcore.metametamodel.RelationBuilder;
import rule15Antipattern.Rule15AtomFinder;
import utilities.UtilityClass;

@RelationBuilder
public class Rule15AntipatternBuilder  implements IRelationBuilder<MRule15Atom, MCompilationUnit>{

	@Override
	public Group<MRule15Atom> buildGroup(MCompilationUnit arg0) {

		ICompilationUnit iCompilationUnit = (ICompilationUnit) arg0.getUnderlyingObject();
		Rule15AtomFinder rule15AtomFinder = new Rule15AtomFinder();
		List<MRule15Atom> atoms = rule15AtomFinder.getMAtoms(UtilityClass.parse(iCompilationUnit));
		Group<MRule15Atom> group = new Group<>();
		group.addAll(atoms);
		return group;
	}

}