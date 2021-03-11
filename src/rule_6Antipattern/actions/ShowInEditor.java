package rule_6Antipattern.actions;

import optionalanalizer.metamodel.entity.MRule6sAntipattern;
import ro.lrg.xcore.metametamodel.ActionPerformer;
import ro.lrg.xcore.metametamodel.HListEmpty;
import ro.lrg.xcore.metametamodel.IActionPerformer;
import rule_6Antipattern.Rule6Antipattern;


@ActionPerformer
public class ShowInEditor implements IActionPerformer<Void, MRule6sAntipattern, HListEmpty>{

	@Override
	public Void performAction(MRule6sAntipattern arg0, HListEmpty arg1) {
		Rule6Antipattern instance =  (Rule6Antipattern) arg0.getUnderlyingObject();
		instance.showInEditor();
		return null;
	}

}