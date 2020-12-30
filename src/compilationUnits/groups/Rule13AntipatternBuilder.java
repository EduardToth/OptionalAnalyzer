package compilationUnits.groups;

import java.util.List;

import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.dom.CompilationUnit;

import optionalanalizer.metamodel.entity.MCompilationUnit;
import optionalanalizer.metamodel.entity.MRule13Atom;
import ro.lrg.xcore.metametamodel.Group;
import ro.lrg.xcore.metametamodel.IRelationBuilder;
import ro.lrg.xcore.metametamodel.RelationBuilder;
import rule13Antipattern.Rule13AtomFinder;
import utilities.UtilityClass;

@RelationBuilder
public class Rule13AntipatternBuilder implements IRelationBuilder<MRule13Atom, MCompilationUnit>{

	@Override
	public Group<MRule13Atom> buildGroup(MCompilationUnit arg0) {
		Rule13AtomFinder rule13AtomFinder = new Rule13AtomFinder();
		Group<MRule13Atom> group = new Group<>();
		ICompilationUnit iCompilationUnit = (ICompilationUnit) arg0.getUnderlyingObject();
		CompilationUnit compilationUnit = UtilityClass.parse(iCompilationUnit);
		List<MRule13Atom> atoms = rule13AtomFinder.getMAtoms(compilationUnit);
		
		group.addAll(atoms);

		return group;
	}	
}