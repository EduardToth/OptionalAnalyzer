package launcher;

import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.dom.Assignment;
import org.eclipse.jdt.core.dom.IfStatement;
import org.eclipse.jdt.core.dom.InfixExpression;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.MethodInvocation;
import org.eclipse.jdt.core.dom.PrefixExpression;
import org.eclipse.jdt.core.dom.ReturnStatement;
import org.eclipse.jdt.core.dom.SimpleName;
import org.eclipse.jdt.core.dom.SingleVariableDeclaration;
import org.eclipse.jdt.core.dom.VariableDeclarationExpression;
import org.eclipse.jdt.core.dom.VariableDeclarationFragment;
import org.eclipse.ui.IStartup;

import optionalanalizer.metamodel.entity.MDeclaration;
import optionalanalizer.metamodel.entity.MInfixExpression;
import optionalanalizer.metamodel.entity.MVariableDeclaration;
import optionalanalizer.metamodel.factory.Factory;
import ro.lrg.insider.view.ToolRegistration;
import ro.lrg.insider.view.ToolRegistration.XEntityConverter;
import ro.lrg.xcore.metametamodel.XEntity;

/*
 * N-am facut inca la:
 * 10
 * 11 -> inca nu stiu
 * 12
 * 22
 * 23
 * 24
 */
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
							try {
								return Factory.getInstance().createMAssignment((VariableDeclarationFragment)element);
							} catch(ClassCastException exp) {

							}
							return Factory.getInstance().createMVariableDeclaration((VariableDeclarationFragment)element);
						} else if(element instanceof IfStatement) {
							return Factory.getInstance().createMIfStatement((IfStatement)element);
						}  else if(element instanceof SingleVariableDeclaration) {
							return Factory.getInstance().createMVariableDeclaration((SingleVariableDeclaration)element);
						} else if(element instanceof MethodDeclaration) {
							try {
								return Factory.getInstance().createMDeclaration((MethodDeclaration)element);
							}catch (ClassCastException e) {
								// TODO: handle exception
							}
							return Factory.getInstance().createMMethod((MethodDeclaration)element);
						} else if(element instanceof InfixExpression) {
							return Factory.getInstance().createMInfixExpression((InfixExpression)element);
						} else if(element instanceof ReturnStatement) {
							return Factory.getInstance().createMReturnStatement((ReturnStatement)element);
						} else if(element instanceof SimpleName) {
							return Factory.getInstance().createMVariableDeclaration((SimpleName)element);
						} else if(element instanceof PrefixExpression) {
							return Factory.getInstance().createMPrefixExpression((PrefixExpression)element);
						}
						return null;
					}
				}
				);
	}
}