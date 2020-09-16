package compilationUnits.groups;

import java.util.List;

import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.dom.CompilationUnit;

import optionalanalizer.metamodel.entity.MCompilationUnit;
import optionalanalizer.metamodel.entity.MRule20Atom;
import ro.lrg.xcore.metametamodel.Group;
import ro.lrg.xcore.metametamodel.IRelationBuilder;
import ro.lrg.xcore.metametamodel.RelationBuilder;
import rule20Antipattern.Rule20AtomFinder;
import utilities.UtilityClass;

@RelationBuilder
public class Rule20AntipatternBuilder  implements IRelationBuilder<MRule20Atom, MCompilationUnit>{

	@Override
	public Group<MRule20Atom> buildGroup(MCompilationUnit arg0) {
		ICompilationUnit iCompilationUnit = (ICompilationUnit) arg0.getUnderlyingObject();
		Rule20AtomFinder rule20AtomFinder = new Rule20AtomFinder();
		CompilationUnit compilationUnit = UtilityClass.parse(iCompilationUnit);
		List<MRule20Atom> atoms = rule20AtomFinder.getMAtoms(compilationUnit);
		atoms.stream().forEach(el -> System.out.println("**************: " + el.getUnderlyingObject().toString()));
		Group<MRule20Atom> group = new Group<>();
		group.addAll(atoms);
		return group;
	}
}