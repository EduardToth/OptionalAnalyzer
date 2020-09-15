package compilationUnits.groups;

import java.util.List;

import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.dom.CompilationUnit;
import optionalanalizer.metamodel.entity.MCompilationUnit;
import optionalanalizer.metamodel.entity.MRule7Atom;
import ro.lrg.xcore.metametamodel.Group;
import ro.lrg.xcore.metametamodel.IRelationBuilder;
import ro.lrg.xcore.metametamodel.RelationBuilder;
import rule_7Antipattern.Rule7AtomFinder;
import utilities.UtilityClass;

@RelationBuilder
public class Rule_7AntipatternBuilder implements IRelationBuilder<MRule7Atom, MCompilationUnit>{

	@Override
	public Group<MRule7Atom> buildGroup(MCompilationUnit arg0) {
		Rule7AtomFinder rule7AtomFinder = new Rule7AtomFinder();
		Group<MRule7Atom> group = new Group<>();
		ICompilationUnit iCompilationUnit = (ICompilationUnit) arg0.getUnderlyingObject();
		CompilationUnit compilationUnit = UtilityClass.parse(iCompilationUnit);
		List<MRule7Atom> atoms = rule7AtomFinder.getMAtoms(compilationUnit);
		
		group.addAll(atoms);

		return group;
	}	
}