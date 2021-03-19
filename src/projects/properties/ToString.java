package projects.properties;

import org.eclipse.jdt.core.IJavaProject;

import optionalanalyzer.metamodel.entity.MProject;
import ro.lrg.xcore.metametamodel.IPropertyComputer;
import ro.lrg.xcore.metametamodel.PropertyComputer;

@PropertyComputer
public class ToString implements IPropertyComputer<String, MProject>{

	
	@Override
	public String compute(MProject arg0) {
		return ((IJavaProject)arg0.getUnderlyingObject()).getElementName();
	}
	
	
}
