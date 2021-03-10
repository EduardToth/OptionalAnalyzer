package rule19Antipattern.actions;

import optionalanalizer.metamodel.entity.MRule19Atom;
import ro.lrg.xcore.metametamodel.ActionPerformer;
import ro.lrg.xcore.metametamodel.HListEmpty;
import ro.lrg.xcore.metametamodel.IActionPerformer;
import rule19Antipattern.Rule19Antipattern;

@ActionPerformer
public class ShowInEditor implements IActionPerformer<Void, MRule19Atom, HListEmpty>{

	@Override
	public Void performAction(MRule19Atom arg0, HListEmpty arg1) {
		Rule19Antipattern instance =  (Rule19Antipattern) arg0.getUnderlyingObject();
		instance.showInEditor();
		return null;
	}

}