package rule15Antipattern;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.MethodDeclaration;

import optionalanalizer.metamodel.entity.MRule15sAntipattern;
import optionalanalizer.metamodel.factory.Factory;
import utilities.UtilityClass;

public class Rule15AntipatternFinder {

	public List<MRule15sAntipattern> getMAntipatterns(ASTNode astNode) {
		List<MethodDeclaration> methodDeclarations = UtilityClass.getMethodDeclarations(astNode);
        
		return methodDeclarations.stream()
				.filter(UtilityClass::isSetter)
				.map(UtilityClass::getFirstOptionalParameter)
				.flatMap(Optional::stream)
				.map(Rule15Antipattern::getInstance)
				.flatMap(Optional::stream)
				.map(Factory.getInstance()::createMRule15sAntipattern)
				.collect(Collectors.toList());
	}
}
