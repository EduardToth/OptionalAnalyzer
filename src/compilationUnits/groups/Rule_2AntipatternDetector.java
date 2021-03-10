package compilationUnits.groups;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.dom.CompilationUnit;

import optionalanalizer.metamodel.entity.MCompilationUnit;
import optionalanalizer.metamodel.entity.MRule2Atom;
import optionalanalizer.metamodel.factory.Factory;
import ro.lrg.xcore.metametamodel.Group;
import ro.lrg.xcore.metametamodel.IRelationBuilder;
import ro.lrg.xcore.metametamodel.RelationBuilder;
import rule_2Antipattern.Rule2Antipattern;
import rule_2Antipattern.Rule2AntipatternFinder;
import utilities.UtilityClass;

@RelationBuilder
public class Rule_2AntipatternDetector  implements IRelationBuilder<MRule2Atom, MCompilationUnit>{

	@Override
	public Group<MRule2Atom> buildGroup(MCompilationUnit arg0) {
		Rule2AntipatternFinder rule2AtomFinder = new Rule2AntipatternFinder();
		Group<MRule2Atom> group = new Group<>();
		ICompilationUnit iCompilationUnit = (ICompilationUnit) arg0.getUnderlyingObject();
		CompilationUnit compilationUnit = UtilityClass.parse(iCompilationUnit);
		List<MRule2Atom> atoms = rule2AtomFinder.getMAtoms(compilationUnit);
		
		group.addAll(atoms);

		return group;
	}

}
