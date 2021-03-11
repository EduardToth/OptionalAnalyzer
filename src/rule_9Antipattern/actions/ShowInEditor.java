package rule_9Antipattern.actions;

import optionalanalizer.metamodel.entity.MRule9sAntipattern;
import ro.lrg.xcore.metametamodel.ActionPerformer;
import ro.lrg.xcore.metametamodel.HListEmpty;
import ro.lrg.xcore.metametamodel.IActionPerformer;
import rule_9Antipattern.Rule9Antipattern;

@ActionPerformer
public class ShowInEditor implements IActionPerformer<Void, MRule9sAntipattern, HListEmpty>{

	@Override
	public Void performAction(MRule9sAntipattern arg0, HListEmpty arg1) {
		Rule9Antipattern instance =  (Rule9Antipattern) arg0.getUnderlyingObject();
		instance.showInEditor();
		return null;
	}

}