package uncategorizedIsPresentUsage.properties;


import optionalanalyzer.metamodel.entity.MUncategorizedIsPresentPossibleAntipattern;
import ro.lrg.xcore.metametamodel.IPropertyComputer;
import ro.lrg.xcore.metametamodel.PropertyComputer;

@PropertyComputer
public class ToString implements IPropertyComputer<String, MUncategorizedIsPresentPossibleAntipattern>{

	@Override
	public String compute(MUncategorizedIsPresentPossibleAntipattern arg0) {
		return arg0.getUnderlyingObject().toString();
	}

}