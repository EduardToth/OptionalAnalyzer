package rule15Antipattern;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.MethodDeclaration;

import optionalanalizer.metamodel.entity.MRule15Atom;
import optionalanalizer.metamodel.factory.Factory;
import utilities.UtilityClass;

public class Rule15AntipatternFinder {

	public List<MRule15Atom> getMAtoms(ASTNode astNode) {
		List<MethodDeclaration> methodDeclarations = UtilityClass.getMethodDeclarations(astNode);
        
		return methodDeclarations.stream()
				.filter(UtilityClass::isSetter)
				.filter(UtilityClass::containsAtLeastOneOptionalAsParameter)
				.map(UtilityClass::getFirstOptionalParameter)
				.map(Rule15Antipattern::getInstance)
				.filter(Optional::isPresent)
				.map(Optional::get)
				.map(Factory.getInstance()::createMRule15Atom)
				.collect(Collectors.toList());
	}
}
