package rule_7Antipattern.actions;

import optionalanalyzer.metamodel.entity.MRule6sAntipattern;
import optionalanalyzer.metamodel.entity.MRule7sAntipattern;
import ro.lrg.xcore.metametamodel.ActionPerformer;
import ro.lrg.xcore.metametamodel.HListEmpty;
import ro.lrg.xcore.metametamodel.IActionPerformer;
import rule_6Antipattern.Rule6Antipattern;
import rule_7Antipattern.Rule7Antipattern;

@ActionPerformer
public class ShowInEditor implements IActionPerformer<Void, MRule7sAntipattern, HListEmpty>{

	@Override
	public Void performAction(MRule7sAntipattern arg0, HListEmpty arg1) {
		Rule7Antipattern instance =  (Rule7Antipattern) arg0.getUnderlyingObject();
		instance.showInEditor();
		return null;
	}

}