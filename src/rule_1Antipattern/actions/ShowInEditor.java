package rule_1Antipattern.actions;

import optionalanalizer.metamodel.entity.MRule1Atom;
import ro.lrg.xcore.metametamodel.ActionPerformer;
import ro.lrg.xcore.metametamodel.HListEmpty;
import ro.lrg.xcore.metametamodel.IActionPerformer;
import rule_1Antipattern.Rule1Atom;


@ActionPerformer
public class ShowInEditor implements IActionPerformer<Void, MRule1Atom, HListEmpty>{

	@Override
	public Void performAction(MRule1Atom arg0, HListEmpty arg1) {
		Rule1Atom rule1Atom =  (Rule1Atom) arg0.getUnderlyingObject();
		rule1Atom.showInEditor();
		return null;
	}

}