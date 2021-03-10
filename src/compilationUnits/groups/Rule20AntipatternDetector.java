package compilationUnits.groups;

import java.util.List;

import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.dom.CompilationUnit;

import optionalanalizer.metamodel.entity.MCompilationUnit;
import optionalanalizer.metamodel.entity.MRule20Atom;
import ro.lrg.xcore.metametamodel.Group;
import ro.lrg.xcore.metametamodel.IRelationBuilder;
import ro.lrg.xcore.metametamodel.RelationBuilder;
import rule20Antipattern.Rule20AntipatternFinder;
import utilities.UtilityClass;

@RelationBuilder
public class Rule20AntipatternDetector  implements IRelationBuilder<MRule20Atom, MCompilationUnit>{

	@Override
	public Group<MRule20Atom> buildGroup(MCompilationUnit arg0) {
		ICompilationUnit iCompilationUnit = (ICompilationUnit) arg0.getUnderlyingObject();
		Rule20AntipatternFinder rule20AtomFinder = new Rule20AntipatternFinder();
		CompilationUnit compilationUnit = UtilityClass.parse(iCompilationUnit);
		List<MRule20Atom> atoms = rule20AtomFinder.getMAtoms(compilationUnit);
		Group<MRule20Atom> group = new Group<>();
		group.addAll(atoms);
		return group;
	}
}