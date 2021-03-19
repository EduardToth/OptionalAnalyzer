package rule_5Antipattern.properties;

import optionalanalyzer.metamodel.entity.MRule5sAntipattern;
import ro.lrg.xcore.metametamodel.IPropertyComputer;
import ro.lrg.xcore.metametamodel.PropertyComputer;

@PropertyComputer
public class ToString implements IPropertyComputer<String, MRule5sAntipattern>{

	@Override
	public String compute(MRule5sAntipattern arg0) {
		return arg0.getUnderlyingObject().toString();
	}

}
