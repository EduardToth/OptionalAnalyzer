package rule_8Antipattern.actions;


import optionalanalizer.metamodel.entity.MRule8Atom;
import ro.lrg.xcore.metametamodel.ActionPerformer;
import ro.lrg.xcore.metametamodel.HListEmpty;
import ro.lrg.xcore.metametamodel.IActionPerformer;
import rule_8Antipattern.Rule8Atom;

@ActionPerformer
public class ShowInEditor implements IActionPerformer<Void, MRule8Atom, HListEmpty>{

	@Override
	public Void performAction(MRule8Atom arg0, HListEmpty arg1) {
		Rule8Atom instance =  (Rule8Atom) arg0.getUnderlyingObject();
		instance.showInEditor();
		return null;
	}

}