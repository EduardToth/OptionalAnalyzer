package rule26Antipattern.properties;

import optionalanalizer.metamodel.entity.MRule26sAntipattern;
import ro.lrg.xcore.metametamodel.IPropertyComputer;
import ro.lrg.xcore.metametamodel.PropertyComputer;

@PropertyComputer
public class ToString implements IPropertyComputer<String, MRule26sAntipattern>{

	@Override
	public String compute(MRule26sAntipattern arg0) {
		return arg0.getUnderlyingObject().toString();
	}

}
