package rule10Antipattern.actions;

import optionalanalyzer.metamodel.entity.MRule10sAntipattern;
import ro.lrg.xcore.metametamodel.ActionPerformer;
import ro.lrg.xcore.metametamodel.HListEmpty;
import ro.lrg.xcore.metametamodel.IActionPerformer;
import utilities.Antipattern;

@ActionPerformer
public class ShowInEditor implements IActionPerformer<Void, MRule10sAntipattern, HListEmpty>{

	@Override
	public Void performAction(MRule10sAntipattern arg0, HListEmpty arg1) {
		Antipattern instance =  (Antipattern) arg0.getUnderlyingObject();
		instance.showInEditor();
		return null;
	}

}