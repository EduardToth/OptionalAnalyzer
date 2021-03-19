package rule18Antipattern.properties;

import optionalanalyzer.metamodel.entity.MRule18sAntipattern;
import ro.lrg.xcore.metametamodel.IPropertyComputer;
import ro.lrg.xcore.metametamodel.PropertyComputer;

@PropertyComputer
public class ToString implements IPropertyComputer<String, MRule18sAntipattern>{

	@Override
	public String compute(MRule18sAntipattern arg0) {
		return arg0.getUnderlyingObject().toString();
	}

}
