package rule15Antipattern.actions;

import optionalanalizer.metamodel.entity.MRule15sAntipattern;
import ro.lrg.xcore.metametamodel.ActionPerformer;
import ro.lrg.xcore.metametamodel.HListEmpty;
import ro.lrg.xcore.metametamodel.IActionPerformer;
import rule15Antipattern.Rule15Antipattern;

@ActionPerformer
public class ShowInEditor implements IActionPerformer<Void, MRule15sAntipattern, HListEmpty>{

	@Override
	public Void performAction(MRule15sAntipattern arg0, HListEmpty arg1) {
		Rule15Antipattern instance =  (Rule15Antipattern) arg0.getUnderlyingObject();
		instance.showInEditor();
		return null;
	}

}