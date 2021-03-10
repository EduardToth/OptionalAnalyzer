package packages.groups;

import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.IPackageFragment;
import org.eclipse.jdt.core.JavaModelException;

import optionalanalizer.metamodel.entity.MCompilationUnit;
import optionalanalizer.metamodel.entity.MPackage;
import optionalanalizer.metamodel.factory.Factory;
import ro.lrg.xcore.metametamodel.Group;
import ro.lrg.xcore.metametamodel.IRelationBuilder;
import ro.lrg.xcore.metametamodel.RelationBuilder;

@RelationBuilder
public class CompilationUnitDetector implements IRelationBuilder<MCompilationUnit, MPackage>{



	@Override
	public Group<MCompilationUnit> buildGroup(MPackage arg0) {

		Group<MCompilationUnit> compilationUnits = new Group<>();
		IPackageFragment iPackageFragment = (IPackageFragment)arg0.getUnderlyingObject();	
		getIntoPackage(compilationUnits, iPackageFragment);

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