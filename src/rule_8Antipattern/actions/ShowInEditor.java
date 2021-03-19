package rule_8Antipattern.actions;


import optionalanalyzer.metamodel.entity.MRule8sAntipattern;
import ro.lrg.xcore.metametamodel.ActionPerformer;
import ro.lrg.xcore.metametamodel.HListEmpty;
import ro.lrg.xcore.metametamodel.IActionPerformer;
import rule_8Antipattern.Rule8Antipattern;

@ActionPerformer
public class ShowInEditor implements IActionPerformer<Void, MRule8sAntipattern, HListEmpty>{

	@Override
	public Void performAction(MRule8sAntipattern arg0, HListEmpty arg1) {
		Rule8Antipattern instance =  (Rule8Antipattern) arg0.getUnderlyingObject();
		instance.showInEditor();
		return null;
	}

}