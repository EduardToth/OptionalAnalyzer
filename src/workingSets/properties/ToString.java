package workingSets.properties;

import org.eclipse.ui.internal.WorkingSet;

import optionalanalyzer.metamodel.entity.MWorkingSet;
import ro.lrg.xcore.metametamodel.IPropertyComputer;
import ro.lrg.xcore.metametamodel.PropertyComputer;

@PropertyComputer
public class ToString implements IPropertyComputer<String, MWorkingSet>{
	
	@Override
	public String compute(MWorkingSet arg0) {
		return ((WorkingSet)arg0.getUnderlyingObject()).getName();
	}
	
}

