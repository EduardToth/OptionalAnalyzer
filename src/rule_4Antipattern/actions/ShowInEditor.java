package rule_4Antipattern.actions;

import optionalanalizer.metamodel.entity.MRule4Atom;
import ro.lrg.xcore.metametamodel.ActionPerformer;
import ro.lrg.xcore.metametamodel.HListEmpty;
import ro.lrg.xcore.metametamodel.IActionPerformer;
import rule_4Antipattern.Rule4Antipattern;

@ActionPerformer
public class ShowInEditor implements IActionPerformer<Void, MRule4Atom, HListEmpty>{

	@Override
	public Void performAction(MRule4Atom arg0, HListEmpty arg1) {
		Rule4Antipattern instance =  (Rule4Antipattern) arg0.getUnderlyingObject();
		instance.showInEditor();
		return null;
	}

}