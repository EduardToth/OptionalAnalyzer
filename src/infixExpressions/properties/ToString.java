package infixExpressions.properties;

import org.eclipse.jdt.core.dom.InfixExpression;

import optionalanalizer.metamodel.entity.MInfixExpression;
import ro.lrg.xcore.metametamodel.IPropertyComputer;
import ro.lrg.xcore.metametamodel.PropertyComputer;

@PropertyComputer
public class ToString implements IPropertyComputer<String, MInfixExpression>{

	@Override
	public String compute(MInfixExpression arg0) {
		InfixExpression infixExpression = (InfixExpression) arg0.getUnderlyingObject();
		return infixExpression.toString();
		
	}

}