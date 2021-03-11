package compilationUnits.groups;

import java.util.List;

import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.dom.CompilationUnit;
import optionalanalizer.metamodel.entity.MCompilationUnit;
import optionalanalizer.metamodel.entity.MRule7sAntipattern;
import ro.lrg.xcore.metametamodel.Group;
import ro.lrg.xcore.metametamodel.IRelationBuilder;
import ro.lrg.xcore.metametamodel.RelationBuilder;
import rule_7Antipattern.Rule7AntipatternFinder;
import utilities.UtilityClass;

@RelationBuilder
public class Rule_7AntipatternDetector implements IRelationBuilder<MRule7sAntipattern, MCompilationUnit>{

	@Override
	public Group<MRule7sAntipattern> buildGroup(MCompilationUnit arg0) {
		Rule7AntipatternFinder rule7AntipatternFinder = new Rule7AntipatternFinder();
		Group<MRule7sAntipattern> group = new Group<>();
		ICompilationUnit iCompilationUnit = (ICompilationUnit) arg0.getUnderlyingObject();
		CompilationUnit compilationUnit = UtilityClass.parse(iCompilationUnit);
		List<MRule7sAntipattern> antipatterns = rule7AntipatternFinder.getMAntipatterns(compilationUnit);
		
		group.addAll(antipatterns);

		return group;
	}	
}