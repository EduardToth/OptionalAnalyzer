package rule_8Antipattern.properties;

import optionalanalizer.metamodel.entity.MRule8sAntipattern;
import ro.lrg.xcore.metametamodel.IPropertyComputer;
import ro.lrg.xcore.metametamodel.PropertyComputer;

@PropertyComputer
public class ToString implements IPropertyComputer<String, MRule8sAntipattern>{

	@Override
	public String compute(MRule8sAntipattern arg0) {
		return arg0.getUnderlyingObject().toString();
	}

}
