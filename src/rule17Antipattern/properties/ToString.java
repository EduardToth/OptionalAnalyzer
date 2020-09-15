package rule17Antipattern.properties;

import optionalanalizer.metamodel.entity.MRule17Atom;
import ro.lrg.xcore.metametamodel.IPropertyComputer;
import ro.lrg.xcore.metametamodel.PropertyComputer;

@PropertyComputer
public class ToString implements IPropertyComputer<String, MRule17Atom>{

	@Override
	public String compute(MRule17Atom arg0) {
		return arg0.getUnderlyingObject().toString();
	}

}
