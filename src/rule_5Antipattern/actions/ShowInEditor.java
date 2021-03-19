package rule_5Antipattern.actions;

import optionalanalyzer.metamodel.entity.MRule5sAntipattern;
import ro.lrg.xcore.metametamodel.ActionPerformer;
import ro.lrg.xcore.metametamodel.HListEmpty;
import ro.lrg.xcore.metametamodel.IActionPerformer;
import rule_5Antipattern.Rule5Antipattern;

@ActionPerformer
public class ShowInEditor implements IActionPerformer<Void, MRule5sAntipattern, HListEmpty>{

	@Override
	public Void performAction(MRule5sAntipattern arg0, HListEmpty arg1) {
		Rule5Antipattern instance =  (Rule5Antipattern) arg0.getUnderlyingObject();
		instance.showInEditor();
		return null;
	}

}