package compilationUnits.groups;

import java.util.List;

import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.dom.CompilationUnit;

import optionalanalyzer.metamodel.entity.MCompilationUnit;
import optionalanalyzer.metamodel.entity.MRule10sAntipattern;
import ro.lrg.xcore.metametamodel.Group;
import ro.lrg.xcore.metametamodel.IRelationBuilder;
import ro.lrg.xcore.metametamodel.RelationBuilder;
import rule10Antipattern.Rule10AntipatternFinder;
import utilities.UtilityClass;

@RelationBuilder
public class Rule10AntipatternDetector implements IRelationBuilder<MRule10sAntipattern, MCompilationUnit>{

	@Override
	public Group<MRule10sAntipattern> buildGroup(MCompilationUnit arg0) {
		Rule10AntipatternFinder rule10AntipatternFinder = new Rule10AntipatternFinder();
		Group<MRule10sAntipattern> group = new Group<>();
		ICompilationUnit iCompilationUnit = (ICompilationUnit) arg0.getUnderlyingObject();
		CompilationUnit compilationUnit = UtilityClass.parse(iCompilationUnit);
		List<MRule10sAntipattern> antipatterns = rule10AntipatternFinder.getMAntipatterns(compilationUnit);
		
		group.addAll(antipatterns);

		return group;
	}	
}