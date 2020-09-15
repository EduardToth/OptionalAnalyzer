package rule18Antipattern.properties;

import optionalanalizer.metamodel.entity.MRule18Atom;
import ro.lrg.xcore.metametamodel.IPropertyComputer;
import ro.lrg.xcore.metametamodel.PropertyComputer;

@PropertyComputer
public class ToString implements IPropertyComputer<String, MRule18Atom>{

	@Override
	public String compute(MRule18Atom arg0) {
		return arg0.getUnderlyingObject().toString();
	}

}
