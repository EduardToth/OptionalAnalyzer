package rule17Antipattern.properties;

import optionalanalyzer.metamodel.entity.MRule17sAntipattern;
import ro.lrg.xcore.metametamodel.IPropertyComputer;
import ro.lrg.xcore.metametamodel.PropertyComputer;

@PropertyComputer
public class ToString implements IPropertyComputer<String, MRule17sAntipattern>{

	@Override
	public String compute(MRule17sAntipattern arg0) {
		return arg0.getUnderlyingObject().toString();
	}

}
