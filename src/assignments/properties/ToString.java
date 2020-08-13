package assignments.properties;

import org.eclipse.jdt.core.dom.Assignment;
import org.eclipse.jdt.core.dom.VariableDeclarationFragment;

import optionalanalizer.metamodel.entity.MAssignment;
import ro.lrg.xcore.metametamodel.IPropertyComputer;
import ro.lrg.xcore.metametamodel.PropertyComputer;

@PropertyComputer
public class ToString implements IPropertyComputer<String, MAssignment>{

	@Override
	public String compute(MAssignment arg0) {
		Object token = arg0.getUnderlyingObject();
		if(token instanceof Assignment) {
			return token.toString(); 
		}
		/*
		I know that always the token reference refers to an object of type VariableDeclarationFragment
		That's why I do not verify this
		 */
		return getStringFormForVariableDeclaration(token);
	}

	private String getStringFormForVariableDeclaration(Object token) {
		VariableDeclarationFragment fragment = (VariableDeclarationFragment)token;
		String typeName = fragment.resolveBinding().getType().getName();

		return typeName + " " + fragment;
	}
}
