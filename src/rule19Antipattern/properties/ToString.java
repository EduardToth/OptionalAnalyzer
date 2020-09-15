package rule19Antipattern.properties;

import optionalanalizer.metamodel.entity.MRule19Atom;
import ro.lrg.xcore.metametamodel.IPropertyComputer;
import ro.lrg.xcore.metametamodel.PropertyComputer;

@PropertyComputer
public class ToString implements IPropertyComputer<String, MRule19Atom>{

	@Override
	public String compute(MRule19Atom arg0) {
		return arg0.getUnderlyingObject().toString();
	}

}
