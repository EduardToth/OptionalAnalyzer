package rule21Antipattern.properties;

import optionalanalizer.metamodel.entity.MRule21sAntipattern;
import ro.lrg.xcore.metametamodel.IPropertyComputer;
import ro.lrg.xcore.metametamodel.PropertyComputer;

@PropertyComputer
public class ToString implements IPropertyComputer<String, MRule21sAntipattern>{

	@Override
	public String compute(MRule21sAntipattern arg0) {
		return arg0.getUnderlyingObject().toString();
	}

}
