package rule15Antipattern.actions;

import optionalanalizer.metamodel.entity.MRule15Atom;
import ro.lrg.xcore.metametamodel.ActionPerformer;
import ro.lrg.xcore.metametamodel.HListEmpty;
import ro.lrg.xcore.metametamodel.IActionPerformer;
import rule15Antipattern.Rule15Antipattern;

@ActionPerformer
public class ShowInEditor implements IActionPerformer<Void, MRule15Atom, HListEmpty>{

	@Override
	public Void performAction(MRule15Atom arg0, HListEmpty arg1) {
		Rule15Antipattern instance =  (Rule15Antipattern) arg0.getUnderlyingObject();
		instance.showInEditor();
		return null;
	}

}