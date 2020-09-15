package compilationUnits.groups;

import java.util.List;

import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.dom.CompilationUnit;

import optionalanalizer.metamodel.entity.MCompilationUnit;
import optionalanalizer.metamodel.entity.MRule21Atom;
import ro.lrg.xcore.metametamodel.Group;
import ro.lrg.xcore.metametamodel.IRelationBuilder;
import ro.lrg.xcore.metametamodel.RelationBuilder;
import rule21Antipattern.Rule21AtomFinder;
import utilities.UtilityClass;

@RelationBuilder
public class Rule21AntipatternBuilder implements IRelationBuilder<MRule21Atom, MCompilationUnit>{

	@Override
	public Group<MRule21Atom> buildGroup(MCompilationUnit arg0) {
		Rule21AtomFinder rule21AtomFinder = new Rule21AtomFinder();
		Group<MRule21Atom> group = new Group<>();
		ICompilationUnit iCompilationUnit = (ICompilationUnit) arg0.getUnderlyingObject();
		CompilationUnit compilationUnit = UtilityClass.parse(iCompilationUnit);
		List<MRule21Atom> atoms = rule21AtomFinder.getMAtoms(compilationUnit);
		
		group.addAll(atoms);

		return group;
	}
}