package rule_9Antipattern.properties;

import optionalanalizer.metamodel.entity.MRule9Atom;
import ro.lrg.xcore.metametamodel.IPropertyComputer;
import ro.lrg.xcore.metametamodel.PropertyComputer;

@PropertyComputer
public class ToString implements IPropertyComputer<String, MRule9Atom>{

	@Override
	public String compute(MRule9Atom arg0) {
		return arg0.getUnderlyingObject().toString();
	}

}
