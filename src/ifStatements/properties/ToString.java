package ifStatements.properties;

import org.eclipse.jdt.core.dom.IfStatement;

import optionalanalizer.metamodel.entity.MIfStatement;
import ro.lrg.xcore.metametamodel.IPropertyComputer;
import ro.lrg.xcore.metametamodel.PropertyComputer;

@PropertyComputer
public class ToString  implements IPropertyComputer<String, MIfStatement>{

	@Override
	public String compute(MIfStatement arg0) {
		return ((IfStatement)arg0.getUnderlyingObject()).getExpression().toString();
	}

}