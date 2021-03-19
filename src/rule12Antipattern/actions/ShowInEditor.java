package rule12Antipattern.actions;

import optionalanalyzer.metamodel.entity.MRule12sAntipattern;
import ro.lrg.xcore.metametamodel.ActionPerformer;
import ro.lrg.xcore.metametamodel.HListEmpty;
import ro.lrg.xcore.metametamodel.IActionPerformer;
import rule12Antipattern.Rule12Antipattern;

@ActionPerformer
public class ShowInEditor implements IActionPerformer<Void, MRule12sAntipattern, HListEmpty>{

	@Override
	public Void performAction(MRule12sAntipattern arg0, HListEmpty arg1) {
		Rule12Antipattern instance =  (Rule12Antipattern) arg0.getUnderlyingObject();
		instance.showInEditor();
		return null;
	}

}