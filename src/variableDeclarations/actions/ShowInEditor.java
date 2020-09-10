package variableDeclarations.actions;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.SimpleName;
import org.eclipse.jdt.core.dom.SingleVariableDeclaration;
import org.eclipse.jdt.core.dom.VariableDeclarationFragment;
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

		if(arg0.getUnderlyingObject() instanceof SingleVariableDeclaration) {
			handleSituationForSingleVariableDeclaration(arg0);
		} else if(arg0.getUnderlyingObject() instanceof VariableDeclarationFragment ){
			handleSituationForVariableDeclarationFragment(arg0);
		} else if(arg0.getUnderlyingObject() instanceof SimpleName) {
			handleSituationForSimpleName(arg0);
		}
		return null;
	}

	private void handleSituationForSimpleName(MVariableDeclaration arg0) {
		SimpleName simpleName = (SimpleName) arg0.getUnderlyingObject();
		ASTNode astNode = null;
		if(simpleName.getParent() instanceof VariableDeclarationFragment) {
			astNode = simpleName.getParent().getParent();
		} else {
			astNode = simpleName.getParent();
		}
		ITextEditor ite = UtilityClass.getITextEditor(astNode);
		ite.selectAndReveal(astNode.getStartPosition(), astNode.getLength());
	}

	private void handleSituationForVariableDeclarationFragment(MVariableDeclaration arg0) {
		VariableDeclarationFragment var = (VariableDeclarationFragment) arg0.getUnderlyingObject();
		ITextEditor ite = UtilityClass.getITextEditor(var);
		ite.selectAndReveal(var.getParent().getStartPosition(), var.getParent().getLength());
	}

	private void handleSituationForSingleVariableDeclaration(MVariableDeclaration arg0) {
		SingleVariableDeclaration singleVariableDeclaration = (SingleVariableDeclaration) arg0.getUnderlyingObject();
		ITextEditor ite = UtilityClass.getITextEditor(singleVariableDeclaration);

		ite.selectAndReveal(singleVariableDeclaration.getStartPosition(), singleVariableDeclaration.getLength());
	}

}