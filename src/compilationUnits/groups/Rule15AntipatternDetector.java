package compilationUnits.groups;

import java.util.List;

import org.eclipse.jdt.core.ICompilationUnit;

import optionalanalyzer.metamodel.entity.MCompilationUnit;
import optionalanalyzer.metamodel.entity.MRule15sAntipattern;
import ro.lrg.xcore.metametamodel.Group;
import ro.lrg.xcore.metametamodel.IRelationBuilder;
import ro.lrg.xcore.metametamodel.RelationBuilder;
import rule15Antipattern.Rule15AntipatternFinder;
import utilities.UtilityClass;

@RelationBuilder
public class Rule15AntipatternDetector  implements IRelationBuilder<MRule15sAntipattern, MCompilationUnit>{

	@Override
	public Group<MRule15sAntipattern> buildGroup(MCompilationUnit arg0) {

		ICompilationUnit iCompilationUnit = (ICompilationUnit) arg0.getUnderlyingObject();
		Rule15AntipatternFinder rule15AntipatternFinder = new Rule15AntipatternFinder();
		List<MRule15sAntipattern> antipatterns = rule15AntipatternFinder.getMAntipatterns(UtilityClass.parse(iCompilationUnit));
		Group<MRule15sAntipattern> group = new Group<>();
		group.addAll(antipatterns);
		return group;
	}

}