package rule26Antipattern.actions;

import optionalanalyzer.metamodel.entity.MRule26sAntipattern;
import ro.lrg.xcore.metametamodel.ActionPerformer;
import ro.lrg.xcore.metametamodel.HListEmpty;
import ro.lrg.xcore.metametamodel.IActionPerformer;
import utilities.Antipattern;

@ActionPerformer
public class ShowInEditor implements IActionPerformer<Void, MRule26sAntipattern, HListEmpty>{

	@Override
	public Void performAction(MRule26sAntipattern arg0, HListEmpty arg1) {
		Antipattern instance =  (Antipattern) arg0.getUnderlyingObject();
		instance.showInEditor();
		return null;
	}

}