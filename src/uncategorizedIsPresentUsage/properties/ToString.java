package uncategorizedIsPresentUsage.properties;


import optionalanalizer.metamodel.entity.MUncategorizedIsPresentAtom;
import ro.lrg.xcore.metametamodel.IPropertyComputer;
import ro.lrg.xcore.metametamodel.PropertyComputer;

@PropertyComputer
public class ToString implements IPropertyComputer<String, MUncategorizedIsPresentAtom>{

	@Override
	public String compute(MUncategorizedIsPresentAtom arg0) {
		return arg0.getUnderlyingObject().toString();
	}

}