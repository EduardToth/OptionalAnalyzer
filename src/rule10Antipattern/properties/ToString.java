package rule10Antipattern.properties;

import optionalanalizer.metamodel.entity.MRule10sAntipattern;
import ro.lrg.xcore.metametamodel.IPropertyComputer;
import ro.lrg.xcore.metametamodel.PropertyComputer;

@PropertyComputer
public class ToString implements IPropertyComputer<String, MRule10sAntipattern>{

	@Override
	public String compute(MRule10sAntipattern arg0) {
		return arg0.getUnderlyingObject().toString();
	}

}
