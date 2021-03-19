package rule_1Antipattern.properties;

import optionalanalyzer.metamodel.entity.MRule1sAntipattern;
import ro.lrg.xcore.metametamodel.IPropertyComputer;
import ro.lrg.xcore.metametamodel.PropertyComputer;

@PropertyComputer
public class ToString implements IPropertyComputer<String, MRule1sAntipattern>{

	@Override
	public String compute(MRule1sAntipattern arg0) {
		return arg0.getUnderlyingObject().toString();
	}

}