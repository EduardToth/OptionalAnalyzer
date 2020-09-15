package compilationUnits.groups;

import java.util.List;

import org.eclipse.jdt.core.ICompilationUnit;

import optionalanalizer.metamodel.entity.MCompilationUnit;
import optionalanalizer.metamodel.entity.MRule14Atom;
import ro.lrg.xcore.metametamodel.Group;
import ro.lrg.xcore.metametamodel.IRelationBuilder;
import ro.lrg.xcore.metametamodel.RelationBuilder;
import rule14Antipattern.Rule14AtomFinder;
import utilities.UtilityClass;

@RelationBuilder
public class Rule14AntipatternBuilder  implements IRelationBuilder<MRule14Atom, MCompilationUnit>{

	@Override
	public Group<MRule14Atom> buildGroup(MCompilationUnit arg0) {
		ICompilationUnit iCompilationUnit = (ICompilationUnit) arg0.getUnderlyingObject();
		Rule14AtomFinder rule14AtomFinder = new Rule14AtomFinder();
		List<MRule14Atom> atoms = rule14AtomFinder.getMAtoms(UtilityClass.parse(iCompilationUnit));
		Group<MRule14Atom> group = new Group<>();
		group.addAll(atoms);
		return group;
	}
}