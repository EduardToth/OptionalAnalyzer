package rule16Antipattern.actions;

import optionalanalyzer.metamodel.entity.MRule16sAntipattern;
import ro.lrg.xcore.metametamodel.ActionPerformer;
import ro.lrg.xcore.metametamodel.HListEmpty;
import ro.lrg.xcore.metametamodel.IActionPerformer;
import rule16Antipattern.Rule16Antipattern;

@ActionPerformer
public class ShowInEditor implements IActionPerformer<Void, MRule16sAntipattern, HListEmpty>{

	@Override
	public Void performAction(MRule16sAntipattern arg0, HListEmpty arg1) {
		Rule16Antipattern instance =  (Rule16Antipattern) arg0.getUnderlyingObject();
		instance.showInEditor();
		return null;
	}

}