package compilationUnits.groups;

import java.util.List;

import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.dom.CompilationUnit;

import optionalanalizer.metamodel.entity.MCompilationUnit;
import optionalanalizer.metamodel.entity.MRule3Atom;
import ro.lrg.xcore.metametamodel.Group;
import ro.lrg.xcore.metametamodel.IRelationBuilder;
import ro.lrg.xcore.metametamodel.RelationBuilder;
import rule_3Antipattern.Rule3AtomFinder;
import utilities.UtilityClass;

@RelationBuilder
public class Rule_3AntipatternBuilder  implements IRelationBuilder<MRule3Atom, MCompilationUnit>{

	@Override
	public Group<MRule3Atom> buildGroup(MCompilationUnit arg0) {
		Rule3AtomFinder rule3AtomFinder = new Rule3AtomFinder();
		Group<MRule3Atom> group = new Group<>();
		ICompilationUnit iCompilationUnit = (ICompilationUnit) arg0.getUnderlyingObject();
		CompilationUnit compilationUnit = UtilityClass.parse(iCompilationUnit);
		List<MRule3Atom> atoms = rule3AtomFinder.getMAtoms(compilationUnit);
		
		group.addAll(atoms);

		return group;
	}
}
