package FullAnalysis.properties;

import optionalanalyzer.metamodel.entity.MAnalysis;
import ro.lrg.xcore.metametamodel.IPropertyComputer;
import ro.lrg.xcore.metametamodel.PropertyComputer;

@PropertyComputer
public class ToString implements IPropertyComputer<String, MAnalysis>{

	@Override
	public String compute(MAnalysis arg0) {
		return arg0.getUnderlyingObject()
				.toString();
	}

}
