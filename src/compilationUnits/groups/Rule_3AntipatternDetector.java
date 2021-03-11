package compilationUnits.groups;

import java.util.List;

import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.dom.CompilationUnit;

import optionalanalizer.metamodel.entity.MCompilationUnit;
import optionalanalizer.metamodel.entity.MRule3sAntipattern;
import ro.lrg.xcore.metametamodel.Group;
import ro.lrg.xcore.metametamodel.IRelationBuilder;
import ro.lrg.xcore.metametamodel.RelationBuilder;
import rule_3Antipattern.Rule3AntipatternFinder;
import utilities.UtilityClass;

@RelationBuilder
public class Rule_3AntipatternDetector  implements IRelationBuilder<MRule3sAntipattern, MCompilationUnit>{

	@Override
	public Group<MRule3sAntipattern> buildGroup(MCompilationUnit arg0) {
		Rule3AntipatternFinder rule3AntipatternFinder = new Rule3AntipatternFinder();
		Group<MRule3sAntipattern> group = new Group<>();
		ICompilationUnit iCompilationUnit = (ICompilationUnit) arg0.getUnderlyingObject();
		CompilationUnit compilationUnit = UtilityClass.parse(iCompilationUnit);
		List<MRule3sAntipattern> antipatterns = rule3AntipatternFinder.getMAntipatterns(compilationUnit);
		
		group.addAll(antipatterns);

		return group;
	}
}
