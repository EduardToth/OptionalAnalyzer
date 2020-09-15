package rule_5Antipattern.properties;

import optionalanalizer.metamodel.entity.MRule5Atom;
import ro.lrg.xcore.metametamodel.IPropertyComputer;
import ro.lrg.xcore.metametamodel.PropertyComputer;

@PropertyComputer
public class ToString implements IPropertyComputer<String, MRule5Atom>{

	@Override
	public String compute(MRule5Atom arg0) {
		return arg0.getUnderlyingObject().toString();
	}

}
