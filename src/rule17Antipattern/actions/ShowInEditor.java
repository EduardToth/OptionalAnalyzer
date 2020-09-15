package rule17Antipattern.actions;

import optionalanalizer.metamodel.entity.MRule17Atom;
import ro.lrg.xcore.metametamodel.ActionPerformer;
import ro.lrg.xcore.metametamodel.HListEmpty;
import ro.lrg.xcore.metametamodel.IActionPerformer;
import rule17Antipattern.Rule17Atom;

@ActionPerformer
public class ShowInEditor implements IActionPerformer<Void, MRule17Atom, HListEmpty>{

	@Override
	public Void performAction(MRule17Atom arg0, HListEmpty arg1) {
		Rule17Atom instance =  (Rule17Atom) arg0.getUnderlyingObject();
		instance.showInEditor();
		return null;
	}
}