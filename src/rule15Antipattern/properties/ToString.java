package rule15Antipattern.properties;

import optionalanalyzer.metamodel.entity.MRule15sAntipattern;
import ro.lrg.xcore.metametamodel.IPropertyComputer;
import ro.lrg.xcore.metametamodel.PropertyComputer;

@PropertyComputer
public class ToString implements IPropertyComputer<String, MRule15sAntipattern>{

	@Override
	public String compute(MRule15sAntipattern arg0) {
		return arg0.getUnderlyingObject().toString();
	}

}
