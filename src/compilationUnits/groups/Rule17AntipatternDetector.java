package compilationUnits.groups;

import java.util.List;

import org.eclipse.jdt.core.ICompilationUnit;

import optionalanalizer.metamodel.entity.MCompilationUnit;
import optionalanalizer.metamodel.entity.MRule17sAntipattern;
import ro.lrg.xcore.metametamodel.Group;
import ro.lrg.xcore.metametamodel.IRelationBuilder;
import ro.lrg.xcore.metametamodel.RelationBuilder;
import rule17Antipattern.Rule17AntipatternFinder;
import utilities.UtilityClass;

@RelationBuilder
public class Rule17AntipatternDetector  implements IRelationBuilder<MRule17sAntipattern, MCompilationUnit>{

	@Override
	public Group<MRule17sAntipattern> buildGroup(MCompilationUnit arg0) {
		ICompilationUnit iCompilationUnit = (ICompilationUnit) arg0.getUnderlyingObject();
		Rule17AntipatternFinder rule17AntipatternFinder = new Rule17AntipatternFinder();
		List<MRule17sAntipattern> antipatterns = rule17AntipatternFinder.getMAntipatterns(UtilityClass.parse(iCompilationUnit));
		Group<MRule17sAntipattern> group = new Group<>();
		group.addAll(antipatterns);
		return group;
	}
}