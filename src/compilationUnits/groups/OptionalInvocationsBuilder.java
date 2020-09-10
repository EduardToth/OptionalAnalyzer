package compilationUnits.groups;

import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.dom.CompilationUnit;

import optionalanalizer.metamodel.entity.MCompilationUnit;
import optionalanalizer.metamodel.entity.MInvocation;
import optionalanalizer.metamodel.factory.Factory;
import ro.lrg.xcore.metametamodel.Group;
import ro.lrg.xcore.metametamodel.IRelationBuilder;
import ro.lrg.xcore.metametamodel.RelationBuilder;
import utilities.MethodInvokedFromOptionalVisitor;
import utilities.UtilityClass;

@RelationBuilder
public class OptionalInvocationsBuilder implements IRelationBuilder<MInvocation, MCompilationUnit>{

	private String invokedMethodName = "isPresent";

	@Override
	public Group<MInvocation> buildGroup(MCompilationUnit mCompilationUnit) {
		ICompilationUnit iCompilationUnit = (ICompilationUnit)mCompilationUnit.getUnderlyingObject();
		Group<MInvocation> group = new Group<>();
		CompilationUnit compilationUnit = UtilityClass.parse(iCompilationUnit);
		MethodInvokedFromOptionalVisitor methodInvokedFromOptionalVisitor = 
				new MethodInvokedFromOptionalVisitor(invokedMethodName);

		compilationUnit.accept(methodInvokedFromOptionalVisitor);
		
		methodInvokedFromOptionalVisitor.getInvocations()
		.stream()
		.map(inv -> Factory.getInstance().createMInvocation(inv))
		.forEach(mInv -> group.add( mInv));

		return group;
	}

	public Group<MInvocation> buildGroup(MCompilationUnit mCompilationUnit, String invokedMethodName) {
		setInvokedMethodName( invokedMethodName );
		return buildGroup(mCompilationUnit);
	}

	public void setInvokedMethodName(String invokedMethodName) {
		this.invokedMethodName = invokedMethodName;
	}
}
