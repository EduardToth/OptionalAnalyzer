package rule16Antipattern.properties;


import optionalanalyzer.metamodel.entity.MRule16sAntipattern;
import ro.lrg.xcore.metametamodel.IPropertyComputer;
import ro.lrg.xcore.metametamodel.PropertyComputer;

@PropertyComputer
public class ToString implements IPropertyComputer<String, MRule16sAntipattern>{

	@Override
	public String compute(MRule16sAntipattern arg0) {
		return arg0.getUnderlyingObject().toString();
	}

}
