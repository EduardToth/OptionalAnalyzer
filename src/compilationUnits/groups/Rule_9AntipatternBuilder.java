package compilationUnits.groups;

import java.util.List;

import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.dom.CompilationUnit;
import optionalanalizer.metamodel.entity.MCompilationUnit;
import optionalanalizer.metamodel.entity.MRule9Atom;
import ro.lrg.xcore.metametamodel.Group;
import ro.lrg.xcore.metametamodel.IRelationBuilder;
import ro.lrg.xcore.metametamodel.RelationBuilder;
import rule_9Antipattern.Rule9AtomFinder;
import utilities.UtilityClass;

@RelationBuilder
public class Rule_9AntipatternBuilder implements IRelationBuilder<MRule9Atom, MCompilationUnit>{

	@Override
	public Group<MRule9Atom> buildGroup(MCompilationUnit arg0) {
		Rule9AtomFinder rule9AtomFinder = new Rule9AtomFinder();
		Group<MRule9Atom> group = new Group<>();
		ICompilationUnit iCompilationUnit = (ICompilationUnit) arg0.getUnderlyingObject();
		CompilationUnit compilationUnit = UtilityClass.parse(iCompilationUnit);
		List<MRule9Atom> atoms = rule9AtomFinder.getMAtoms(compilationUnit);
		
		group.addAll(atoms);
		
		return group;
	}
}