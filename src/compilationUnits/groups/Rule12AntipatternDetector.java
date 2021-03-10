package compilationUnits.groups;

import java.util.List;

import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.dom.CompilationUnit;

import optionalanalizer.metamodel.entity.MCompilationUnit;
import optionalanalizer.metamodel.entity.MRule12Atom;
import ro.lrg.xcore.metametamodel.Group;
import ro.lrg.xcore.metametamodel.IRelationBuilder;
import ro.lrg.xcore.metametamodel.RelationBuilder;
import rule12Antipattern.Rule12AntipatternFinder;
import utilities.UtilityClass;

@RelationBuilder
public class Rule12AntipatternDetector implements IRelationBuilder<MRule12Atom, MCompilationUnit>{

	@Override
	public Group<MRule12Atom> buildGroup(MCompilationUnit arg0) {
		Rule12AntipatternFinder rule12AtomFilder = new Rule12AntipatternFinder();
		ICompilationUnit iCompilationUnit = (ICompilationUnit) arg0.getUnderlyingObject();
		CompilationUnit compilationUnit = UtilityClass.parse(iCompilationUnit);
		List<MRule12Atom> rule12Antipatterns = rule12AtomFilder.getMAtoms(compilationUnit);
		Group<MRule12Atom> group = new Group<>();
		
		group.addAll(rule12Antipatterns);
		
		return group;
	}


}