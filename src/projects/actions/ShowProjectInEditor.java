package projects.actions;

import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.ui.JavaUI;
import org.eclipse.ui.PartInitException;

import optionalanalizer.metamodel.entity.MProject;
import ro.lrg.xcore.metametamodel.ActionPerformer;
import ro.lrg.xcore.metametamodel.HListEmpty;
import ro.lrg.xcore.metametamodel.IActionPerformer;

@ActionPerformer
public class ShowProjectInEditor  implements IActionPerformer<Void, MProject, HListEmpty>{

	@Override
	public Void performAction(MProject arg0, HListEmpty arg1) {
		try {
			JavaUI.openInEditor((IJavaElement) arg0.getUnderlyingObject(), true, true);
		} catch (PartInitException e) {
			e.printStackTrace();
		} catch( JavaModelException e ) {
			e.printStackTrace();
		}
		return null;
	}

}
