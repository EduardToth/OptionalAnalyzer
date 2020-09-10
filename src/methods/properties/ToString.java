package methods.properties;

import org.eclipse.jdt.core.dom.MethodDeclaration;

import optionalanalizer.metamodel.entity.MMethod;
import ro.lrg.xcore.metametamodel.IPropertyComputer;
import ro.lrg.xcore.metametamodel.PropertyComputer;

@PropertyComputer
public class ToString implements IPropertyComputer<String, MMethod>{

	@Override
	public String compute(MMethod arg0) {
		MethodDeclaration methodDeclaration = (MethodDeclaration) arg0.getUnderlyingObject();
		return methodDeclaration.getName().toString();
	}

}