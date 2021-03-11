package rule20Antipattern.properties;

import optionalanalizer.metamodel.entity.MRule20sAntipattern;
import ro.lrg.xcore.metametamodel.IPropertyComputer;
import ro.lrg.xcore.metametamodel.PropertyComputer;

@PropertyComputer
public class ToString implements IPropertyComputer<String, MRule20sAntipattern>{

	@Override
	public String compute(MRule20sAntipattern arg0) {
		return arg0.getUnderlyingObject().toString();
	}

}
