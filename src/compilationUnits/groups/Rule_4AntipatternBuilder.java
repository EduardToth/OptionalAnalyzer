package compilationUnits.groups;

import java.util.List;

import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.dom.CompilationUnit;
import optionalanalizer.metamodel.entity.MCompilationUnit;
import optionalanalizer.metamodel.entity.MRule4Atom;
import ro.lrg.xcore.metametamodel.Group;
import ro.lrg.xcore.metametamodel.IRelationBuilder;
import ro.lrg.xcore.metametamodel.RelationBuilder;
import rule_4Antipattern.Rule4AtomFinder;
import utilities.UtilityClass;

@RelationBuilder
public class Rule_4AntipatternBuilder implements IRelationBuilder<MRule4Atom, MCompilationUnit>{

	@Override
	public Group<MRule4Atom> buildGroup(MCompilationUnit arg0) {
		Rule4AtomFinder rule4AtomFinder = new Rule4AtomFinder();
		Group<MRule4Atom> group = new Group<>();
		ICompilationUnit iCompilationUnit = (ICompilationUnit) arg0.getUnderlyingObject();
		CompilationUnit compilationUnit = UtilityClass.parse(iCompilationUnit);
		List<MRule4Atom> atoms = rule4AtomFinder.getMAtoms(compilationUnit);
		
		group.addAll(atoms);

		return group;
	}

}
