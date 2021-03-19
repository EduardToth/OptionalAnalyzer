package projects.properties;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IPackageFragment;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.TypeDeclaration;

import optionalanalyzer.metamodel.entity.MProject;
import ro.lrg.xcore.metametamodel.IPropertyComputer;
import ro.lrg.xcore.metametamodel.PropertyComputer;
import utilities.UtilityClass;

@PropertyComputer
public class GetNrOfClasses implements IPropertyComputer<Integer, MProject>{


	@Override
	public Integer compute(MProject arg0) {
		IJavaProject iJavaProject = ((IJavaProject)arg0.getUnderlyingObject());
		
		return getPackages(iJavaProject).stream()
				.map(this::getCompilationUnits)
				.flatMap(List::stream)
				.map(this::getNrOfTypeDeclarations)
				.reduce((nr1, nr2) -> nr1 + nr2)
				.orElse(0);

	}

	private List<IPackageFragment> getPackages(IJavaProject iJavaProject) {
		IPackageFragment[] iPackageFragments = new IPackageFragment[ 0 ];
		try {
			iPackageFragments = iJavaProject.getPackageFragments();
		} catch (JavaModelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return Arrays.asList(iPackageFragments);
	}

	private List<ICompilationUnit> getCompilationUnits(IPackageFragment iPackageFragment) {
		ICompilationUnit[] iCompilationUnits = new ICompilationUnit[ 0 ];

		try {
			iCompilationUnits = iPackageFragment.getCompilationUnits();
		} catch (JavaModelException e) {
			e.printStackTrace();
		}

		return Arrays.asList(iCompilationUnits);
	}

	private int getNrOfTypeDeclarations(ICompilationUnit iCompilationUnit) {
		CompilationUnit compilationUnit = UtilityClass.parse(iCompilationUnit);
		AtomicInteger nrOfTypeDeclarations = new AtomicInteger(0);
		compilationUnit.accept(new ASTVisitor() {

			@Override
			public boolean visit(TypeDeclaration typeDeclaration) {
				nrOfTypeDeclarations.incrementAndGet();
				return super.visit(typeDeclaration);
			}
		});
		return nrOfTypeDeclarations.get();
	}
}