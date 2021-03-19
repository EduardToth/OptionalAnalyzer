package compilationUnits.groups;

import java.util.List;

import org.eclipse.jdt.core.ICompilationUnit;

import optionalanalyzer.metamodel.entity.MCompilationUnit;
import optionalanalyzer.metamodel.entity.MRule14sAntipattern;
import ro.lrg.xcore.metametamodel.Group;
import ro.lrg.xcore.metametamodel.IRelationBuilder;
import ro.lrg.xcore.metametamodel.RelationBuilder;
import rule14Antipattern.Rule14AntipatternFinder;
import utilities.UtilityClass;

@RelationBuilder
public class Rule14AntipatternDetector  implements IRelationBuilder<MRule14sAntipattern, MCompilationUnit>{

	@Override
	public Group<MRule14sAntipattern> buildGroup(MCompilationUnit arg0) {
		ICompilationUnit iCompilationUnit = (ICompilationUnit) arg0.getUnderlyingObject();
		Rule14AntipatternFinder rule14AntipatternFinder = new Rule14AntipatternFinder();
		List<MRule14sAntipattern> antipatterns = rule14AntipatternFinder.getMAntipatterns(UtilityClass.parse(iCompilationUnit));
		Group<MRule14sAntipattern> group = new Group<>();
		group.addAll(antipatterns);
		return group;
	}
}