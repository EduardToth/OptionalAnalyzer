package rule12Antipattern.properties;

import optionalanalyzer.metamodel.entity.MRule12sAntipattern;
import ro.lrg.xcore.metametamodel.IPropertyComputer;
import ro.lrg.xcore.metametamodel.PropertyComputer;

@PropertyComputer
public class ToString implements IPropertyComputer<String, MRule12sAntipattern>{

	@Override
	public String compute(MRule12sAntipattern arg0) {
		return arg0.getUnderlyingObject().toString();
	}

}
