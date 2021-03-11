package compilationUnits.groups;

import java.util.List;

import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.dom.CompilationUnit;

import optionalanalizer.metamodel.entity.MCompilationUnit;
import optionalanalizer.metamodel.entity.MRule20sAntipattern;
import ro.lrg.xcore.metametamodel.Group;
import ro.lrg.xcore.metametamodel.IRelationBuilder;
import ro.lrg.xcore.metametamodel.RelationBuilder;
import rule20Antipattern.Rule20AntipatternFinder;
import utilities.UtilityClass;

@RelationBuilder
public class Rule20AntipatternDetector  implements IRelationBuilder<MRule20sAntipattern, MCompilationUnit>{

	@Override
	public Group<MRule20sAntipattern> buildGroup(MCompilationUnit arg0) {
		ICompilationUnit iCompilationUnit = (ICompilationUnit) arg0.getUnderlyingObject();
		Rule20AntipatternFinder rule20AntipatternFinder = new Rule20AntipatternFinder();
		CompilationUnit compilationUnit = UtilityClass.parse(iCompilationUnit);
		List<MRule20sAntipattern> antipatterns = rule20AntipatternFinder.getMAntipatterns(compilationUnit);
		Group<MRule20sAntipattern> group = new Group<>();
		group.addAll(antipatterns);
		return group;
	}
}