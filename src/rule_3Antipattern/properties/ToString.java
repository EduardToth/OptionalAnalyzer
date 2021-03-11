package rule_3Antipattern.properties;

import optionalanalizer.metamodel.entity.MRule3sAntipattern;
import ro.lrg.xcore.metametamodel.IPropertyComputer;
import ro.lrg.xcore.metametamodel.PropertyComputer;

@PropertyComputer
public class ToString implements IPropertyComputer<String, MRule3sAntipattern>{


	@Override
	public String compute(MRule3sAntipattern arg0) {
		// TODO Auto-generated method stub
		return arg0.getUnderlyingObject().toString();
	}

	

}
