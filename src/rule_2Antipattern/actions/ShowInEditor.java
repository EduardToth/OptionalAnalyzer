package rule_2Antipattern.actions;

import optionalanalyzer.metamodel.entity.MRule2sAntipattern;
import ro.lrg.xcore.metametamodel.ActionPerformer;
import ro.lrg.xcore.metametamodel.HListEmpty;
import ro.lrg.xcore.metametamodel.IActionPerformer;
import rule_2Antipattern.Rule2Antipattern;

@ActionPerformer
public class ShowInEditor implements IActionPerformer<Void, MRule2sAntipattern, HListEmpty>{

	@Override
	public Void performAction(MRule2sAntipattern arg0, HListEmpty arg1) {
		Rule2Antipattern rule2Antipattern =  (Rule2Antipattern) arg0.getUnderlyingObject();
		rule2Antipattern.showInEditor();
		return null;
	}

}