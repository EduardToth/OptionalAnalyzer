package projects.groups;

import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.IJavaProject;
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
public class CompilationUnitDetector implements IRelationBuilder<MCompilationUnit, MProject>{

	

	@Override
	public Group<MCompilationUnit> buildGroup(MProject arg0) {


		IJavaProject iProject = (IJavaProject)arg0.getUnderlyingObject();

		Group<MCompilationUnit> compilationUnits = new Group<>();
		
		try {
		
			IPackageFragment[] packages = JavaCore.create(iProject.getProject()).getPackageFragments();

			for(IPackageFragment iPackage : packages) {
				getIntoPackage(compilationUnits, iPackage);
			}

		} catch (JavaModelException e) {
			e.printStackTrace();
		}
		return compilationUnits;
	}

	public void getIntoPackage(Group<MCompilationUnit> compilationUnits, IPackageFragment iPackage) {
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