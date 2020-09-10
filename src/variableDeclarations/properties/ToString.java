package variableDeclarations.properties;

import org.eclipse.jdt.core.dom.SimpleName;
import org.eclipse.jdt.core.dom.SingleVariableDeclaration;
import org.eclipse.jdt.core.dom.VariableDeclarationFragment;

import optionalanalizer.metamodel.entity.MVariableDeclaration;
import ro.lrg.xcore.metametamodel.IPropertyComputer;
import ro.lrg.xcore.metametamodel.PropertyComputer;

@PropertyComputer
public class ToString implements IPropertyComputer<String, MVariableDeclaration>{

	@Override
	public String compute(MVariableDeclaration arg0) {
		if(arg0.getUnderlyingObject() instanceof SingleVariableDeclaration) {
			SingleVariableDeclaration singleVariableDeclaration = (SingleVariableDeclaration) arg0.getUnderlyingObject();
			return singleVariableDeclaration.getType() + " " +  singleVariableDeclaration.getName();
		} else if(arg0.getUnderlyingObject() instanceof SimpleName) {
			SimpleName simpleName = (SimpleName) arg0.getUnderlyingObject();
			return simpleName.toString();
		}
		else {
			return ((VariableDeclarationFragment)arg0.getUnderlyingObject()).getParent().toString();
		}
	}
}