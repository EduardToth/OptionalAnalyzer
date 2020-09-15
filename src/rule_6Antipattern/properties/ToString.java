package rule_6Antipattern.properties;

import optionalanalizer.metamodel.entity.MRule6Atom;
import ro.lrg.xcore.metametamodel.IPropertyComputer;
import ro.lrg.xcore.metametamodel.PropertyComputer;

@PropertyComputer
public class ToString implements IPropertyComputer<String, MRule6Atom>{

	@Override
	public String compute(MRule6Atom arg0) {
		return arg0.getUnderlyingObject().toString();
	}

}
