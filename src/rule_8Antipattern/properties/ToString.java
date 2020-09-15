package rule_8Antipattern.properties;

import optionalanalizer.metamodel.entity.MRule8Atom;
import ro.lrg.xcore.metametamodel.IPropertyComputer;
import ro.lrg.xcore.metametamodel.PropertyComputer;

@PropertyComputer
public class ToString implements IPropertyComputer<String, MRule8Atom>{

	@Override
	public String compute(MRule8Atom arg0) {
		return arg0.getUnderlyingObject().toString();
	}

}
