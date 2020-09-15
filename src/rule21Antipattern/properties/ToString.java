package rule21Antipattern.properties;

import optionalanalizer.metamodel.entity.MRule21Atom;
import ro.lrg.xcore.metametamodel.IPropertyComputer;
import ro.lrg.xcore.metametamodel.PropertyComputer;

@PropertyComputer
public class ToString implements IPropertyComputer<String, MRule21Atom>{

	@Override
	public String compute(MRule21Atom arg0) {
		return arg0.getUnderlyingObject().toString();
	}

}
