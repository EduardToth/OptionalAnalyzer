package variableDeclarations.actions;

import org.eclipse.jdt.core.dom.SingleVariableDeclaration;
import org.eclipse.ui.texteditor.ITextEditor;

import optionalanalizer.metamodel.entity.MVariableDeclaration;
import ro.lrg.xcore.metametamodel.ActionPerformer;
import ro.lrg.xcore.metametamodel.HListEmpty;
import ro.lrg.xcore.metametamodel.IActionPerformer;
import utilities.UtilityClass;

@ActionPerformer
public class ShowInEditor implements IActionPerformer<Void, MVariableDeclaration, HListEmpty>{

	@Override
	public Void performAction(MVariableDeclaration arg0, HListEmpty arg1) {
		SingleVariableDeclaration singleVariableDeclaration = (SingleVariableDeclaration) arg0.getUnderlyingObject();
		ITextEditor ite = UtilityClass.getITextEditor(singleVariableDeclaration);

		ite.selectAndReveal(singleVariableDeclaration.getStartPosition(), singleVariableDeclaration.getLength());

		return null;
	}

}