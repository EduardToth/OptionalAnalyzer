package rule12Antipattern.properties;

import optionalanalizer.metamodel.entity.MRule12Atom;
import ro.lrg.xcore.metametamodel.IPropertyComputer;
import ro.lrg.xcore.metametamodel.PropertyComputer;

@PropertyComputer
public class ToString implements IPropertyComputer<String, MRule12Atom>{

	@Override
	public String compute(MRule12Atom arg0) {
		return arg0.getUnderlyingObject().toString();
	}

}
