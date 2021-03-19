package rule_7Antipattern.properties;

import optionalanalyzer.metamodel.entity.MRule7sAntipattern;
import ro.lrg.xcore.metametamodel.IPropertyComputer;
import ro.lrg.xcore.metametamodel.PropertyComputer;

@PropertyComputer
public class ToString implements IPropertyComputer<String, MRule7sAntipattern>{

	@Override
	public String compute(MRule7sAntipattern arg0) {
		return arg0.getUnderlyingObject().toString();
	}

}
