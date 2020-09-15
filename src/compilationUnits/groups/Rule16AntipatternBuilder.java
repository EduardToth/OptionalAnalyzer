package compilationUnits.groups;

import java.util.List;

import org.eclipse.jdt.core.ICompilationUnit;

import optionalanalizer.metamodel.entity.MCompilationUnit;
import optionalanalizer.metamodel.entity.MRule16Atom;
import ro.lrg.xcore.metametamodel.Group;
import ro.lrg.xcore.metametamodel.IRelationBuilder;
import ro.lrg.xcore.metametamodel.RelationBuilder;
import rule16Antipattern.Rule16AtomFinder;
import utilities.UtilityClass;

@RelationBuilder
public class Rule16AntipatternBuilder  implements IRelationBuilder<MRule16Atom, MCompilationUnit>{

	@Override
	public Group<MRule16Atom> buildGroup(MCompilationUnit arg0) {
		ICompilationUnit iCompilationUnit = (ICompilationUnit) arg0.getUnderlyingObject();
		Rule16AtomFinder rule16AtomFinder = new Rule16AtomFinder();
		List<MRule16Atom> atoms = rule16AtomFinder.getMAtoms(UtilityClass.parse(iCompilationUnit));
		Group<MRule16Atom> group = new Group<>();
		group.addAll(atoms);
		return group;
	}


}