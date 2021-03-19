package compilationUnits.groups;

import java.util.List;

import optionalanalyzer.metamodel.entity.MCompilationUnit;
import optionalanalyzer.metamodel.entity.MUncategorizedIsPresentPossibleAntipattern;
import ro.lrg.xcore.metametamodel.Group;
import ro.lrg.xcore.metametamodel.IRelationBuilder;
import ro.lrg.xcore.metametamodel.RelationBuilder;
import uncategorizedIsPresentUsage.UncategorizedIsPresentAntipatternFinder;

@RelationBuilder
public class UncategorizedIsPresentInvocationBasedAntipatternDetector 
implements IRelationBuilder<MUncategorizedIsPresentPossibleAntipattern, MCompilationUnit>{

	@Override
	public Group<MUncategorizedIsPresentPossibleAntipattern> buildGroup(MCompilationUnit arg0) {
		MCompilationUnit mCompilationUnit = arg0;
		UncategorizedIsPresentAntipatternFinder uncategorizedIsPresentAntipatternFinder = new UncategorizedIsPresentAntipatternFinder();
		Group<MUncategorizedIsPresentPossibleAntipattern> group = new Group<>();
		List<MUncategorizedIsPresentPossibleAntipattern> antipatterns = uncategorizedIsPresentAntipatternFinder.getMAntipatterns(mCompilationUnit);
		
		group.addAll(antipatterns);

		return group;
	}
}