package compilationUnits.groups;

import java.util.List;

import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.dom.CompilationUnit;

import optionalanalizer.metamodel.entity.MCompilationUnit;
import optionalanalizer.metamodel.entity.MRule26sAntipattern;
import ro.lrg.xcore.metametamodel.Group;
import ro.lrg.xcore.metametamodel.IRelationBuilder;
import ro.lrg.xcore.metametamodel.RelationBuilder;
import rule26Antipattern.Rule26AntipatternFinder;
import utilities.UtilityClass;

@RelationBuilder
public class Rule26AntipatternDetector implements IRelationBuilder<MRule26sAntipattern, MCompilationUnit>{

	@Override
	public Group<MRule26sAntipattern> buildGroup(MCompilationUnit arg0) {
		Rule26AntipatternFinder rule26AntipatternFinder = new Rule26AntipatternFinder();
		Group<MRule26sAntipattern> group = new Group<>();
		ICompilationUnit iCompilationUnit = (ICompilationUnit) arg0.getUnderlyingObject();
		CompilationUnit compilationUnit = UtilityClass.parse(iCompilationUnit);
		List<MRule26sAntipattern> antipatterns = rule26AntipatternFinder.getMAntipatterns(compilationUnit);
		
		group.addAll(antipatterns);

		return group;
	}
}