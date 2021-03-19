package compilationUnits.groups;

import java.util.List;

import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.dom.CompilationUnit;

import optionalanalyzer.metamodel.entity.MCompilationUnit;
import optionalanalyzer.metamodel.entity.MRule5sAntipattern;
import ro.lrg.xcore.metametamodel.Group;
import ro.lrg.xcore.metametamodel.IRelationBuilder;
import ro.lrg.xcore.metametamodel.RelationBuilder;
import rule_5Antipattern.Rule5AntipatternFinder;
import utilities.UtilityClass;

@RelationBuilder
public class Rule_5AntipatternDetector  implements IRelationBuilder<MRule5sAntipattern, MCompilationUnit>{

	@Override
	public Group<MRule5sAntipattern> buildGroup(MCompilationUnit arg0) {
		Rule5AntipatternFinder rule5AntipatternFinder = new Rule5AntipatternFinder();
		Group<MRule5sAntipattern> group = new Group<>();
		ICompilationUnit iCompilationUnit = (ICompilationUnit) arg0.getUnderlyingObject();
		CompilationUnit compilationUnit = UtilityClass.parse(iCompilationUnit);
		List<MRule5sAntipattern> antipatterns = rule5AntipatternFinder.getMAntipatterns(compilationUnit);
		
		group.addAll(antipatterns);

		return group;
	}
	
}
