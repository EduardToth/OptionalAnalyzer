package rule25Antipattern.properties;

import optionalanalyzer.metamodel.entity.MRule25sAntipattern;
import ro.lrg.xcore.metametamodel.IPropertyComputer;
import ro.lrg.xcore.metametamodel.PropertyComputer;

@PropertyComputer
public class ToString implements IPropertyComputer<String, MRule25sAntipattern>{

	@Override
	public String compute(MRule25sAntipattern arg0) {
		return arg0.getUnderlyingObject().toString();
	}

}
