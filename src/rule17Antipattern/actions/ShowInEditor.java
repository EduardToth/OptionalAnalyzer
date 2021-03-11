package rule17Antipattern.actions;

import optionalanalizer.metamodel.entity.MRule17sAntipattern;
import ro.lrg.xcore.metametamodel.ActionPerformer;
import ro.lrg.xcore.metametamodel.HListEmpty;
import ro.lrg.xcore.metametamodel.IActionPerformer;
import rule17Antipattern.Rule17Antipattern;

@ActionPerformer
public class ShowInEditor implements IActionPerformer<Void, MRule17sAntipattern, HListEmpty>{

	@Override
	public Void performAction(MRule17sAntipattern arg0, HListEmpty arg1) {
		Rule17Antipattern instance =  (Rule17Antipattern) arg0.getUnderlyingObject();
		instance.showInEditor();
		return null;
	}
}