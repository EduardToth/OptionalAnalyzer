package rule16Antipattern;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.MethodDeclaration;

import optionalanalizer.metamodel.entity.MRule16Atom;
import optionalanalizer.metamodel.factory.Factory;
import utilities.UtilityClass;

public class Rule16AntipatternFinder {

	public List<MRule16Atom> getMAtoms(ASTNode astNode) {
		List<MethodDeclaration> methodDeclarations = UtilityClass.getMethodDeclarations(astNode);
        
		return methodDeclarations.stream()
				.filter(UtilityClass.negatePredicate(MethodDeclaration::isConstructor))
				.filter(UtilityClass.negatePredicate(UtilityClass::isSetter))
				.filter(UtilityClass::containsAtLeastOneOptionalAsParameter)
				.map(UtilityClass::getFirstOptionalParameter)
				.map(Rule16Antipattern::getInstance)
				.filter(Optional::isPresent)
				.map(Optional::get)
				.map(Factory.getInstance()::createMRule16Atom)
				.collect(Collectors.toList());
	}
}
