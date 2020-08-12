package launcher;

import org.eclipse.core.resources.IProject;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IMethod;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.dom.Assignment;
import org.eclipse.jdt.core.dom.IfStatement;
import org.eclipse.jdt.core.dom.MethodInvocation;
import org.eclipse.jdt.core.dom.VariableDeclarationFragment;
import org.eclipse.ui.IStartup;

import optionalanalizer.metamodel.factory.Factory;
import ro.lrg.insider.view.ToolRegistration;
import ro.lrg.insider.view.ToolRegistration.XEntityConverter;
import ro.lrg.xcore.metametamodel.XEntity;

public class Startup1 implements IStartup{

	@Override
	public void earlyStartup() {
		ToolRegistration.getInstance().registerXEntityConverter(

				new XEntityConverter() {
					@Override
					public XEntity convert(Object element) {

						if(element instanceof ICompilationUnit) {
							return Factory.getInstance().createMCompilationUnit((ICompilationUnit)element);
						} else if(element instanceof MethodInvocation) {
							MethodInvocation m = null;


							return Factory.getInstance().createMInvocation((MethodInvocation)element);
						} else if(element instanceof IJavaProject) {
							return Factory.getInstance().createMProject((IJavaProject)element);
						} else if(element instanceof Assignment) {
							return Factory.getInstance().createMAssignment((Assignment)element);
						}  else if(element instanceof VariableDeclarationFragment) {
							return Factory.getInstance().createMVariableDeclarationFragment((VariableDeclarationFragment)element);
						}  else if(element instanceof IfStatement) {
							return Factory.getInstance().createMIfStatement((IfStatement)element);
						}
						return null;
					}
				}
				);
	}
}