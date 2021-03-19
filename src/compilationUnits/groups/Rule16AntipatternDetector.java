package compilationUnits.groups;

import java.util.List;

import org.eclipse.jdt.core.ICompilationUnit;

import optionalanalyzer.metamodel.entity.MCompilationUnit;
import optionalanalyzer.metamodel.entity.MRule16sAntipattern;
import ro.lrg.xcore.metametamodel.Group;
import ro.lrg.xcore.metametamodel.IRelationBuilder;
import ro.lrg.xcore.metametamodel.RelationBuilder;
import rule16Antipattern.Rule16AntipatternFinder;
import utilities.UtilityClass;

@RelationBuilder
public class Rule16AntipatternDetector  implements IRelationBuilder<MRule16sAntipattern, MCompilationUnit>{

	@Override
	public Group<MRule16sAntipattern> buildGroup(MCompilationUnit arg0) {
		ICompilationUnit iCompilationUnit = (ICompilationUnit) arg0.getUnderlyingObject();
		Rule16AntipatternFinder rule16AntipatternFinder = new Rule16AntipatternFinder();
		List<MRule16sAntipattern> antipatterns = rule16AntipatternFinder.getMAntipatterns(UtilityClass.parse(iCompilationUnit));
		Group<MRule16sAntipattern> group = new Group<>();
		group.addAll(antipatterns);
		return group;
	}


}