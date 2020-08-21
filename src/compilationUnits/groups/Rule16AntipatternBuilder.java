package compilationUnits.groups;

import java.util.LinkedList;
import java.util.List;

import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.SingleVariableDeclaration;

import optionalanalizer.metamodel.entity.MCompilationUnit;
import optionalanalizer.metamodel.entity.MVariableDeclaration;
import optionalanalizer.metamodel.factory.Factory;
import ro.lrg.xcore.metametamodel.Group;
import ro.lrg.xcore.metametamodel.IRelationBuilder;
import ro.lrg.xcore.metametamodel.RelationBuilder;
import utilities.UtilityClass;

@RelationBuilder
public class Rule16AntipatternBuilder  implements IRelationBuilder<MVariableDeclaration, MCompilationUnit>{

	@Override
	public Group<MVariableDeclaration> buildGroup(MCompilationUnit arg0) {

		ICompilationUnit iCompilationUnit = (ICompilationUnit) arg0.getUnderlyingObject();
		List<MethodDeclaration> methodDeclarations = getMethodDeclarations(iCompilationUnit);

		Group<MVariableDeclaration> group = new Group<>();
		methodDeclarations.stream()
		.filter(this::containsAtLeastOneOptionalAsParameter)
		.map(this::getFirstOptionalParameter)
		.map(elem -> Factory.getInstance().createMVariableDeclaration(elem))
		.forEach(elem -> group.add( elem ) );
		
		return group;
	}

	private List<MethodDeclaration> getMethodDeclarations(ICompilationUnit iCompilationUnit) {

		final List<MethodDeclaration> methodDeclarations = new LinkedList<>();
		CompilationUnit compilationUnit = UtilityClass.parse(iCompilationUnit);
		compilationUnit.accept(new ASTVisitor() {
			@Override
			public boolean visit(MethodDeclaration methodDeclaration) {

				methodDeclarations.add(methodDeclaration);
				return super.visit(methodDeclaration);

			}

		});
		return methodDeclarations;
	}

	private boolean containsAtLeastOneOptionalAsParameter(MethodDeclaration methodDeclaration) {
		List<SingleVariableDeclaration> parameters = methodDeclaration.parameters();

		return parameters.stream()
				.map(param -> param.getType().resolveBinding().getQualifiedName())
				.filter(typeName -> UtilityClass.isInvocatorOfOptionalType(typeName))
				.findFirst()
				.isPresent();
	}

	private SingleVariableDeclaration getFirstOptionalParameter(MethodDeclaration methodDeclaration) {

		List<SingleVariableDeclaration> parameters = methodDeclaration.parameters();

		return parameters.stream()
				.filter(this::isParameterOfTypeOptional)
				.findFirst()
				.get();
	}

	private boolean isParameterOfTypeOptional(SingleVariableDeclaration singleVariableDeclaration) {
		String typeName = singleVariableDeclaration.getType().resolveBinding().getQualifiedName();
		return UtilityClass.isInvocatorOfOptionalType(typeName);
	}

}