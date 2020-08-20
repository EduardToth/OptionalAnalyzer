package methodInvocations.actions;

import org.eclipse.jdt.core.dom.MethodInvocation;
import org.eclipse.ui.texteditor.ITextEditor;

import optionalanalizer.metamodel.entity.MInvocation;
import ro.lrg.xcore.metametamodel.ActionPerformer;
import ro.lrg.xcore.metametamodel.HListEmpty;
import ro.lrg.xcore.metametamodel.IActionPerformer;
import utilities.UtilityClass;

@ActionPerformer
public class OptionalIsPresentLocatorInEditor implements IActionPerformer<Void, MInvocation, HListEmpty>{

	@Override
	public Void performAction(MInvocation arg0, HListEmpty arg1) {
		MethodInvocation methodInvocation = (MethodInvocation) arg0.getUnderlyingObject();
		ITextEditor ite = UtilityClass.getITextEditor(methodInvocation);

		ite.selectAndReveal(methodInvocation.getStartPosition(), methodInvocation.getLength());

		return null;
	}

}
