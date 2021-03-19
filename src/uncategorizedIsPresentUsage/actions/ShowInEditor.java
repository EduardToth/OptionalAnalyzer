package uncategorizedIsPresentUsage.actions;


import optionalanalyzer.metamodel.entity.MUncategorizedIsPresentPossibleAntipattern;
import ro.lrg.xcore.metametamodel.ActionPerformer;
import ro.lrg.xcore.metametamodel.HListEmpty;
import ro.lrg.xcore.metametamodel.IActionPerformer;
import utilities.Antipattern;

@ActionPerformer
public class ShowInEditor implements IActionPerformer<Void, MUncategorizedIsPresentPossibleAntipattern, HListEmpty>{

	@Override
	public Void performAction(MUncategorizedIsPresentPossibleAntipattern arg0, HListEmpty arg1) {
		Antipattern instance =  (Antipattern) arg0.getUnderlyingObject();
		instance.showInEditor();
		return null;
	}

}