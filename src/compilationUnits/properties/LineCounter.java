package compilationUnits.properties;

import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.JavaModelException;

import optionalanalyzer.metamodel.entity.MCompilationUnit;
import ro.lrg.xcore.metametamodel.IPropertyComputer;
import ro.lrg.xcore.metametamodel.PropertyComputer;


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
		int lineNumber = 0;
		
		try {
			String source = iCompilationUnit.getSource();
			lineNumber = getNumberOfLineSeparators(source);
		} catch (JavaModelException e) {
			
			e.printStackTrace();
		}
		
		return lineNumber + 1;
	}
}

