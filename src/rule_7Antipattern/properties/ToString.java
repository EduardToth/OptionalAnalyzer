package rule_7Antipattern.properties;

import optionalanalizer.metamodel.entity.MRule7Atom;
import ro.lrg.xcore.metametamodel.IPropertyComputer;
import ro.lrg.xcore.metametamodel.PropertyComputer;

@PropertyComputer
public class ToString implements IPropertyComputer<String, MRule7Atom>{

	@Override
	public String compute(MRule7Atom arg0) {
		return arg0.getUnderlyingObject().toString();
	}

}
