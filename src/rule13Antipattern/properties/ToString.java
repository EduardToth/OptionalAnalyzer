package rule13Antipattern.properties;

import optionalanalizer.metamodel.entity.MRule13Atom;
import ro.lrg.xcore.metametamodel.IPropertyComputer;
import ro.lrg.xcore.metametamodel.PropertyComputer;

@PropertyComputer
public class ToString implements IPropertyComputer<String, MRule13Atom>{

	@Override
	public String compute(MRule13Atom arg0) {
		return arg0.getUnderlyingObject().toString();
	}

}
