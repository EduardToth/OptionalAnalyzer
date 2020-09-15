package rule_6Antipattern.actions;

import optionalanalizer.metamodel.entity.MRule6Atom;
import ro.lrg.xcore.metametamodel.ActionPerformer;
import ro.lrg.xcore.metametamodel.HListEmpty;
import ro.lrg.xcore.metametamodel.IActionPerformer;
import rule_6Antipattern.Rule6Atom;


@ActionPerformer
public class ShowInEditor implements IActionPerformer<Void, MRule6Atom, HListEmpty>{

	@Override
	public Void performAction(MRule6Atom arg0, HListEmpty arg1) {
		Rule6Atom instance =  (Rule6Atom) arg0.getUnderlyingObject();
		instance.showInEditor();
		return null;
	}

}