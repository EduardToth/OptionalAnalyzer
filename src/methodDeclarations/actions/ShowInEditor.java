package methodDeclarations.actions;

import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.ui.texteditor.ITextEditor;

import optionalanalizer.metamodel.entity.MDeclaration;
import ro.lrg.xcore.metametamodel.ActionPerformer;
import ro.lrg.xcore.metametamodel.HListEmpty;
import ro.lrg.xcore.metametamodel.IActionPerformer;
import utilities.UtilityClass;

@ActionPerformer
public class ShowInEditor implements IActionPerformer<Void, MDeclaration, HListEmpty>{

	@Override
	public Void performAction(MDeclaration arg0, HListEmpty arg1) {
		MethodDeclaration methodDecl = (MethodDeclaration) arg0.getUnderlyingObject();
		ITextEditor ite = UtilityClass.getITextEditor(methodDecl);

		ite.selectAndReveal(methodDecl.getStartPosition(), methodDecl.getLength());

		return null;
	}

}