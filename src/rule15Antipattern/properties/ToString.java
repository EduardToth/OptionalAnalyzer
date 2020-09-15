package rule15Antipattern.properties;

import optionalanalizer.metamodel.entity.MRule15Atom;
import ro.lrg.xcore.metametamodel.IPropertyComputer;
import ro.lrg.xcore.metametamodel.PropertyComputer;

@PropertyComputer
public class ToString implements IPropertyComputer<String, MRule15Atom>{

	@Override
	public String compute(MRule15Atom arg0) {
		return arg0.getUnderlyingObject().toString();
	}

}
