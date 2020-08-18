package assignments.actions;


import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.Assignment;
import org.eclipse.jdt.core.dom.VariableDeclarationFragment;
import org.eclipse.jdt.ui.JavaUI;
import org.eclipse.ui.PartInitException;

import optionalanalizer.metamodel.entity.MAssignment;
import ro.lrg.xcore.metametamodel.ActionPerformer;
import ro.lrg.xcore.metametamodel.HListEmpty;
import ro.lrg.xcore.metametamodel.IActionPerformer;

@ActionPerformer
public class ShowInEditor implements IActionPerformer<Void, MAssignment, HListEmpty>{

	@Override
	public Void performAction(MAssignment arg0, HListEmpty arg1) {
		//		VariableDeclarationFragment fragment = null;
		//		Object token = arg0.getUnderlyingObject();
		//		if(arg0 instanceof Assignment) {
		//			((Assignment)arg0).accept(new ASTVisitor() {
		//				
		//				@Override
		//				public boolean visit()
		//			});
		//		} else {
		//			fragment = (VariableDeclarationFragment)token;
		//		}
		//		try {
		//			JavaUI.openInEditor((IJavaElement) fragment, true, true);
		//		} catch (PartInitException e) {
		//			e.printStackTrace();
		//		} catch( JavaModelException e ) {
		//			e.printStackTrace();
		//		}
		return null;
	}

}
