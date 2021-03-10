package compilationUnits.groups;

import java.util.List;

import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.dom.CompilationUnit;

import optionalanalizer.metamodel.entity.MCompilationUnit;
import optionalanalizer.metamodel.entity.MRule10Atom;
import ro.lrg.xcore.metametamodel.Group;
import ro.lrg.xcore.metametamodel.IRelationBuilder;
import ro.lrg.xcore.metametamodel.RelationBuilder;
import rule10Antipattern.Rule10AntipatternFinder;
import utilities.UtilityClass;

@RelationBuilder
public class Rule10AntipatternDetector implements IRelationBuilder<MRule10Atom, MCompilationUnit>{

	@Override
	public Group<MRule10Atom> buildGroup(MCompilationUnit arg0) {
		Rule10AntipatternFinder rule10AtomFinder = new Rule10AntipatternFinder();
		Group<MRule10Atom> group = new Group<>();
		ICompilationUnit iCompilationUnit = (ICompilationUnit) arg0.getUnderlyingObject();
		CompilationUnit compilationUnit = UtilityClass.parse(iCompilationUnit);
		List<MRule10Atom> atoms = rule10AtomFinder.getMAtoms(compilationUnit);
		
		group.addAll(atoms);

		return group;
	}	
}