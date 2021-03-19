package rule21Antipattern.actions;

import optionalanalyzer.metamodel.entity.MRule21sAntipattern;
import ro.lrg.xcore.metametamodel.ActionPerformer;
import ro.lrg.xcore.metametamodel.HListEmpty;
import ro.lrg.xcore.metametamodel.IActionPerformer;
import rule21Antipattern.Rule21Antipattern;

@ActionPerformer
public class ShowInEditor implements IActionPerformer<Void, MRule21sAntipattern, HListEmpty>{

	@Override
	public Void performAction(MRule21sAntipattern arg0, HListEmpty arg1) {
		Rule21Antipattern instance =  (Rule21Antipattern) arg0.getUnderlyingObject();
		instance.showInEditor();
		return null;
	}

}