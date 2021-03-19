package compilationUnits.groups;

import java.util.List;

import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.dom.CompilationUnit;

import optionalanalyzer.metamodel.entity.MCompilationUnit;
import optionalanalyzer.metamodel.entity.MRule21sAntipattern;
import ro.lrg.xcore.metametamodel.Group;
import ro.lrg.xcore.metametamodel.IRelationBuilder;
import ro.lrg.xcore.metametamodel.RelationBuilder;
import rule21Antipattern.Rule21AntipatternFinder;
import utilities.UtilityClass;

@RelationBuilder
public class Rule21AntipatternDetector implements IRelationBuilder<MRule21sAntipattern, MCompilationUnit>{

	@Override
	public Group<MRule21sAntipattern> buildGroup(MCompilationUnit arg0) {
		Rule21AntipatternFinder rule21AntipatternFinder = new Rule21AntipatternFinder();
		Group<MRule21sAntipattern> group = new Group<>();
		ICompilationUnit iCompilationUnit = (ICompilationUnit) arg0.getUnderlyingObject();
		CompilationUnit compilationUnit = UtilityClass.parse(iCompilationUnit);
		List<MRule21sAntipattern> antipatterns = rule21AntipatternFinder.getMAntipatterns(compilationUnit);
		
		group.addAll(antipatterns);

		return group;
	}
}