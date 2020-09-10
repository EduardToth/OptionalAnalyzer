package prefixExpressions.properties;

import org.eclipse.jdt.core.dom.PrefixExpression;
import optionalanalizer.metamodel.entity.MPrefixExpression;
import ro.lrg.xcore.metametamodel.IPropertyComputer;
import ro.lrg.xcore.metametamodel.PropertyComputer;

@PropertyComputer
public class ToString implements IPropertyComputer<String, MPrefixExpression>{

	@Override
	public String compute(MPrefixExpression arg0) {
		PrefixExpression prefixExpression = (PrefixExpression) arg0.getUnderlyingObject();

		return prefixExpression.toString();
	}

}