package projects.properties;

import org.eclipse.core.resources.IProject;
import org.eclipse.jdt.core.IJavaProject;

import optionalanalizer.metamodel.entity.MProject;
import ro.lrg.xcore.metametamodel.IPropertyComputer;
import ro.lrg.xcore.metametamodel.PropertyComputer;

@PropertyComputer
public class ToString implements IPropertyComputer<String, MProject>{

	
	@Override
	public String compute(MProject arg0) {
		return ((IJavaProject)arg0.getUnderlyingObject()).getElementName();
	}
	
	
}
