package compilationUnits.groups;

import java.util.List;

import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.dom.CompilationUnit;
import optionalanalizer.metamodel.entity.MCompilationUnit;
import optionalanalizer.metamodel.entity.MRule4sAntipattern;
import ro.lrg.xcore.metametamodel.Group;
import ro.lrg.xcore.metametamodel.IRelationBuilder;
import ro.lrg.xcore.metametamodel.RelationBuilder;
import rule_4Antipattern.Rule4AntipatternFinder;
import utilities.UtilityClass;

@RelationBuilder
public class Rule_4AntipatternDetector implements IRelationBuilder<MRule4sAntipattern, MCompilationUnit>{

	@Override
	public Group<MRule4sAntipattern> buildGroup(MCompilationUnit arg0) {
		Rule4AntipatternFinder rule4AntipatternFinder = new Rule4AntipatternFinder();
		Group<MRule4sAntipattern> group = new Group<>();
		ICompilationUnit iCompilationUnit = (ICompilationUnit) arg0.getUnderlyingObject();
		CompilationUnit compilationUnit = UtilityClass.parse(iCompilationUnit);
		List<MRule4sAntipattern> antipatterns = rule4AntipatternFinder.getMAntipatterns(compilationUnit);
		
		group.addAll(antipatterns);

		return group;
	}

}
