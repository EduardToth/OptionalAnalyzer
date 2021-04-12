package utilities;
import java.util.Arrays;
import java.util.Collections;
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

		ASTParser parser = ASTParser.newParser(AST.JLS15);
		parser.setKind(ASTParser.K_COMPILATION_UNIT);
		parser.setSource(unit);
		parser.setResolveBindings(true);
		return (CompilationUnit) parser.createAST(null); 
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
				.anyMatch(genericArgument -> isTypeNamePresent(genericArgument, Libraries.badGenericTypesForOptional));


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
				.anyMatch(optionalTypeName -> rawGenericTypes.contains(optionalTypeName.toString()));

		if(!contains) {
			contains = genericTypes.stream()
					.anyMatch(type -> hasGenericTypeInside(listOfTypes, type));
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
				.map(UtilityClass::getTypeName)
				.flatMap(Optional::stream)
				.anyMatch(UtilityClass::isTypeOptional);
	}

	private static Optional<String> getTypeName(SingleVariableDeclaration singleVariableDeclaration) {
		String typeName = null;
		try {
			typeName = singleVariableDeclaration.getType().resolveBinding().getQualifiedName();
		}catch(Exception ex) {}

		return Optional.ofNullable(typeName);
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

	public static Optional<SingleVariableDeclaration> getFirstOptionalParameter(MethodDeclaration methodDeclaration) {

		List<SingleVariableDeclaration> parameters = methodDeclaration.parameters();

		return parameters.stream()
				.filter(UtilityClass::isParameterOfTypeOptional)
				.findFirst();

	}

	public static boolean isParameterOfTypeOptional(SingleVariableDeclaration singleVariableDeclaration) {
		String typeName = "";
		try {
			typeName = singleVariableDeclaration.getType()
					.resolveBinding()
					.getQualifiedName();
		} catch(NullPointerException npe) {
			return false;
		}
		return utilities.UtilityClass.isTypeOptional(typeName);
	}

	public static boolean isSetter(MethodDeclaration methodDeclaration) {
		Optional<TypeDeclaration> typeDeclaration = getTypeDeclaration(methodDeclaration);

		String methodName = methodDeclaration.getName().toString();

		return typeDeclaration
				.map(TypeDeclaration::getFields)
				.map(Arrays::asList)
				.orElse(Collections.emptyList())
				.stream()
				.map(UtilityClass::createSetterName)
				.anyMatch(methodName::equals);
	}

	private static String createSetterName(FieldDeclaration fieldDeclaration) {

		List<?> fragments = fieldDeclaration.fragments();
		
		return fragments.stream()
				.findAny()
				.filter(VariableDeclarationFragment.class::isInstance)
				.map(VariableDeclarationFragment.class::cast)
				.map(UtilityClass::getPossibleSetterName)
				.orElseThrow();
	}

	private static String getPossibleSetterName(VariableDeclarationFragment variableDeclarationFragment) {
		String methodName = "set";

		String fieldName = variableDeclarationFragment.getName().toString();
		char firstLetterOfTheFieldName = fieldName.charAt(0);
		char upperCaseLetter = Character.toUpperCase( firstLetterOfTheFieldName);

		methodName += upperCaseLetter + fieldName.substring(1);

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
