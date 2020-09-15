package compilationUnits.groups;

import java.util.List;

import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.dom.CompilationUnit;
import optionalanalizer.metamodel.entity.MCompilationUnit;
import optionalanalizer.metamodel.entity.MRule6Atom;
import ro.lrg.xcore.metametamodel.Group;
import ro.lrg.xcore.metametamodel.IRelationBuilder;
import ro.lrg.xcore.metametamodel.RelationBuilder;
import rule_6Antipattern.Rule6AtomFinder;
import utilities.UtilityClass;

@RelationBuilder
public class Rule_6AntipatternBuilder  implements IRelationBuilder<MRule6Atom, MCompilationUnit>{

	@Override
	public Group<MRule6Atom> buildGroup(MCompilationUnit arg0) {
		Rule6AtomFinder rule6AtomFinder = new Rule6AtomFinder();
		Group<MRule6Atom> group = new Group<>();
		ICompilationUnit iCompilationUnit = (ICompilationUnit) arg0.getUnderlyingObject();
		CompilationUnit compilationUnit = UtilityClass.parse(iCompilationUnit);
		List<MRule6Atom> atoms = rule6AtomFinder.getMAtoms(compilationUnit);
		
		group.addAll(atoms);

		return group;
	}
	
}
