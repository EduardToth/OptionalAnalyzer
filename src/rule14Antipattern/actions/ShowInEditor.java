package rule14Antipattern.actions;

import optionalanalyzer.metamodel.entity.MRule14sAntipattern;
import ro.lrg.xcore.metametamodel.ActionPerformer;
import ro.lrg.xcore.metametamodel.HListEmpty;
import ro.lrg.xcore.metametamodel.IActionPerformer;
import rule14Antipattern.Rule14Antipattern;

@ActionPerformer
public class ShowInEditor implements IActionPerformer<Void, MRule14sAntipattern, HListEmpty>{

	@Override
	public Void performAction(MRule14sAntipattern arg0, HListEmpty arg1) {
		Rule14Antipattern instance =  (Rule14Antipattern) arg0.getUnderlyingObject();
		instance.showInEditor();
		return null;
	}

}