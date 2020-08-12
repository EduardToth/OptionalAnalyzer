package methodInvocations.properties;

import optionalanalizer.metamodel.entity.MInvocation;
import ro.lrg.xcore.metametamodel.IPropertyComputer;
import ro.lrg.xcore.metametamodel.PropertyComputer;

@PropertyComputer
public class ToString implements IPropertyComputer<String, MInvocation>{

	@Override
	public String compute(MInvocation arg0) {
		return new InvocationToOptionalsPresent().compute(arg0);
	}

}
