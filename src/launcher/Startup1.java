package launcher;

import org.eclipse.core.resources.IProject;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.IMethod;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.dom.MethodInvocation;
import org.eclipse.ui.IStartup;

import optionalanalizer.metamodel.factory.Factory;
import ro.lrg.insider.view.ToolRegistration;
import ro.lrg.insider.view.ToolRegistration.XEntityConverter;
import ro.lrg.xcore.metametamodel.XEntity;

public class Startup1 implements IStartup{

	@Override
	public void earlyStartup() {
		ToolRegistration.getInstance().registerXEntityConverter(

				(XEntityConverter) new XEntityConverter() {
					@Override
					public XEntity convert(Object element) {
						if(element instanceof ICompilationUnit) {
							return Factory.getInstance().createMCompilationUnit((ICompilationUnit)element);
						} else if(element instanceof MethodInvocation) {
							return Factory.getInstance().createMInvocation((MethodInvocation)element);
						} else if(element instanceof IProject) {
							return Factory.getInstance().createMProject((IProject)element);
						} 
						return null;
					}
				}
				);
	}
}