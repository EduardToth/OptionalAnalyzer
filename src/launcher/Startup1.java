package launcher;

import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.dom.Assignment;
import org.eclipse.jdt.core.dom.IfStatement;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.MethodInvocation;
import org.eclipse.jdt.core.dom.SingleVariableDeclaration;
import org.eclipse.jdt.core.dom.VariableDeclarationFragment;
import org.eclipse.ui.IStartup;

import optionalanalizer.metamodel.entity.MDeclaration;
import optionalanalizer.metamodel.entity.MVariableDeclaration;
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
							return Factory.getInstance().createMInvocation((MethodInvocation)element);
						} else if(element instanceof IJavaProject) {
							return Factory.getInstance().createMProject((IJavaProject)element);
						} else if(element instanceof Assignment) {
							return Factory.getInstance().createMAssignment((Assignment)element);
						} else if(element instanceof VariableDeclarationFragment) {
							return Factory.getInstance().createMAssignment((VariableDeclarationFragment)element);
						} else if(element instanceof IfStatement) {
							return Factory.getInstance().createMIfStatement((IfStatement)element);
						}  else if(element instanceof SingleVariableDeclaration) {
							return Factory.getInstance().createMVariableDeclaration((SingleVariableDeclaration)element);
						} else if(element instanceof MethodDeclaration) {
							return Factory.getInstance().createMDeclaration((MethodDeclaration)element);
						}
						return null;
					}
				}
				);
	}
}