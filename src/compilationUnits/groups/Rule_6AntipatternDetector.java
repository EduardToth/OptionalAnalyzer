package compilationUnits.groups;

import java.util.List;

import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.dom.CompilationUnit;
import optionalanalizer.metamodel.entity.MCompilationUnit;
import optionalanalizer.metamodel.entity.MRule6sAntipattern;
import ro.lrg.xcore.metametamodel.Group;
import ro.lrg.xcore.metametamodel.IRelationBuilder;
import ro.lrg.xcore.metametamodel.RelationBuilder;
import rule_6Antipattern.Rule6AntipatternFinder;
import utilities.UtilityClass;

@RelationBuilder
public class Rule_6AntipatternDetector  implements IRelationBuilder<MRule6sAntipattern, MCompilationUnit>{

	@Override
	public Group<MRule6sAntipattern> buildGroup(MCompilationUnit arg0) {
		Rule6AntipatternFinder rule6AntipatternFinder = new Rule6AntipatternFinder();
		Group<MRule6sAntipattern> group = new Group<>();
		ICompilationUnit iCompilationUnit = (ICompilationUnit) arg0.getUnderlyingObject();
		CompilationUnit compilationUnit = UtilityClass.parse(iCompilationUnit);
		List<MRule6sAntipattern> antipatterns = rule6AntipatternFinder.getMAntipatterns(compilationUnit);
		
		group.addAll(antipatterns);

		return group;
	}
	
}
