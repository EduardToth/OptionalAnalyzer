package rule16Antipattern.properties;


import optionalanalizer.metamodel.entity.MRule16Atom;
import ro.lrg.xcore.metametamodel.IPropertyComputer;
import ro.lrg.xcore.metametamodel.PropertyComputer;

@PropertyComputer
public class ToString implements IPropertyComputer<String, MRule16Atom>{

	@Override
	public String compute(MRule16Atom arg0) {
		return arg0.getUnderlyingObject().toString();
	}

}
