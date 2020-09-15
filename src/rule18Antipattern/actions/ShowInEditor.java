package rule18Antipattern.actions;

import optionalanalizer.metamodel.entity.MRule18Atom;
import ro.lrg.xcore.metametamodel.ActionPerformer;
import ro.lrg.xcore.metametamodel.HListEmpty;
import ro.lrg.xcore.metametamodel.IActionPerformer;
import rule18Antipattern.Rule18Atom;

@ActionPerformer
public class ShowInEditor implements IActionPerformer<Void, MRule18Atom, HListEmpty>{

	@Override
	public Void performAction(MRule18Atom arg0, HListEmpty arg1) {
		Rule18Atom instance =  (Rule18Atom) arg0.getUnderlyingObject();
		instance.showInEditor();
		return null;
	}

}