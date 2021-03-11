package rule17Antipattern;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.MethodDeclaration;

import optionalanalizer.metamodel.entity.MRule17Atom;
import optionalanalizer.metamodel.factory.Factory;
import utilities.Unit;
import utilities.UtilityClass;

public class Rule17AntipatternFinder {

	public List<MRule17Atom> getMAtoms(ASTNode astNode) {
		
		List<MethodDeclaration> methodDeclarations = UtilityClass.getMethodDeclarations(astNode);
		final Unit<MethodDeclaration> currentMethodDeclaration = new Unit<>(null);
		
		return methodDeclarations.stream()
				.peek(currentMethodDeclaration::setAt0)
				.filter(UtilityClass.negatePredicate(MethodDeclaration::isConstructor))
				.map(this::getTypeName)
				.filter(this::isExpressionOfTypeOptionalContainingArrayOrCollection)
				.map(el -> Rule17Antipattern.getInstance(currentMethodDeclaration.getValue0()))
				.filter(Optional::isPresent)
				.map(Optional::get)
				.map(Factory.getInstance()::createMRule17Atom)
				.collect(Collectors.toList());
		
		 
	}
	
	private String getTypeName(MethodDeclaration methodDeclaration) {
		String typeName = "";
		try {
			typeName = methodDeclaration.resolveBinding().getReturnType().getQualifiedName();
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
