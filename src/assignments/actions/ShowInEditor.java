package assignments.actions;


import java.util.Optional;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.Assignment;
import org.eclipse.ui.texteditor.ITextEditor;

import compilationUnits.groups.OptionalInvocationsBuilder;
import optionalanalizer.metamodel.entity.MAssignment;
import ro.lrg.xcore.metametamodel.ActionPerformer;
import ro.lrg.xcore.metametamodel.HListEmpty;
import ro.lrg.xcore.metametamodel.IActionPerformer;
import utilities.UtilityClass;

@ActionPerformer
public class ShowInEditor implements IActionPerformer<Void, MAssignment, HListEmpty>{

	@Override
	public Void performAction(MAssignment arg0, HListEmpty arg1) {
		Object o = arg0.getUnderlyingObject();
		Optional<ASTNode> node = Optional.empty();;
		if(o instanceof ASTNode) {
			node = Optional.of( (ASTNode)o );
		}
		node.ifPresent(el -> show(el));

		return null;
	}

	private void show(ASTNode node) {
		ITextEditor ite = UtilityClass.getITextEditor(node);

		ite.selectAndReveal(node.getStartPosition(), node.getLength());
	}

}
