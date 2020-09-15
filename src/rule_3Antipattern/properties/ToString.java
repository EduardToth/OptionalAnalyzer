package rule_3Antipattern.properties;

import optionalanalizer.metamodel.entity.MRule3Atom;
import ro.lrg.xcore.metametamodel.IPropertyComputer;
import ro.lrg.xcore.metametamodel.PropertyComputer;

@PropertyComputer
public class ToString implements IPropertyComputer<String, MRule3Atom>{

	@Override
	public String compute(MRule3Atom arg0) {
		// TODO Auto-generated method stub
		return arg0.getUnderlyingObject().toString();
	}

	

}
