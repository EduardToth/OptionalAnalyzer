package compilationUnits.groups;

import java.util.List;

import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.dom.CompilationUnit;

import optionalanalizer.metamodel.entity.MCompilationUnit;
import optionalanalizer.metamodel.entity.MRule18Atom;
import ro.lrg.xcore.metametamodel.Group;
import ro.lrg.xcore.metametamodel.IRelationBuilder;
import ro.lrg.xcore.metametamodel.RelationBuilder;
import rule18Antipattern.Rule18AntipatternFinder;
import utilities.UtilityClass;


@RelationBuilder
public class Rule18AntipatternDetector  implements IRelationBuilder<MRule18Atom, MCompilationUnit>{

	@Override
	public Group<MRule18Atom> buildGroup(MCompilationUnit arg0) {
		ICompilationUnit iCompilationUnit = (ICompilationUnit) arg0.getUnderlyingObject();
		Rule18AntipatternFinder rule18AtomFinder = new Rule18AntipatternFinder();
		CompilationUnit compilationUnit = UtilityClass.parse(iCompilationUnit);
		List<MRule18Atom> atoms = rule18AtomFinder.getMAtoms(compilationUnit);
		Group<MRule18Atom> group = new Group<>();
		group.addAll(atoms);
		return group;
	}
}