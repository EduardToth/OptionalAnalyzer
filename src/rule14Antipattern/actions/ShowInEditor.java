package rule14Antipattern.actions;

import optionalanalizer.metamodel.entity.MRule14Atom;
import ro.lrg.xcore.metametamodel.ActionPerformer;
import ro.lrg.xcore.metametamodel.HListEmpty;
import ro.lrg.xcore.metametamodel.IActionPerformer;
import rule14Antipattern.Rule14Atom;

@ActionPerformer
public class ShowInEditor implements IActionPerformer<Void, MRule14Atom, HListEmpty>{

	@Override
	public Void performAction(MRule14Atom arg0, HListEmpty arg1) {
		Rule14Atom instance =  (Rule14Atom) arg0.getUnderlyingObject();
		instance.showInEditor();
		return null;
	}

}