package compilationUnits.groups;

import java.util.List;

import org.eclipse.jdt.core.ICompilationUnit;

import optionalanalizer.metamodel.entity.MCompilationUnit;
import optionalanalizer.metamodel.entity.MRule16Atom;
import ro.lrg.xcore.metametamodel.Group;
import ro.lrg.xcore.metametamodel.IRelationBuilder;
import ro.lrg.xcore.metametamodel.RelationBuilder;
import rule16Antipattern.Rule16AntipatternFinder;
import utilities.UtilityClass;

@RelationBuilder
public class Rule16AntipatternDetector  implements IRelationBuilder<MRule16Atom, MCompilationUnit>{

	@Override
	public Group<MRule16Atom> buildGroup(MCompilationUnit arg0) {
		ICompilationUnit iCompilationUnit = (ICompilationUnit) arg0.getUnderlyingObject();
		Rule16AntipatternFinder rule16AtomFinder = new Rule16AntipatternFinder();
		List<MRule16Atom> atoms = rule16AtomFinder.getMAtoms(UtilityClass.parse(iCompilationUnit));
		Group<MRule16Atom> group = new Group<>();
		group.addAll(atoms);
		return group;
	}


}