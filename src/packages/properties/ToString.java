package packages.properties;


import org.eclipse.jdt.core.IPackageFragment;

import optionalanalyzer.metamodel.entity.MPackage;
import ro.lrg.xcore.metametamodel.IPropertyComputer;
import ro.lrg.xcore.metametamodel.PropertyComputer;

@PropertyComputer
public class ToString implements IPropertyComputer<String, MPackage>{

	
	@Override
	public String compute(MPackage arg0) {
		return ((IPackageFragment)arg0.getUnderlyingObject()).getElementName();
	}
	
	
}