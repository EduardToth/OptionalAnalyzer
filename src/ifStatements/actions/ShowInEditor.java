package ifStatements.actions;


import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.core.dom.IfStatement;
import org.eclipse.jdt.core.dom.VariableDeclarationExpression;
import org.eclipse.jdt.ui.JavaUI;
import org.eclipse.ui.PartInitException;

import optionalanalizer.metamodel.entity.MIfStatement;
import ro.lrg.xcore.metametamodel.ActionPerformer;
import ro.lrg.xcore.metametamodel.HListEmpty;
import ro.lrg.xcore.metametamodel.IActionPerformer;

@ActionPerformer
public class ShowInEditor implements IActionPerformer<Void, MIfStatement, HListEmpty>{

	@Override
	public Void performAction(MIfStatement arg0, HListEmpty arg1) {
		IfStatement ifStatement = (IfStatement) arg0.getUnderlyingObject();
		VariableDeclarationExpression variableDeclarationExpressiopon = 
				(VariableDeclarationExpression) ifStatement.getExpression();
		try {
			JavaUI.openInEditor((IJavaElement) variableDeclarationExpressiopon, true, true);
		} catch (PartInitException e) {
			e.printStackTrace();
		} catch( JavaModelException e ) {
			e.printStackTrace();
		}
		return null;
	}

}