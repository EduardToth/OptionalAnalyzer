package compilationUnits.groups;

import java.util.List;

import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.dom.CompilationUnit;

import optionalanalizer.metamodel.entity.MCompilationUnit;
import optionalanalizer.metamodel.entity.MRule12sAntipattern;
import ro.lrg.xcore.metametamodel.Group;
import ro.lrg.xcore.metametamodel.IRelationBuilder;
import ro.lrg.xcore.metametamodel.RelationBuilder;
import rule12Antipattern.Rule12AntipatternFinder;
import utilities.UtilityClass;

@RelationBuilder
public class Rule12AntipatternDetector implements IRelationBuilder<MRule12sAntipattern, MCompilationUnit>{

	@Override
	public Group<MRule12sAntipattern> buildGroup(MCompilationUnit arg0) {
		Rule12AntipatternFinder rule12AntipatternFilder = new Rule12AntipatternFinder();
		ICompilationUnit iCompilationUnit = (ICompilationUnit) arg0.getUnderlyingObject();
		CompilationUnit compilationUnit = UtilityClass.parse(iCompilationUnit);
		List<MRule12sAntipattern> rule12Antipatterns = rule12AntipatternFilder.getMAntipatterns(compilationUnit);
		Group<MRule12sAntipattern> group = new Group<>();
		
		group.addAll(rule12Antipatterns);
		
		return group;
	}


}