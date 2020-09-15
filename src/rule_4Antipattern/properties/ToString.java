package rule_4Antipattern.properties;

import optionalanalizer.metamodel.entity.MRule4Atom;
import ro.lrg.xcore.metametamodel.IPropertyComputer;
import ro.lrg.xcore.metametamodel.PropertyComputer;

@PropertyComputer
public class ToString implements IPropertyComputer<String, MRule4Atom>{

	@Override
	public String compute(MRule4Atom arg0) {
		return arg0.getUnderlyingObject().toString();
	}

}
