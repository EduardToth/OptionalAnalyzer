package utilities;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.MethodInvocation;
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

	private static CompilationUnit getCompilationUnit(ASTNode statement) {
		ASTNode node = statement;
		while( true ) {
			node = node.getParent();
			if(node instanceof CompilationUnit) {
				break;
			}

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
}
