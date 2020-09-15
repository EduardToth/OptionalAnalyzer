package compilationUnits.groups;

import java.util.List;

import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.dom.CompilationUnit;
import optionalanalizer.metamodel.entity.MCompilationUnit;
import optionalanalizer.metamodel.entity.MRule5Atom;
import ro.lrg.xcore.metametamodel.Group;
import ro.lrg.xcore.metametamodel.IRelationBuilder;
import ro.lrg.xcore.metametamodel.RelationBuilder;
import rule_5Antipattern.Rule5AtomFinder;
import utilities.UtilityClass;

@RelationBuilder
public class Rule_5AntipatternBuilder  implements IRelationBuilder<MRule5Atom, MCompilationUnit>{

	@Override
	public Group<MRule5Atom> buildGroup(MCompilationUnit arg0) {
		Rule5AtomFinder rule5AtomFinder = new Rule5AtomFinder();
		Group<MRule5Atom> group = new Group<>();
		ICompilationUnit iCompilationUnit = (ICompilationUnit) arg0.getUnderlyingObject();
		CompilationUnit compilationUnit = UtilityClass.parse(iCompilationUnit);
		List<MRule5Atom> atoms = rule5AtomFinder.getMAtoms(compilationUnit);
		
		group.addAll(atoms);

		return group;
	}
	
}
