package rule_2Antipattern.properties;

import optionalanalyzer.metamodel.entity.MRule2sAntipattern;
import ro.lrg.xcore.metametamodel.IPropertyComputer;
import ro.lrg.xcore.metametamodel.PropertyComputer;

@PropertyComputer
public class ToString implements IPropertyComputer<String, MRule2sAntipattern>{

	@Override
	public String compute(MRule2sAntipattern arg0) {
		String relativePath = "TestProject\\rule2Examples\\";
		
		return relativePath + arg0.getUnderlyingObject().toString();
	}

}
