package methods.actions;

import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.ui.texteditor.ITextEditor;

import optionalanalizer.metamodel.entity.MMethod;
import ro.lrg.xcore.metametamodel.ActionPerformer;
import ro.lrg.xcore.metametamodel.HListEmpty;
import ro.lrg.xcore.metametamodel.IActionPerformer;
import utilities.UtilityClass;

@ActionPerformer
public class ShowInEditor implements IActionPerformer<Void, MMethod, HListEmpty>{

	@Override
	public Void performAction(MMethod arg0, HListEmpty arg1) {
		MethodDeclaration methodDeclaration = (MethodDeclaration) arg0.getUnderlyingObject();
		ITextEditor ite = UtilityClass.getITextEditor(methodDeclaration);
		ite.selectAndReveal(methodDeclaration.getStartPosition(), methodDeclaration.getLength());

		return null;
	}

}
