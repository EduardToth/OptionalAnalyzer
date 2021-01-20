package uncategorizedIsPresentUsage.actions;


import optionalanalizer.metamodel.entity.MUncategorizedIsPresentAtom;
import ro.lrg.xcore.metametamodel.ActionPerformer;
import ro.lrg.xcore.metametamodel.HListEmpty;
import ro.lrg.xcore.metametamodel.IActionPerformer;
import utilities.Atom;

@ActionPerformer
public class ShowInEditor implements IActionPerformer<Void, MUncategorizedIsPresentAtom, HListEmpty>{

	@Override
	public Void performAction(MUncategorizedIsPresentAtom arg0, HListEmpty arg1) {
		Atom instance =  (Atom) arg0.getUnderlyingObject();
		instance.showInEditor();
		return null;
	}

}