package rule14Antipattern.properties;

import optionalanalizer.metamodel.entity.MRule14Atom;
import ro.lrg.xcore.metametamodel.IPropertyComputer;
import ro.lrg.xcore.metametamodel.PropertyComputer;

@PropertyComputer
public class ToString implements IPropertyComputer<String, MRule14Atom>{

	@Override
	public String compute(MRule14Atom arg0) {
		return arg0.getUnderlyingObject().toString();
	}

}
