package returnStatements.actions;

import org.eclipse.jdt.core.dom.ReturnStatement;
import org.eclipse.ui.texteditor.ITextEditor;
import optionalanalizer.metamodel.entity.MReturnStatement;
import ro.lrg.xcore.metametamodel.ActionPerformer;
import ro.lrg.xcore.metametamodel.HListEmpty;
import ro.lrg.xcore.metametamodel.IActionPerformer;
import utilities.UtilityClass;

@ActionPerformer
public class ShowInEditor implements IActionPerformer<Void, MReturnStatement, HListEmpty>{

	@Override
	public Void performAction(MReturnStatement arg0, HListEmpty arg1) {
		ReturnStatement returnStatement = (ReturnStatement) arg0.getUnderlyingObject();
		ITextEditor ite = UtilityClass.getITextEditor(returnStatement);
		ite.selectAndReveal(returnStatement.getStartPosition(), returnStatement.getLength());

		return null;
	}

}