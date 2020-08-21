package variableDeclarations.properties;

import org.eclipse.jdt.core.dom.SingleVariableDeclaration;

import optionalanalizer.metamodel.entity.MVariableDeclaration;
import ro.lrg.xcore.metametamodel.IPropertyComputer;
import ro.lrg.xcore.metametamodel.PropertyComputer;

@PropertyComputer
public class ToString implements IPropertyComputer<String, MVariableDeclaration>{

	@Override
	public String compute(MVariableDeclaration arg0) {
		SingleVariableDeclaration singleVariableDeclaration = (SingleVariableDeclaration) arg0.getUnderlyingObject();
		return singleVariableDeclaration.getType() + " " +  singleVariableDeclaration.getName();
		//return "illes";
	}

}