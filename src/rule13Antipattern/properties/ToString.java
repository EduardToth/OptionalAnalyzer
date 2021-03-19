package rule13Antipattern.properties;

import optionalanalyzer.metamodel.entity.MRule13sAntipattern;
import ro.lrg.xcore.metametamodel.IPropertyComputer;
import ro.lrg.xcore.metametamodel.PropertyComputer;

@PropertyComputer
public class ToString implements IPropertyComputer<String, MRule13sAntipattern>{

	@Override
	public String compute(MRule13sAntipattern arg0) {
		return arg0.getUnderlyingObject().toString();
	}

}
