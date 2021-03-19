package compilationUnits.groups;

import java.util.List;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.dom.CompilationUnit;
import optionalanalyzer.metamodel.entity.MCompilationUnit;
import optionalanalyzer.metamodel.entity.MRule8sAntipattern;
import ro.lrg.xcore.metametamodel.Group;
import ro.lrg.xcore.metametamodel.IRelationBuilder;
import ro.lrg.xcore.metametamodel.RelationBuilder;
import rule_8Antipattern.Rule8AntipatternFinder;
import utilities.UtilityClass;

@RelationBuilder
public class Rule_8AntipatternDetector implements IRelationBuilder<MRule8sAntipattern, MCompilationUnit>{

	@Override
	public Group<MRule8sAntipattern> buildGroup(MCompilationUnit arg0) {
		Rule8AntipatternFinder rule8AntipatternFinder = new Rule8AntipatternFinder();
		Group<MRule8sAntipattern> group = new Group<>();
		ICompilationUnit iCompilationUnit = (ICompilationUnit) arg0.getUnderlyingObject();
		CompilationUnit compilationUnit = UtilityClass.parse(iCompilationUnit);
		List<MRule8sAntipattern> antipatterns = rule8AntipatternFinder.getMAntipatterns(compilationUnit);
		
		group.addAll(antipatterns);

		return group;
	}
}