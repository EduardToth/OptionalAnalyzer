package compilationUnits.groups;

import java.util.List;

import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.dom.CompilationUnit;

import optionalanalyzer.metamodel.entity.MCompilationUnit;
import optionalanalyzer.metamodel.entity.MRule25sAntipattern;
import ro.lrg.xcore.metametamodel.Group;
import ro.lrg.xcore.metametamodel.IRelationBuilder;
import ro.lrg.xcore.metametamodel.RelationBuilder;
import rule25Antipattern.Rule25AntipatternFinder;
import utilities.UtilityClass;

@RelationBuilder
public class Rule25AntipatternDetector implements IRelationBuilder<MRule25sAntipattern, MCompilationUnit>{

	@Override
	public Group<MRule25sAntipattern> buildGroup(MCompilationUnit arg0) {
		Rule25AntipatternFinder rule25AntipatternFinder = new Rule25AntipatternFinder();
		Group<MRule25sAntipattern> group = new Group<>();
		ICompilationUnit iCompilationUnit = (ICompilationUnit) arg0.getUnderlyingObject();
		CompilationUnit compilationUnit = UtilityClass.parse(iCompilationUnit);
		List<MRule25sAntipattern> antipatterns = rule25AntipatternFinder.getMAntipatterns(compilationUnit);
		
		group.addAll(antipatterns);

		return group;
	}

	
}