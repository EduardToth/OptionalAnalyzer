package compilationUnits.groups;

import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.MethodInvocation;

import optionalanalizer.metamodel.entity.MCompilationUnit;
import optionalanalizer.metamodel.entity.MInvocation;
import optionalanalizer.metamodel.factory.Factory;
import ro.lrg.xcore.metametamodel.Group;
import ro.lrg.xcore.metametamodel.IRelationBuilder;
import ro.lrg.xcore.metametamodel.RelationBuilder;
import utilities.Unit;
import utilities.UtilityClass;

@RelationBuilder
public class OptionalInvocationsBuilder implements IRelationBuilder<MInvocation, MCompilationUnit>{

	final private Unit<String> invokedMethodName = new Unit<>("isPresent");

	@Override
	public Group<MInvocation> buildGroup(MCompilationUnit mCompilationUnit) {
		ICompilationUnit iCompilationUnit = (ICompilationUnit)mCompilationUnit.getUnderlyingObject();
		Group<MInvocation> group = new Group<>();

		CompilationUnit compilationUnit = UtilityClass.parse(iCompilationUnit);



		compilationUnit.accept(new ASTVisitor() {

			@Override
			public boolean visit(MethodInvocation invocation) {
				String invokedMethodName = invocation.getName().toString();
				if(itsFine(invocation, invokedMethodName)) {
					group.add(Factory.getInstance().createMInvocation(invocation));

				}
				return super.visit( invocation );
			}

			private boolean itsFine(MethodInvocation invocation, String invokedMethodName) {
				return invocation.getExpression() != null &&
						UtilityClass.isInvocatorOfOptionalType(invocation.getExpression()
								.resolveTypeBinding().getQualifiedName())
						&& invokedMethodName.equals(OptionalInvocationsBuilder.this.invokedMethodName.getValue0());
			}
		});

		return group;
	}

	public Group<MInvocation> buildGroup(MCompilationUnit mCompilationUnit, String invokedMethodName) {
		setInvokedMethodName( invokedMethodName );
		return buildGroup(mCompilationUnit);
	}

	public void setInvokedMethodName(String invokedMethodName) {
		this.invokedMethodName.setAt0(invokedMethodName);
	}


}
