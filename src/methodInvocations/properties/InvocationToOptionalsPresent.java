package methodInvocations.properties;

import org.eclipse.jdt.core.dom.MethodInvocation;

import optionalanalizer.metamodel.entity.MInvocation;
import ro.lrg.xcore.metametamodel.IPropertyComputer;
import ro.lrg.xcore.metametamodel.PropertyComputer;


@PropertyComputer
public class InvocationToOptionalsPresent  implements IPropertyComputer<String, MInvocation>{

	@Override
	public String compute(MInvocation arg0) {
		MethodInvocation invocation = (MethodInvocation) arg0.getUnderlyingObject();

		String invokedMethodName = invocation.getName().toString();
		String invocatorName = invocation.getExpression().toString();

		return invocatorName + "." + invokedMethodName + "()";
	}
}
