package initializations.properties;

import org.eclipse.jdt.core.dom.VariableDeclarationFragment;

import optionalanalizer.metamodel.entity.MVariableDeclarationFragment;
import ro.lrg.xcore.metametamodel.IPropertyComputer;
import ro.lrg.xcore.metametamodel.PropertyComputer;

@PropertyComputer
public class ToString implements IPropertyComputer<String, MVariableDeclarationFragment>{

	@Override
	public String compute(MVariableDeclarationFragment arg0) {
		VariableDeclarationFragment fragment = (VariableDeclarationFragment)arg0.getUnderlyingObject();
		String typeName = fragment.resolveBinding().getType().getName();

		return typeName + " " + fragment;
	}

}
