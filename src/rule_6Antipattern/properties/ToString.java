package rule_6Antipattern.properties;

import optionalanalizer.metamodel.entity.MRule6sAntipattern;
import ro.lrg.xcore.metametamodel.IPropertyComputer;
import ro.lrg.xcore.metametamodel.PropertyComputer;

@PropertyComputer
public class ToString implements IPropertyComputer<String, MRule6sAntipattern>{

	@Override
	public String compute(MRule6sAntipattern arg0) {
		return arg0.getUnderlyingObject().toString();
	}

}
