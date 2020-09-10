package compilationUnits.groups.rule14_15_16AntipatternBuilders;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.FieldDeclaration;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.SingleVariableDeclaration;
import org.eclipse.jdt.core.dom.TypeDeclaration;
import org.eclipse.jdt.core.dom.VariableDeclarationFragment;

public class UtilityClass {


	public List<MethodDeclaration> getMethodDeclarations(ICompilationUnit iCompilationUnit) {

		final List<MethodDeclaration> methodDeclarations = new LinkedList<>();
		CompilationUnit compilationUnit = utilities.UtilityClass.parse(iCompilationUnit);
		compilationUnit.accept(new ASTVisitor() {
			@Override
			public boolean visit(MethodDeclaration methodDeclaration) {

				methodDeclarations.add(methodDeclaration);
				return super.visit(methodDeclaration);

			}

		});
		return methodDeclarations;
	}

	public boolean containsAtLeastOneOptionalAsParameter(MethodDeclaration methodDeclaration) {
		List<SingleVariableDeclaration> parameters = methodDeclaration.parameters();

		return parameters.stream()
				.map(param -> param.getType().resolveBinding().getQualifiedName())
				.filter(typeName -> utilities.UtilityClass.isTypeOptional(typeName))
				.findFirst()
				.isPresent();
	}

	public SingleVariableDeclaration getFirstOptionalParameter(MethodDeclaration methodDeclaration) {

		List<SingleVariableDeclaration> parameters = methodDeclaration.parameters();

		return parameters.stream()
				.filter(this::isParameterOfTypeOptional)
				.findFirst()
				.get();
	}

	public boolean isParameterOfTypeOptional(SingleVariableDeclaration singleVariableDeclaration) {
		String typeName = singleVariableDeclaration.getType().resolveBinding().getQualifiedName();
		return utilities.UtilityClass.isTypeOptional(typeName);
	}

	public boolean isSetter(MethodDeclaration methodDeclaration) {
		Optional<TypeDeclaration> typeDeclaration = getTypeDeclaration(methodDeclaration);
		if(!typeDeclaration.isPresent()) {
			return false;
		}

		String methodName = methodDeclaration.getName().toString();
		FieldDeclaration[] fields = typeDeclaration.get().getFields();

		return Arrays.asList( fields ).stream()
				.map(this::createSetterName)
				.filter(setterName -> setterName.equals(methodName))
				.findAny()
				.isPresent();


	}

	private String createSetterName(FieldDeclaration fieldDeclaration) {
		String methodName = "set";
		Object fragment = fieldDeclaration.fragments().get( 0 );

		if(fragment instanceof VariableDeclarationFragment) {
			String fieldName = ((VariableDeclarationFragment) fragment).getName().toString();
			char firstLetterOfTheFieldName = fieldName.charAt(0);
			char upperCaseLetter = Character.toUpperCase( firstLetterOfTheFieldName);

			methodName += upperCaseLetter + fieldName.substring(1);
		}

		return methodName;
	}

	private Optional<TypeDeclaration> getTypeDeclaration(MethodDeclaration methodDeclaration) {
		ASTNode astNode = methodDeclaration.getParent();

		TypeDeclaration typeDeclaration = null;
		while(astNode != null) {
			if(astNode instanceof TypeDeclaration) {
				typeDeclaration = (TypeDeclaration) astNode;
				break;
			}
			astNode = astNode.getParent();
		}

		return Optional.ofNullable(typeDeclaration);
	}
}
