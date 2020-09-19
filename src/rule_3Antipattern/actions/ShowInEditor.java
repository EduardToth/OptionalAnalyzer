package rule_3Antipattern.actions;

import optionalanalizer.metamodel.entity.MRule3Atom;
import ro.lrg.xcore.metametamodel.ActionPerformer;
import ro.lrg.xcore.metametamodel.HListEmpty;
import ro.lrg.xcore.metametamodel.IActionPerformer;
import rule_3Antipattern.Rule3Atom;

@ActionPerformer
public class ShowInEditor implements IActionPerformer<Void, MRule3Atom, HListEmpty>{

	@Override
	public Void performAction(MRule3Atom arg0, HListEmpty arg1) {
		Rule3Atom instance =  (Rule3Atom) arg0.getUnderlyingObject();
		instance.showInEditor();
		return null;
	}

}