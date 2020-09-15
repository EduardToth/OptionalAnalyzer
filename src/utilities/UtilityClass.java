package utilities;
import java.io.FileReader;
import java.io.IOException;
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
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class UtilityClass {

	private static final String pathToOptionalTypesJSON = "C:\\Users\\40752\\eclipse-workspace\\OptionalAnalizer\\jsonFolder\\optionalTypes.json";
	private static final String pathToSpecialOptionalTypesJSON = "C:\\Users\\40752\\eclipse-workspace\\OptionalAnalizer\\jsonFolder\\specialOptionalType.json";
	private static final String pathToCollectionTypesJSON = "C:\\Users\\40752\\eclipse-workspace\\OptionalAnalizer\\jsonFolder\\collectionTypes.json";
	private static final String pathToBadGenericTypesForJSON = "C:\\Users\\40752\\eclipse-workspace\\OptionalAnalizer\\jsonFolder\\badGenericTypesForOptional.json";

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
				, pathToOptionalTypesJSON);
	}

	public static boolean isSpecialOptionalType(String typeName) {
		return isTypeNamePresent(typeName
				,pathToSpecialOptionalTypesJSON);
	}

	public static boolean isSimpleOptionalType(String typeName) {
		typeName = removeGenericArguments(typeName);
		return typeName.equals("java.util.Optional");
	}

	public static boolean isCollectionType(final String typeName) {
		String rawTypeName = removeGenericArguments(typeName);
		return isTypeNamePresent(rawTypeName
				, pathToCollectionTypesJSON);
	}

	public static boolean hasBadGenericTypeArgumentForOptional(String typeName) {
		String[] genericArguments = getGenericTypes(typeName);

		return Arrays.asList(genericArguments).stream()
				.filter(genericArgument -> isTypeNamePresent(genericArgument,  pathToBadGenericTypesForJSON))
				.findAny()
				.isPresent();
	}

	public static boolean hasOptionalTypeInside(String typeName) {
		Optional<JSONArray> jsonArray = readJSONFile(pathToOptionalTypesJSON); 


		if(jsonArray.isPresent()) {
			return hasGenericTypeInside(jsonArray.get(), typeName);
		}
		return false;

	}

	public static String[] getGenericTypes(String typeName) {
		int startIndex = typeName.indexOf("<");
		int endIndex = typeName.lastIndexOf(">");

		if(startIndex == -1) {
			return new String[ 0 ];
		}

		return typeName.substring(startIndex + 1, endIndex).split(", *");
	}

	private static boolean hasGenericTypeInside(JSONArray arrayOfTypes, String typeName) {
		List<String> genericTypes = Arrays.asList(getGenericTypes(typeName));

		List<String> rawGenericTypes = genericTypes.stream()
				.map(UtilityClass::removeGenericArguments)
				.collect(Collectors.toList());

		return Arrays.asList(arrayOfTypes.toArray()).stream()
				.filter(optionalTypeName -> rawGenericTypes.contains(optionalTypeName.toString()))
				.findAny()
				.isPresent();
	}


	private static boolean isTypeNamePresent(String typeName, String pathToJsonFile) {
		Optional<JSONArray> jsonArray = readJSONFile(pathToJsonFile); 

		return jsonArray
				.filter(array -> array.contains(typeName))
				.isPresent();
	}


	private static Optional<JSONArray> readJSONFile(String fileName) {
		Optional<JSONArray> jsonArray = Optional.empty();

		try {
			Object obj = new JSONParser().parse(new FileReader(fileName));
			if(obj instanceof JSONArray) {
				jsonArray = Optional.of( (JSONArray)obj );
			}
		} catch (IOException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jsonArray;
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
				.map(param -> param.getType().resolveBinding().getQualifiedName())
				.filter(typeName -> utilities.UtilityClass.isTypeOptional(typeName))
				.findFirst()
				.isPresent();
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

}
