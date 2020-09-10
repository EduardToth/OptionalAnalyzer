package methodInvocations.properties;

import org.eclipse.jdt.core.dom.MethodInvocation;

import optionalanalizer.metamodel.entity.MInvocation;
import ro.lrg.xcore.metametamodel.IPropertyComputer;
import ro.lrg.xcore.metametamodel.PropertyComputer;

@PropertyComputer
public class ToString implements IPropertyComputer<String, MInvocation>{

	@Override
	public String compute(MInvocation arg0) {
		MethodInvocation methodInvocation = (MethodInvocation) arg0.getUnderlyingObject();
		
		return methodInvocation.toString();
	}

}
