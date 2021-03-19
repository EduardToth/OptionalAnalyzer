package rule_4Antipattern.properties;

import optionalanalyzer.metamodel.entity.MRule4sAntipattern;
import ro.lrg.xcore.metametamodel.IPropertyComputer;
import ro.lrg.xcore.metametamodel.PropertyComputer;

@PropertyComputer
public class ToString implements IPropertyComputer<String, MRule4sAntipattern>{

	@Override
	public String compute(MRule4sAntipattern arg0) {
		return arg0.getUnderlyingObject().toString();
	}

}
