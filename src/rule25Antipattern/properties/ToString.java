package rule25Antipattern.properties;

import optionalanalizer.metamodel.entity.MRule25Atom;
import ro.lrg.xcore.metametamodel.IPropertyComputer;
import ro.lrg.xcore.metametamodel.PropertyComputer;

@PropertyComputer
public class ToString implements IPropertyComputer<String, MRule25Atom>{

	@Override
	public String compute(MRule25Atom arg0) {
		return arg0.getUnderlyingObject().toString();
	}

}
