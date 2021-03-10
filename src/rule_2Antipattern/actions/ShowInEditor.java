package rule_2Antipattern.actions;

import optionalanalizer.metamodel.entity.MRule2Atom;
import ro.lrg.xcore.metametamodel.ActionPerformer;
import ro.lrg.xcore.metametamodel.HListEmpty;
import ro.lrg.xcore.metametamodel.IActionPerformer;
import rule_2Antipattern.Rule2Antipattern;

@ActionPerformer
public class ShowInEditor implements IActionPerformer<Void, MRule2Atom, HListEmpty>{

	@Override
	public Void performAction(MRule2Atom arg0, HListEmpty arg1) {
		Rule2Antipattern rule2Atom =  (Rule2Antipattern) arg0.getUnderlyingObject();
		rule2Atom.showInEditor();
		return null;
	}

}