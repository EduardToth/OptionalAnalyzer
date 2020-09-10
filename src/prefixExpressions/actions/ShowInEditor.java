package prefixExpressions.actions;

import org.eclipse.jdt.core.dom.PrefixExpression;
import org.eclipse.ui.texteditor.ITextEditor;

import optionalanalizer.metamodel.entity.MPrefixExpression;
import ro.lrg.xcore.metametamodel.ActionPerformer;
import ro.lrg.xcore.metametamodel.HListEmpty;
import ro.lrg.xcore.metametamodel.IActionPerformer;
import utilities.UtilityClass;

@ActionPerformer
public class ShowInEditor implements IActionPerformer<Void, MPrefixExpression, HListEmpty>{

	@Override
	public Void performAction(MPrefixExpression arg0, HListEmpty arg1) {
		PrefixExpression prefixExpression = (PrefixExpression) arg0.getUnderlyingObject();
		ITextEditor ite = UtilityClass.getITextEditor(prefixExpression);
		ite.selectAndReveal(prefixExpression.getStartPosition(), prefixExpression.getLength());

		return null;
	}

}