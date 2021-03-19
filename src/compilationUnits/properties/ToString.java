package compilationUnits.properties;

import org.eclipse.jdt.core.ICompilationUnit;

import optionalanalyzer.metamodel.entity.MCompilationUnit;
import ro.lrg.xcore.metametamodel.IPropertyComputer;
import ro.lrg.xcore.metametamodel.PropertyComputer;

@PropertyComputer
public class ToString  implements IPropertyComputer<String, MCompilationUnit>{

	@Override
	public String compute(MCompilationUnit arg0) {
		return ((ICompilationUnit)arg0.getUnderlyingObject()).getElementName();
	}

}
