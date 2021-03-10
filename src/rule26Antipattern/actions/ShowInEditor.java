package rule26Antipattern.actions;

import optionalanalizer.metamodel.entity.MRule26Atom;
import ro.lrg.xcore.metametamodel.ActionPerformer;
import ro.lrg.xcore.metametamodel.HListEmpty;
import ro.lrg.xcore.metametamodel.IActionPerformer;
import utilities.Antipattern;

@ActionPerformer
public class ShowInEditor implements IActionPerformer<Void, MRule26Atom, HListEmpty>{

	@Override
	public Void performAction(MRule26Atom arg0, HListEmpty arg1) {
		Antipattern instance =  (Antipattern) arg0.getUnderlyingObject();
		instance.showInEditor();
		return null;
	}

}