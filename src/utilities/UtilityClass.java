package utilities;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.Expression;
import org.eclipse.jdt.core.dom.FieldDeclaration;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.MethodInvocation;
import org.eclipse.jdt.core.dom.SingleVariableDeclaration;
import org.eclipse.jdt.core.dom.TypeDeclaration;
import org.eclipse.jdt.core.dom.VariableDeclarationFragment;
import org.eclipse.jdt.ui.JavaUI;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.texteditor.ITextEditor;

public class UtilityClass {

	public static CompilationUnit parse(ICompilationUnit unit) {

		ASTParser parser = ASTParser.newParser(AST.JLS14);
		parser.setKind(ASTParser.K_COMPILATION_UNIT);
		parser.setSource(unit);
		parser.setResolveBindings(true);
		return (CompilationUnit) parser.createAST(null); // parse
	}


	public static Optional<String> getInvocatorName(MethodInvocation methodInvocation) {
		String invocatorName = null;

		if(methodInvocation.getExpression() != null) {
			invocatorName = methodInvocation.getExpression().toString();
		}
		return Optional.ofNullable(invocatorName);
	}

	public static ITextEditor getITextEditor(ASTNode statement) {

		IEditorPart editor = null;
		try {
			ICompilationUnit unit = (ICompilationUnit) getCompilationUnit( statement).getJavaElement();
			editor = JavaUI.openInEditor((IJavaElement) unit);
		} catch (PartInitException e) {
			e.printStackTrace();
		} catch( JavaModelException e ) {
			e.printStackTrace();
		}
		if (!(editor instanceof ITextEditor)) {
			return null; 
		}
		ITextEditor ite = (ITextEditor)editor;
		return ite;
	}

	public static boolean isTypeOptional(String typeName) {
		typeName = removeGenericArguments(typeName);
		return isTypeNamePresent(typeName
				, Libraries.optionalTypes);
	}

	public static boolean isSpecialOptionalType(String typeName) {
		return isTypeNamePresent(typeName
				,Libraries.specialOptionalTypes);
	}

	public static boolean isSimpleOptionalType(String typeName) {
		typeName = removeGenericArguments(typeName);
		return typeName.equals("java.util.Optional");
	}

	public static boolean isCollectionType(final String typeName) {
		String rawTypeName = removeGenericArguments(typeName);
		return isTypeNamePresent(rawTypeName
				, Libraries.collectionTypes);
	}

	public static boolean hasBadGenericTypeArgumentForOptional(String typeName) {
		String[] genericArguments = getGenericTypes(typeName);

		return Arrays.asList(genericArguments).stream()
				.filter(genericArgument -> isTypeNamePresent(genericArgument, Libraries.badGenericTypesForOptional))
		        .findAny()
		        .isPresent();
				
	}

	public static boolean hasOptionalTypeInside(String typeName) {
			return hasGenericTypeInside(Libraries.optionalTypes, typeName);

	}

	public static String[] getGenericTypes(String typeName) {
		int startIndex = typeName.indexOf("<");
		int endIndex = typeName.lastIndexOf(">");

		if(startIndex == -1 || endIndex == -1) {
			return new String[ 0 ];
		}

		try {
		return typeName.substring(startIndex + 1, endIndex).split(", *");
		} catch(IndexOutOfBoundsException indexOutOfBoundsException) {
			return new String[ 0 ];
		}
	}

	private static boolean hasGenericTypeInside(List<String> listOfTypes, String typeName) {
		List<String> genericTypes = Arrays.asList(getGenericTypes(typeName));

		List<String> rawGenericTypes = genericTypes.stream()
				.map(UtilityClass::removeGenericArguments)
				.collect(Collectors.toList());

		boolean contains =  listOfTypes.stream()
				.filter(optionalTypeName -> rawGenericTypes.contains(optionalTypeName.toString()))
				.findAny()
				.isPresent();


		if(!contains) {
			contains = genericTypes.stream()
					.filter(type -> hasGenericTypeInside(listOfTypes, type))
					.findAny()
					.isPresent();
		}
		return contains;
	}

	private static boolean isTypeNamePresent(String typeName, List<String> types) {
		return types.contains(typeName);
	}


	public static CompilationUnit getCompilationUnit(ASTNode astNode) {
		ASTNode node = astNode;
		while( !(node instanceof CompilationUnit) ) {
			node = node.getParent();
		}

		return (CompilationUnit)node;
	}

	private static String removeGenericArguments(String typeName) {

		String[] splittedParts = typeName.split("<.*>");
		if(splittedParts.length != 0) {
			return typeName.split("<.*>")[ 0 ];
		}

		return "";
	}

	public static List<MethodDeclaration> getMethodDeclarations(ASTNode astNode) {

		final List<MethodDeclaration> methodDeclarations = new LinkedList<>();
		astNode.accept(new ASTVisitor() {
			@Override
			public boolean visit(MethodDeclaration methodDeclaration) {

				methodDeclarations.add(methodDeclaration);
				return super.visit(methodDeclaration);
			}

		});
		return methodDeclarations;
	}

	public static boolean containsAtLeastOneOptionalAsParameter(MethodDeclaration methodDeclaration) {
		List<SingleVariableDeclaration> parameters = methodDeclaration.parameters();

		return parameters.stream()
				.filter(UtilityClass::hasType)
				.map(param -> param.getType().resolveBinding().getQualifiedName())
				.filter(typeName -> utilities.UtilityClass.isTypeOptional(typeName))
				.findFirst()
				.isPresent();
	}
	
	private static boolean hasType(SingleVariableDeclaration singleVariableDeclaration) {
		try {
			singleVariableDeclaration.getType()
			.resolveBinding()
			.getQualifiedName();
			return true;
		} catch (NullPointerException e) {
			return false;
		}
	}

	public static SingleVariableDeclaration getFirstOptionalParameter(MethodDeclaration methodDeclaration) {

		List<SingleVariableDeclaration> parameters = methodDeclaration.parameters();

		return parameters.stream()
				.filter(UtilityClass::isParameterOfTypeOptional)
				.findFirst()
				.get();
	}

	public static boolean isParameterOfTypeOptional(SingleVariableDeclaration singleVariableDeclaration) {
		String typeName = singleVariableDeclaration.getType().resolveBinding().getQualifiedName();
		return utilities.UtilityClass.isTypeOptional(typeName);
	}

	public static boolean isSetter(MethodDeclaration methodDeclaration) {
		Optional<TypeDeclaration> typeDeclaration = getTypeDeclaration(methodDeclaration);
		if(!typeDeclaration.isPresent()) {
			return false;
		}

		String methodName = methodDeclaration.getName().toString();
		FieldDeclaration[] fields = typeDeclaration.get().getFields();

		return Arrays.asList( fields ).stream()
				.map(UtilityClass::createSetterName)
				.filter(setterName -> setterName.equals(methodName))
				.findAny()
				.isPresent();


	}

	private static String createSetterName(FieldDeclaration fieldDeclaration) {
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

	private static Optional<TypeDeclaration> getTypeDeclaration(MethodDeclaration methodDeclaration) {
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
	
	public static Optional<String> getTypeName(Expression expression) {
		return Optional.ofNullable(expression.resolveTypeBinding().getQualifiedName());
	}

}
