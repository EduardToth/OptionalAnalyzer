package infixExpressions.actions;

import org.eclipse.jdt.core.dom.InfixExpression;
import org.eclipse.ui.texteditor.ITextEditor;

import optionalanalizer.metamodel.entity.MInfixExpression;
import ro.lrg.xcore.metametamodel.ActionPerformer;
import ro.lrg.xcore.metametamodel.HListEmpty;
import ro.lrg.xcore.metametamodel.IActionPerformer;
import utilities.UtilityClass;

@ActionPerformer
public class ShowInEditor implements IActionPerformer<Void, MInfixExpression, HListEmpty>{

	@Override
	public Void performAction(MInfixExpression arg0, HListEmpty arg1) {
		InfixExpression infixExpression = (InfixExpression) arg0.getUnderlyingObject();
		ITextEditor ite = UtilityClass.getITextEditor(infixExpression);

		ite.selectAndReveal(infixExpression.getStartPosition(), infixExpression.getLength());

		return null;
	}

}