package compilationUnits.groups;

import java.util.List;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.dom.CompilationUnit;
import optionalanalizer.metamodel.entity.MCompilationUnit;
import optionalanalizer.metamodel.entity.MRule19sAntipattern;
import ro.lrg.xcore.metametamodel.Group;
import ro.lrg.xcore.metametamodel.IRelationBuilder;
import ro.lrg.xcore.metametamodel.RelationBuilder;
import rule19Antipattern.Rule19AntipatternFinder;
import utilities.UtilityClass;


@RelationBuilder
public class Rule19AntipatternDetector  implements IRelationBuilder<MRule19sAntipattern, MCompilationUnit>{

	@Override
	public Group<MRule19sAntipattern> buildGroup(MCompilationUnit arg0) {
		ICompilationUnit iCompilationUnit = (ICompilationUnit) arg0.getUnderlyingObject();
		Rule19AntipatternFinder rule19AntipatternFinder = new Rule19AntipatternFinder();
		CompilationUnit compilationUnit = UtilityClass.parse(iCompilationUnit);
		List<MRule19sAntipattern> antipatterns = rule19AntipatternFinder.getMAntipatterns(compilationUnit);
		Group<MRule19sAntipattern> group = new Group<>();
		group.addAll(antipatterns);
		return group;
	}
}


