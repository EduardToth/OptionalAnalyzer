package compilationUnits.groups;

import java.util.List;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.dom.CompilationUnit;
import optionalanalizer.metamodel.entity.MCompilationUnit;
import optionalanalizer.metamodel.entity.MRule19Atom;
import ro.lrg.xcore.metametamodel.Group;
import ro.lrg.xcore.metametamodel.IRelationBuilder;
import ro.lrg.xcore.metametamodel.RelationBuilder;
import rule19Antipattern.Rule19AntipatternFinder;
import utilities.UtilityClass;


@RelationBuilder
public class Rule19AntipatternDetector  implements IRelationBuilder<MRule19Atom, MCompilationUnit>{

	@Override
	public Group<MRule19Atom> buildGroup(MCompilationUnit arg0) {
		ICompilationUnit iCompilationUnit = (ICompilationUnit) arg0.getUnderlyingObject();
		Rule19AntipatternFinder rule19AtomFinder = new Rule19AntipatternFinder();
		CompilationUnit compilationUnit = UtilityClass.parse(iCompilationUnit);
		List<MRule19Atom> atoms = rule19AtomFinder.getMAtoms(compilationUnit);
		Group<MRule19Atom> group = new Group<>();
		group.addAll(atoms);
		return group;
	}
}


