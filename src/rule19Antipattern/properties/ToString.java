package rule19Antipattern.properties;

import optionalanalyzer.metamodel.entity.MRule19sAntipattern;
import ro.lrg.xcore.metametamodel.IPropertyComputer;
import ro.lrg.xcore.metametamodel.PropertyComputer;

@PropertyComputer
public class ToString implements IPropertyComputer<String, MRule19sAntipattern>{

	@Override
	public String compute(MRule19sAntipattern arg0) {
		return arg0.getUnderlyingObject().toString();
	}

}
