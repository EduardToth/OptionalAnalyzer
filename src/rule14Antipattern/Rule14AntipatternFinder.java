package rule14Antipattern;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.MethodDeclaration;

import optionalanalizer.metamodel.entity.MRule14sAntipattern;
import optionalanalizer.metamodel.factory.Factory;
import utilities.UtilityClass;

public class Rule14AntipatternFinder {

	public List<MRule14sAntipattern> getMAntipatterns(ASTNode astNode) {
		List<MethodDeclaration> methodDeclarations = UtilityClass.getMethodDeclarations(astNode);

		return methodDeclarations.stream()
				.filter(MethodDeclaration::isConstructor)
				.map(UtilityClass::getFirstOptionalParameter)
				.flatMap(Optional::stream)
				.map(Rule14Antipattern::getInstance)
				.flatMap(Optional::stream)
				.map(Factory.getInstance()::createMRule14sAntipattern)
				.collect(Collectors.toList());
	}
}
