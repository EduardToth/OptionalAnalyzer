package ifStatements.actions;


import org.eclipse.jdt.core.dom.IfStatement;
import org.eclipse.ui.texteditor.ITextEditor;

import optionalanalizer.metamodel.entity.MIfStatement;
import ro.lrg.xcore.metametamodel.ActionPerformer;
import ro.lrg.xcore.metametamodel.HListEmpty;
import ro.lrg.xcore.metametamodel.IActionPerformer;
import utilities.UtilityClass;


@ActionPerformer
public class ShowInEditor implements IActionPerformer<Void, MIfStatement, HListEmpty>{

	@Override
	public Void performAction(MIfStatement arg0, HListEmpty arg1) {
		IfStatement ifStatement = (IfStatement) arg0.getUnderlyingObject();
		ITextEditor ite = UtilityClass.getITextEditor(ifStatement);
		
		ite.selectAndReveal(ifStatement.getStartPosition(), ifStatement.getLength());

		return null;
	}
}