package rule20Antipattern.properties;

import optionalanalizer.metamodel.entity.MRule20Atom;
import ro.lrg.xcore.metametamodel.IPropertyComputer;
import ro.lrg.xcore.metametamodel.PropertyComputer;

@PropertyComputer
public class ToString implements IPropertyComputer<String, MRule20Atom>{

	@Override
	public String compute(MRule20Atom arg0) {
		return arg0.getUnderlyingObject().toString();
	}

}
