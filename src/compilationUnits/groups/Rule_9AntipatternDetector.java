package compilationUnits.groups;

import java.util.List;
import java.util.stream.Collectors;

import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.dom.CompilationUnit;

import optionalanalizer.metamodel.entity.MCompilationUnit;
import optionalanalizer.metamodel.entity.MRule9sAntipattern;
import optionalanalizer.metamodel.factory.Factory;
import ro.lrg.xcore.metametamodel.Group;
import ro.lrg.xcore.metametamodel.IRelationBuilder;
import ro.lrg.xcore.metametamodel.RelationBuilder;
import rule_7Antipattern.Rule7Antipattern;
import rule_7Antipattern.Rule7AntipatternFinder;
import rule_9Antipattern.Rule9Antipattern;
import rule_9Antipattern.Rule9AntipatternFinder;
import utilities.Antipattern;
import utilities.UtilityClass;

@RelationBuilder
public class Rule_9AntipatternDetector implements IRelationBuilder<MRule9sAntipattern, MCompilationUnit>{

	@Override
	public Group<MRule9sAntipattern> buildGroup(MCompilationUnit arg0) {
		Rule9AntipatternFinder rule9AntipatternFinder = new Rule9AntipatternFinder();
		Group<MRule9sAntipattern> group = new Group<>();
		ICompilationUnit iCompilationUnit = (ICompilationUnit) arg0.getUnderlyingObject();
		CompilationUnit compilationUnit = UtilityClass.parse(iCompilationUnit);
		List<MRule9sAntipattern> antipatterns = rule9AntipatternFinder.getMAntipatterns(compilationUnit);
		group.addAll(antipatterns );
		
		return group;
	}
	
	/*
	 * There is a problem with the rule 9 algorithm. It detects the antipatterns but it cannot make
	 * any difference between rule 9 antipattern and rule 7 antipattern, so I have to filter the result.
	 * I know that it is not the best method to do it, but the algorithm for rule 9 is complicated and 
	 * combined with the rule 7 antipattern would be a nightmare, so I decided to make a compromise and filter the result
	 * using the rule 7 antipattern.
	 */
	private List<MRule9sAntipattern> filterResult(List<MRule9sAntipattern> rawAntipattern, CompilationUnit compilationUnit) {
		Rule7AntipatternFinder rule7AntipatternFinder = new Rule7AntipatternFinder();
		List<? extends Antipattern> rule7List = rule7AntipatternFinder.getMAntipatterns(compilationUnit)
				.stream()
				.map(antipattern -> (Rule7Antipattern)antipattern.getUnderlyingObject())
				.collect(Collectors.toList());
		
		System.out.println(rule7List);
		
		List<? extends Antipattern> rule9List = rawAntipattern.stream()
				.map(antipattern -> (Rule9Antipattern)antipattern.getUnderlyingObject())
				.collect(Collectors.toList());
		System.out.println(rule9List);
		
		rule9List.removeAll(rule9List);
	
		return rule9List.stream()
				.map(antipattern -> Factory.getInstance().createMRule9sAntipattern(antipattern))
				.collect(Collectors.toList());
	}
}