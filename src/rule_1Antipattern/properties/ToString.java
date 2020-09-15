package rule_1Antipattern.properties;

import optionalanalizer.metamodel.entity.MRule1Atom;
import ro.lrg.xcore.metametamodel.IPropertyComputer;
import ro.lrg.xcore.metametamodel.PropertyComputer;

@PropertyComputer
public class ToString implements IPropertyComputer<String, MRule1Atom>{

	@Override
	public String compute(MRule1Atom arg0) {
		return arg0.getUnderlyingObject().toString();
	}

}