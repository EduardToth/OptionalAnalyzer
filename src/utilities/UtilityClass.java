package utilities;


import java.util.Arrays;
import java.util.Optional;

import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.MethodInvocation;

public class UtilityClass {
	
	

	public static CompilationUnit parse(ICompilationUnit unit) {
		ASTParser parser = ASTParser.newParser(AST.JLS3);
		parser.setKind(ASTParser.K_COMPILATION_UNIT);
		parser.setSource(unit);
		parser.setResolveBindings(true);
		return (CompilationUnit) parser.createAST(null); // parse
	}

	public static boolean isInvocatorOfOptionalType(String typeName) {

		String[] optionalTypes = {"java.util.OptionalFloat",
				"java.util.OptionalLong",
				"java.util.Optional",
				"java.util.OptionalInt",
				"java.Util.OptionalDouble"};

		String rawType = typeName.split("<.*>")[ 0 ];
		return Arrays.asList(optionalTypes).stream()
				.filter(type -> type.equals(rawType))
				.findFirst()
				.isPresent();
	}
	
	public static Optional<String> getInvocatorName(MethodInvocation methodInvocation) {
		String invocatorName = null;
		
		if(methodInvocation.getExpression() != null) {
			invocatorName = methodInvocation.getExpression().toString();
		}
		
		return Optional.ofNullable(invocatorName);
	}
}
