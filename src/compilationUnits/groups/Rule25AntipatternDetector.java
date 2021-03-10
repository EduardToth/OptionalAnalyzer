package compilationUnits.groups;

import java.util.List;

import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.dom.CompilationUnit;

import optionalanalizer.metamodel.entity.MCompilationUnit;
import optionalanalizer.metamodel.entity.MRule25Atom;
import ro.lrg.xcore.metametamodel.Group;
import ro.lrg.xcore.metametamodel.IRelationBuilder;
import ro.lrg.xcore.metametamodel.RelationBuilder;
import rule25Antipattern.Rule25AntipatternFinder;
import utilities.UtilityClass;

@RelationBuilder
public class Rule25AntipatternDetector implements IRelationBuilder<MRule25Atom, MCompilationUnit>{

	@Override
	public Group<MRule25Atom> buildGroup(MCompilationUnit arg0) {
		Rule25AntipatternFinder rule25AtomFinder = new Rule25AntipatternFinder();
		Group<MRule25Atom> group = new Group<>();
		ICompilationUnit iCompilationUnit = (ICompilationUnit) arg0.getUnderlyingObject();
		CompilationUnit compilationUnit = UtilityClass.parse(iCompilationUnit);
		List<MRule25Atom> atoms = rule25AtomFinder.getMAtoms(compilationUnit);
		
		group.addAll(atoms);

		return group;
	}

	
}