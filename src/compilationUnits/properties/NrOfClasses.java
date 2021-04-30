package compilationUnits.properties;

import java.util.concurrent.atomic.AtomicInteger;

import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.TypeDeclaration;

import optionalanalyzer.metamodel.entity.MCompilationUnit;
import ro.lrg.xcore.metametamodel.IPropertyComputer;
import ro.lrg.xcore.metametamodel.PropertyComputer;
import utilities.UtilityClass;

@PropertyComputer
public class NrOfClasses implements IPropertyComputer<Integer, MCompilationUnit>{


	@Override
	public Integer compute(MCompilationUnit arg0) {
		
		ICompilationUnit iCompilationUnit = (ICompilationUnit) arg0.getUnderlyingObject();
		
		return getNrOfTypeDeclarations(iCompilationUnit);
	

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
