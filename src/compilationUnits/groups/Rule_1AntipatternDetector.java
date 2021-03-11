package compilationUnits.groups;

import java.util.List;

import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.dom.CompilationUnit;

import optionalanalizer.metamodel.entity.MCompilationUnit;
import optionalanalizer.metamodel.entity.MRule1sAntipattern;
import ro.lrg.xcore.metametamodel.Group;
import ro.lrg.xcore.metametamodel.IRelationBuilder;
import ro.lrg.xcore.metametamodel.RelationBuilder;
import rule_1Antipattern.Rule1AntipatternFinder;
import utilities.UtilityClass;

@RelationBuilder
public class Rule_1AntipatternDetector implements IRelationBuilder<MRule1sAntipattern, MCompilationUnit>{

	@Override
	public Group<MRule1sAntipattern> buildGroup(MCompilationUnit arg0) {
		Rule1AntipatternFinder rule1AntipatternFilder = new Rule1AntipatternFinder();
		ICompilationUnit iCompilationUnit = (ICompilationUnit) arg0.getUnderlyingObject();
		CompilationUnit compilationUnit = UtilityClass.parse(iCompilationUnit);
		List<MRule1sAntipattern> rule1Antipatterns = rule1AntipatternFilder.getMAntipatterns(compilationUnit);
		Group<MRule1sAntipattern> group = new Group<>();
		
		group.addAll(rule1Antipatterns);
		
		return group;
	}
}



