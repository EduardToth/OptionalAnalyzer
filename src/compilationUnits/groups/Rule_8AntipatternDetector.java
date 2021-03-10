package compilationUnits.groups;

import java.util.List;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.dom.CompilationUnit;
import optionalanalizer.metamodel.entity.MCompilationUnit;
import optionalanalizer.metamodel.entity.MRule8Atom;
import ro.lrg.xcore.metametamodel.Group;
import ro.lrg.xcore.metametamodel.IRelationBuilder;
import ro.lrg.xcore.metametamodel.RelationBuilder;
import rule_8Antipattern.Rule8AntipatternFinder;
import utilities.UtilityClass;

@RelationBuilder
public class Rule_8AntipatternDetector implements IRelationBuilder<MRule8Atom, MCompilationUnit>{

	@Override
	public Group<MRule8Atom> buildGroup(MCompilationUnit arg0) {
		Rule8AntipatternFinder rule8AtomFinder = new Rule8AntipatternFinder();
		Group<MRule8Atom> group = new Group<>();
		ICompilationUnit iCompilationUnit = (ICompilationUnit) arg0.getUnderlyingObject();
		CompilationUnit compilationUnit = UtilityClass.parse(iCompilationUnit);
		List<MRule8Atom> atoms = rule8AtomFinder.getMAtoms(compilationUnit);
		
		group.addAll(atoms);

		return group;
	}
}