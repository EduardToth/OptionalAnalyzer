package rule25Antipattern.actions;
import optionalanalyzer.metamodel.entity.MRule25sAntipattern;
import ro.lrg.xcore.metametamodel.ActionPerformer;
import ro.lrg.xcore.metametamodel.HListEmpty;
import ro.lrg.xcore.metametamodel.IActionPerformer;
import rule25Antipattern.Rule25Antipattern;

@ActionPerformer
public class ShowInEditor implements IActionPerformer<Void, MRule25sAntipattern, HListEmpty>{

	@Override
	public Void performAction(MRule25sAntipattern arg0, HListEmpty arg1) {
		Rule25Antipattern instance =  (Rule25Antipattern) arg0.getUnderlyingObject();
		instance.showInEditor();
		return null;
	}

}