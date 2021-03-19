package rule14Antipattern.properties;

import optionalanalyzer.metamodel.entity.MRule14sAntipattern;
import ro.lrg.xcore.metametamodel.IPropertyComputer;
import ro.lrg.xcore.metametamodel.PropertyComputer;

@PropertyComputer
public class ToString implements IPropertyComputer<String, MRule14sAntipattern>{

	@Override
	public String compute(MRule14sAntipattern arg0) {
		return arg0.getUnderlyingObject().toString();
	}

}
