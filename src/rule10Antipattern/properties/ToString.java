package rule10Antipattern.properties;

import optionalanalizer.metamodel.entity.MRule10Atom;
import ro.lrg.xcore.metametamodel.IPropertyComputer;
import ro.lrg.xcore.metametamodel.PropertyComputer;

@PropertyComputer
public class ToString implements IPropertyComputer<String, MRule10Atom>{

	@Override
	public String compute(MRule10Atom arg0) {
		return arg0.getUnderlyingObject().toString();
	}

}
