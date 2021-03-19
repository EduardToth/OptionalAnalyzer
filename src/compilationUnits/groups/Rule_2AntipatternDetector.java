package compilationUnits.groups;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.dom.CompilationUnit;

import optionalanalyzer.metamodel.entity.MCompilationUnit;
import optionalanalyzer.metamodel.entity.MRule2sAntipattern;
import ro.lrg.xcore.metametamodel.Group;
import ro.lrg.xcore.metametamodel.IRelationBuilder;
import ro.lrg.xcore.metametamodel.RelationBuilder;
import rule_2Antipattern.Rule2Antipattern;
import rule_2Antipattern.Rule2AntipatternFinder;
import utilities.UtilityClass;

@RelationBuilder
public class Rule_2AntipatternDetector  implements IRelationBuilder<MRule2sAntipattern, MCompilationUnit>{

	@Override
	public Group<MRule2sAntipattern> buildGroup(MCompilationUnit arg0) {
		Rule2AntipatternFinder rule2AntipatternFinder = new Rule2AntipatternFinder();
		Group<MRule2sAntipattern> group = new Group<>();
		ICompilationUnit iCompilationUnit = (ICompilationUnit) arg0.getUnderlyingObject();
		CompilationUnit compilationUnit = UtilityClass.parse(iCompilationUnit);
		List<MRule2sAntipattern> antipatterns = rule2AntipatternFinder.getMAntipatterns(compilationUnit);
		
		group.addAll(antipatterns);

		return group;
	}

}
