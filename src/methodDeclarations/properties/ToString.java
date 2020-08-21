package methodDeclarations.properties;

import org.eclipse.jdt.core.dom.MethodDeclaration;

import optionalanalizer.metamodel.entity.MDeclaration;
import ro.lrg.xcore.metametamodel.IPropertyComputer;
import ro.lrg.xcore.metametamodel.PropertyComputer;

@PropertyComputer
public class ToString implements IPropertyComputer<String, MDeclaration>{

	@Override
	public String compute(MDeclaration arg0) {
		MethodDeclaration methodDeclaration = (MethodDeclaration) arg0.getUnderlyingObject();
		return methodDeclaration.getName().toString();
		
	}

}
