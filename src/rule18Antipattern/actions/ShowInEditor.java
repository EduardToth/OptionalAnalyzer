package rule18Antipattern.actions;

import optionalanalyzer.metamodel.entity.MRule18sAntipattern;
import ro.lrg.xcore.metametamodel.ActionPerformer;
import ro.lrg.xcore.metametamodel.HListEmpty;
import ro.lrg.xcore.metametamodel.IActionPerformer;
import rule18Antipattern.Rule18Antipattern;

@ActionPerformer
public class ShowInEditor implements IActionPerformer<Void, MRule18sAntipattern, HListEmpty>{

	@Override
	public Void performAction(MRule18sAntipattern arg0, HListEmpty arg1) {
		Rule18Antipattern instance =  (Rule18Antipattern) arg0.getUnderlyingObject();
		instance.showInEditor();
		return null;
	}

}