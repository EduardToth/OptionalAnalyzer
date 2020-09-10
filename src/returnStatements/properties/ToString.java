package returnStatements.properties;

import org.eclipse.jdt.core.dom.InfixExpression;

import optionalanalizer.metamodel.entity.MInfixExpression;
import optionalanalizer.metamodel.entity.MReturnStatement;
import ro.lrg.xcore.metametamodel.IPropertyComputer;
import ro.lrg.xcore.metametamodel.PropertyComputer;

@PropertyComputer
public class ToString implements IPropertyComputer<String, MReturnStatement>{

	@Override
	public String compute(MReturnStatement arg0) {
		return arg0.getUnderlyingObject().toString();
	}

}
