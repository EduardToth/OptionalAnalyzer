package compilationUnits.groups;

import java.util.List;
import java.util.stream.Collectors;

import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.dom.CompilationUnit;

import optionalanalizer.metamodel.entity.MCompilationUnit;
import optionalanalizer.metamodel.entity.MRule9Atom;
import optionalanalizer.metamodel.factory.Factory;
import ro.lrg.xcore.metametamodel.Group;
import ro.lrg.xcore.metametamodel.IRelationBuilder;
import ro.lrg.xcore.metametamodel.RelationBuilder;
import rule_7Antipattern.Rule7Atom;
import rule_9Antipattern.Rule9Atom;
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
		
		group.addAll(filterResult(atoms, arg0));
		
		return group;
	}
	
	/*
	 * There is a problem with the rule 9 algorithm. It detects the antipatterns but it cannot make
	 * any difference between rule 9 antipattern and rule 7 antipattern, so I have to filter the result.
	 * I know that it is not the best method to do it, but the algorithm for rule 9 is complicated and 
	 * combined with the rule 7 antipattern would be a nightmare, so I decided to make a compromise and filter the result
	 * using the rule 7 antipattern.
	 */
	private List<MRule9Atom> filterResult(List<MRule9Atom> rawAtoms,
			MCompilationUnit mCompilationUnit) {
		List<Rule7Atom> rule7List = mCompilationUnit.rule_7AntipatternBuilder()
				.getElements()
				.stream()
				.map(atom -> (Rule7Atom)atom.getUnderlyingObject())
				.collect(Collectors.toList());
		
		List<Rule9Atom> rule9List = rawAtoms.stream()
				.map(atom -> (Rule9Atom)atom.getUnderlyingObject())
				.collect(Collectors.toList());
		
		rule9List.removeAll(rule7List);
	
		return rule9List.stream()
				.map(atom -> Factory.getInstance().createMRule9Atom(atom))
				.collect(Collectors.toList());
	}
}