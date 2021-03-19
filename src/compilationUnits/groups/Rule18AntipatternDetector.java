package compilationUnits.groups;

import java.util.List;

import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.dom.CompilationUnit;

import optionalanalyzer.metamodel.entity.MCompilationUnit;
import optionalanalyzer.metamodel.entity.MRule18sAntipattern;
import ro.lrg.xcore.metametamodel.Group;
import ro.lrg.xcore.metametamodel.IRelationBuilder;
import ro.lrg.xcore.metametamodel.RelationBuilder;
import rule18Antipattern.Rule18AntipatternFinder;
import utilities.UtilityClass;


@RelationBuilder
public class Rule18AntipatternDetector  implements IRelationBuilder<MRule18sAntipattern, MCompilationUnit>{

	@Override
	public Group<MRule18sAntipattern> buildGroup(MCompilationUnit arg0) {
		ICompilationUnit iCompilationUnit = (ICompilationUnit) arg0.getUnderlyingObject();
		Rule18AntipatternFinder rule18AntipatternFinder = new Rule18AntipatternFinder();
		CompilationUnit compilationUnit = UtilityClass.parse(iCompilationUnit);
		List<MRule18sAntipattern> antipatterns = rule18AntipatternFinder.getMAntipatterns(compilationUnit);
		Group<MRule18sAntipattern> group = new Group<>();
		group.addAll(antipatterns);
		return group;
	}
}