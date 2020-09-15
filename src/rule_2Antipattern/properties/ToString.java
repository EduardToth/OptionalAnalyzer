package rule_2Antipattern.properties;

import optionalanalizer.metamodel.entity.MRule2Atom;
import ro.lrg.xcore.metametamodel.IPropertyComputer;
import ro.lrg.xcore.metametamodel.PropertyComputer;

@PropertyComputer
public class ToString implements IPropertyComputer<String, MRule2Atom>{

	@Override
	public String compute(MRule2Atom arg0) {
		return arg0.getUnderlyingObject().toString();
	}

}
