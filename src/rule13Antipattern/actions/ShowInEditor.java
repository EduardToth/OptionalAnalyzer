package rule13Antipattern.actions;

import optionalanalizer.metamodel.entity.MRule13Atom;
import ro.lrg.xcore.metametamodel.ActionPerformer;
import ro.lrg.xcore.metametamodel.HListEmpty;
import ro.lrg.xcore.metametamodel.IActionPerformer;
import rule13Antipattern.Rule13Antipattern;

@ActionPerformer
public class ShowInEditor implements IActionPerformer<Void, MRule13Atom, HListEmpty>{

	@Override
	public Void performAction(MRule13Atom arg0, HListEmpty arg1) {
		Rule13Antipattern instance =  (Rule13Antipattern) arg0.getUnderlyingObject();
		instance.showInEditor();
		return null;
	}

}