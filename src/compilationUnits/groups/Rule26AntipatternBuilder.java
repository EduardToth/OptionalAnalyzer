package compilationUnits.groups;

import java.util.List;

import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.dom.CompilationUnit;

import optionalanalizer.metamodel.entity.MCompilationUnit;
import optionalanalizer.metamodel.entity.MRule26Atom;
import ro.lrg.xcore.metametamodel.Group;
import ro.lrg.xcore.metametamodel.IRelationBuilder;
import ro.lrg.xcore.metametamodel.RelationBuilder;
import rule26Antipattern.Rule26AtomFinder;
import utilities.UtilityClass;

@RelationBuilder
public class Rule26AntipatternBuilder implements IRelationBuilder<MRule26Atom, MCompilationUnit>{

	@Override
	public Group<MRule26Atom> buildGroup(MCompilationUnit arg0) {
		Rule26AtomFinder rule26AtomFinder = new Rule26AtomFinder();
		Group<MRule26Atom> group = new Group<>();
		ICompilationUnit iCompilationUnit = (ICompilationUnit) arg0.getUnderlyingObject();
		CompilationUnit compilationUnit = UtilityClass.parse(iCompilationUnit);
		List<MRule26Atom> atoms = rule26AtomFinder.getMAtoms(compilationUnit);
		
		group.addAll(atoms);

		return group;
	}
}