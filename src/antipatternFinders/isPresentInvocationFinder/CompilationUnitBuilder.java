package antipatternFinders.isPresentInvocationFinder;

import org.eclipse.core.resources.IProject;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.IPackageFragment;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;

import optionalanalizer.metamodel.factory.Factory;
import optionalanalizer.metamodel.entity.MCompilationUnit;
import optionalanalizer.metamodel.entity.MProject;
import ro.lrg.xcore.metametamodel.Group;
import ro.lrg.xcore.metametamodel.IRelationBuilder;
import ro.lrg.xcore.metametamodel.RelationBuilder;

@RelationBuilder
public class CompilationUnitBuilder implements IRelationBuilder<MCompilationUnit, MProject>{

	private Group<MCompilationUnit> compilationUnits = new Group<>();

	@Override
	public Group<MCompilationUnit> buildGroup(MProject arg0) {


		IProject iProject = (IProject)arg0.getUnderlyingObject();

		try {
			IPackageFragment[] packages = JavaCore.create(iProject).getPackageFragments();

			for(IPackageFragment iPackage : packages) {
				getIntoPackage( iPackage );
			}

		} catch (JavaModelException e) {
			e.printStackTrace();
		}
		return compilationUnits;
	}

	public void getIntoPackage(IPackageFragment iPackage) {
		try {
			ICompilationUnit[] units = iPackage.getCompilationUnits();

			for(ICompilationUnit unit : units) {
				compilationUnits.add(Factory.getInstance().createMCompilationUnit(unit));
			}
		} catch (JavaModelException e) {
			e.printStackTrace();
		}
	}

}