package rule25Antipattern.actions;
import optionalanalizer.metamodel.entity.MRule25Atom;
import ro.lrg.xcore.metametamodel.ActionPerformer;
import ro.lrg.xcore.metametamodel.HListEmpty;
import ro.lrg.xcore.metametamodel.IActionPerformer;
import rule25Antipattern.Rule25Antipattern;

@ActionPerformer
public class ShowInEditor implements IActionPerformer<Void, MRule25Atom, HListEmpty>{

	@Override
	public Void performAction(MRule25Atom arg0, HListEmpty arg1) {
		Rule25Antipattern instance =  (Rule25Antipattern) arg0.getUnderlyingObject();
		instance.showInEditor();
		return null;
	}

}