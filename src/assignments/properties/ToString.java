package assignments.properties;

import optionalanalizer.metamodel.entity.MAssignment;
import ro.lrg.xcore.metametamodel.IPropertyComputer;
import ro.lrg.xcore.metametamodel.PropertyComputer;

@PropertyComputer
public class ToString implements IPropertyComputer<String, MAssignment>{

	@Override
	public String compute(MAssignment arg0) {
		return arg0.getUnderlyingObject().toString();
	}

}
