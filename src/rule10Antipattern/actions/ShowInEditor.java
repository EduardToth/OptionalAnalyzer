package rule10Antipattern.actions;

import optionalanalizer.metamodel.entity.MRule10Atom;
import ro.lrg.xcore.metametamodel.ActionPerformer;
import ro.lrg.xcore.metametamodel.HListEmpty;
import ro.lrg.xcore.metametamodel.IActionPerformer;
import utilities.Antipattern;

@ActionPerformer
public class ShowInEditor implements IActionPerformer<Void, MRule10Atom, HListEmpty>{

	@Override
	public Void performAction(MRule10Atom arg0, HListEmpty arg1) {
		Antipattern instance =  (Antipattern) arg0.getUnderlyingObject();
		instance.showInEditor();
		return null;
	}

}