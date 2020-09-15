package rule12Antipattern.actions;

import optionalanalizer.metamodel.entity.MRule12Atom;
import ro.lrg.xcore.metametamodel.ActionPerformer;
import ro.lrg.xcore.metametamodel.HListEmpty;
import ro.lrg.xcore.metametamodel.IActionPerformer;
import rule12Antipattern.Rule12Atom;

@ActionPerformer
public class ShowInEditor implements IActionPerformer<Void, MRule12Atom, HListEmpty>{

	@Override
	public Void performAction(MRule12Atom arg0, HListEmpty arg1) {
		Rule12Atom instance =  (Rule12Atom) arg0.getUnderlyingObject();
		instance.showInEditor();
		return null;
	}

}