package rule_7Antipattern.actions;

import optionalanalizer.metamodel.entity.MRule6Atom;
import optionalanalizer.metamodel.entity.MRule7Atom;
import ro.lrg.xcore.metametamodel.ActionPerformer;
import ro.lrg.xcore.metametamodel.HListEmpty;
import ro.lrg.xcore.metametamodel.IActionPerformer;
import rule_6Antipattern.Rule6Atom;
import rule_7Antipattern.Rule7Atom;

@ActionPerformer
public class ShowInEditor implements IActionPerformer<Void, MRule7Atom, HListEmpty>{

	@Override
	public Void performAction(MRule7Atom arg0, HListEmpty arg1) {
		Rule7Atom instance =  (Rule7Atom) arg0.getUnderlyingObject();
		instance.showInEditor();
		return null;
	}

}