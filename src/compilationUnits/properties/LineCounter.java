package compilationUnits.properties;

import java.util.List;

import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.dom.CompilationUnit;

import optionalanalyzer.metamodel.entity.MCompilationUnit;
import optionalanalyzer.metamodel.entity.MRule1sAntipattern;
import ro.lrg.xcore.metametamodel.Group;
import ro.lrg.xcore.metametamodel.IPropertyComputer;
import ro.lrg.xcore.metametamodel.IRelationBuilder;
import ro.lrg.xcore.metametamodel.PropertyComputer;
import ro.lrg.xcore.metametamodel.RelationBuilder;
import rule_1Antipattern.Rule1AntipatternFinder;
import utilities.UtilityClass;


@PropertyComputer
public class LineCounter  implements IPropertyComputer<Integer, MCompilationUnit>{


	private int getNumberOfLineSeparators(String str) {
		int nrOfLineSeparators = 0;
		for(int i=0; i < str.length(); i++) {
			if(str.charAt(i) == '\n') {
				nrOfLineSeparators++;
			}
		}

		return nrOfLineSeparators;
	}
	@Override
	public Integer compute(MCompilationUnit arg0) {
		ICompilationUnit iCompilationUnit = (ICompilationUnit) arg0.getUnderlyingObject();
		CompilationUnit compilationUnit = UtilityClass.parse(iCompilationUnit);
		int lineNumber = getNumberOfLineSeparators(compilationUnit.toString());
		System.out.println(lineNumber);
		System.out.println(compilationUnit);

		return lineNumber + 1;
	}
}

