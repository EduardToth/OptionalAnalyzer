package rule_1Antipattern.actions;

import optionalanalizer.metamodel.entity.MRule1sAntipattern;
import ro.lrg.xcore.metametamodel.ActionPerformer;
import ro.lrg.xcore.metametamodel.HListEmpty;
import ro.lrg.xcore.metametamodel.IActionPerformer;
import rule_1Antipattern.Rule1Antipattern;


@ActionPerformer
public class ShowInEditor implements IActionPerformer<Void, MRule1sAntipattern, HListEmpty>{

	@Override
	public Void performAction(MRule1sAntipattern arg0, HListEmpty arg1) {
		Rule1Antipattern rule1Antipattern =  (Rule1Antipattern) arg0.getUnderlyingObject();
		rule1Antipattern.showInEditor();
		return null;
	}

}