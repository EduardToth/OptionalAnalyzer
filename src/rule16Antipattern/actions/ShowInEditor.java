package rule16Antipattern.actions;

import optionalanalizer.metamodel.entity.MRule16Atom;
import ro.lrg.xcore.metametamodel.ActionPerformer;
import ro.lrg.xcore.metametamodel.HListEmpty;
import ro.lrg.xcore.metametamodel.IActionPerformer;
import rule16Antipattern.Rule16Antipattern;

@ActionPerformer
public class ShowInEditor implements IActionPerformer<Void, MRule16Atom, HListEmpty>{

	@Override
	public Void performAction(MRule16Atom arg0, HListEmpty arg1) {
		Rule16Antipattern instance =  (Rule16Antipattern) arg0.getUnderlyingObject();
		instance.showInEditor();
		return null;
	}

}