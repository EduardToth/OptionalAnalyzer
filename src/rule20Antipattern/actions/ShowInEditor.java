package rule20Antipattern.actions;

import optionalanalizer.metamodel.entity.MRule20Atom;
import ro.lrg.xcore.metametamodel.ActionPerformer;
import ro.lrg.xcore.metametamodel.HListEmpty;
import ro.lrg.xcore.metametamodel.IActionPerformer;
import rule20Antipattern.Rule20Antipattern;

@ActionPerformer
public class ShowInEditor implements IActionPerformer<Void, MRule20Atom, HListEmpty>{

	@Override
	public Void performAction(MRule20Atom arg0, HListEmpty arg1) {
		Rule20Antipattern instance =  (Rule20Antipattern) arg0.getUnderlyingObject();
		instance.showInEditor();
		return null;
	}

}