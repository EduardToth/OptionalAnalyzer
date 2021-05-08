package rule17Antipattern;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.MethodDeclaration;

import optionalanalyzer.metamodel.entity.MRule17sAntipattern;
import optionalanalyzer.metamodel.factory.Factory;

import utilities.UtilityClass;

public class Rule17AntipatternFinder {

	public List<MRule17sAntipattern> getMAntipatterns(ASTNode astNode) {
		
		List<MethodDeclaration> methodDeclarations = UtilityClass.getMethodDeclarations(astNode);
		
		return methodDeclarations.stream()
				.map(this::convertIntoAntipatternIfPossible)
				.flatMap(Optional::stream)
				.map(Factory.getInstance()::createMRule17sAntipattern)
				.collect(Collectors.toList());
	}
	
	private Optional<Rule17Antipattern> convertIntoAntipatternIfPossible(MethodDeclaration methodDeclaration) {
		if(!methodDeclaration.isConstructor()) {
			String typeName = getTypeName(methodDeclaration);
			if(isExpressionOfTypeOptionalContainingArrayOrCollection(typeName)) {
				return Rule17Antipattern.getInstance(methodDeclaration);
			}
		}
		
		return Optional.empty();
	}
	
	private String getTypeName(MethodDeclaration methodDeclaration) {
		String typeName = "";
		try {
			typeName = methodDeclaration.resolveBinding()
					.getReturnType()
					.getQualifiedName();
		}catch(NullPointerException npe) {}
		
		return typeName;
	}

	private boolean isExpressionOfTypeOptionalContainingArrayOrCollection(String typeName) {
		return UtilityClass.isTypeOptional(typeName) && 
				(isArrayTypeInside(typeName) || isCollectionTypeInside(typeName));
	}

	private boolean isCollectionTypeInside(String typeName) {
		String[] chosenTypes = UtilityClass.getGenericTypes(typeName);

		return Arrays.asList(chosenTypes)
				.stream()
				.anyMatch(UtilityClass::isCollectionType);
	}

	private boolean isArrayTypeInside(String typeName) {
		return typeName.indexOf("[]") != -1;
	}
}
