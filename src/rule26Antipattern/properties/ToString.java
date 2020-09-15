package rule26Antipattern.properties;

import optionalanalizer.metamodel.entity.MRule26Atom;
import ro.lrg.xcore.metametamodel.IPropertyComputer;
import ro.lrg.xcore.metametamodel.PropertyComputer;

@PropertyComputer
public class ToString implements IPropertyComputer<String, MRule26Atom>{

	@Override
	public String compute(MRule26Atom arg0) {
		return arg0.getUnderlyingObject().toString();
	}

}
