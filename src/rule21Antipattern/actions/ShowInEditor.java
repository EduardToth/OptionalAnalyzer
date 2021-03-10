package rule21Antipattern.actions;

import optionalanalizer.metamodel.entity.MRule21Atom;
import ro.lrg.xcore.metametamodel.ActionPerformer;
import ro.lrg.xcore.metametamodel.HListEmpty;
import ro.lrg.xcore.metametamodel.IActionPerformer;
import rule21Antipattern.Rule21Antipattern;

@ActionPerformer
public class ShowInEditor implements IActionPerformer<Void, MRule21Atom, HListEmpty>{

	@Override
	public Void performAction(MRule21Atom arg0, HListEmpty arg1) {
		Rule21Antipattern instance =  (Rule21Antipattern) arg0.getUnderlyingObject();
		instance.showInEditor();
		return null;
	}

}