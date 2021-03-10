package compilationUnits.groups;

import java.util.List;

import org.eclipse.jdt.core.ICompilationUnit;

import optionalanalizer.metamodel.entity.MCompilationUnit;
import optionalanalizer.metamodel.entity.MRule17Atom;
import ro.lrg.xcore.metametamodel.Group;
import ro.lrg.xcore.metametamodel.IRelationBuilder;
import ro.lrg.xcore.metametamodel.RelationBuilder;
import rule17Antipattern.Rule17AntipatternFinder;
import utilities.UtilityClass;

@RelationBuilder
public class Rule17AntipatternDetector  implements IRelationBuilder<MRule17Atom, MCompilationUnit>{

	@Override
	public Group<MRule17Atom> buildGroup(MCompilationUnit arg0) {
		ICompilationUnit iCompilationUnit = (ICompilationUnit) arg0.getUnderlyingObject();
		Rule17AntipatternFinder rule17AtomFinder = new Rule17AntipatternFinder();
		List<MRule17Atom> atoms = rule17AtomFinder.getMAtoms(UtilityClass.parse(iCompilationUnit));
		Group<MRule17Atom> group = new Group<>();
		group.addAll(atoms);
		return group;
	}
}