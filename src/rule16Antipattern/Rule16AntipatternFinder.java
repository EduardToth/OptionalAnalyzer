package rule16Antipattern;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.MethodDeclaration;

import optionalanalizer.metamodel.entity.MRule16sAntipattern;
import optionalanalizer.metamodel.factory.Factory;
import utilities.UtilityClass;

public class Rule16AntipatternFinder {

	public List<MRule16sAntipattern> getMAntipatterns(ASTNode astNode) {
		List<MethodDeclaration> methodDeclarations = UtilityClass.getMethodDeclarations(astNode);
        
		return methodDeclarations.stream()
				.filter(Predicate.not(MethodDeclaration::isConstructor))
				.filter(Predicate.not(UtilityClass::isSetter))
				.map(UtilityClass::getFirstOptionalParameter)
				.flatMap(Optional::stream)
				.map(Rule16Antipattern::getInstance)
				.flatMap(Optional::stream)
				.map(Factory.getInstance()::createMRule16sAntipattern)
				.collect(Collectors.toList());
	}
}
