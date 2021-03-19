package rule_9Antipattern.properties;

import optionalanalyzer.metamodel.entity.MRule9sAntipattern;
import ro.lrg.xcore.metametamodel.IPropertyComputer;
import ro.lrg.xcore.metametamodel.PropertyComputer;

@PropertyComputer
public class ToString implements IPropertyComputer<String, MRule9sAntipattern>{

	@Override
	public String compute(MRule9sAntipattern arg0) {
		return arg0.getUnderlyingObject().toString();
	}

}
