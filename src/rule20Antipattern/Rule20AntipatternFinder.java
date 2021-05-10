package rule20Antipattern;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.VariableDeclarationFragment;

import optionalanalyzer.metamodel.entity.MRule20sAntipattern;
import optionalanalyzer.metamodel.factory.Factory;
import utilities.UtilityClass;

public class Rule20AntipatternFinder {
	
	public List<MRule20sAntipattern> getMAntipatterns(ASTNode astNode) {

		final List<VariableDeclarationFragment> variableDeclarations = getVariableDeclarations(astNode);

		return variableDeclarations.stream()
				.map(this::convertIntoAntipatternIfPossible)
				.flatMap(Optional::stream)
				.map(Factory.getInstance()::createMRule20sAntipattern)
				.collect(Collectors.toList());
	}

	private Optional<Rule20Antipattern> convertIntoAntipatternIfPossible(VariableDeclarationFragment variableDeclarationFragment) {
		return getTypeName(variableDeclarationFragment)
				.filter(UtilityClass::isSimpleOptionalType)
				.filter(UtilityClass::hasBadGenericTypeArgumentForOptional) //like Integer, Double...
				.flatMap(ignored -> Rule20Antipattern.getInstance(variableDeclarationFragment));
	}

	private Optional<String> getTypeName(VariableDeclarationFragment variableDeclarationFragment) {
		try {
			String typeName = variableDeclarationFragment
					.resolveBinding()
					.getType()
					.getQualifiedName();

			return Optional.of(typeName);
		} catch(NullPointerException ignored) {}

		return Optional.empty();
	}

	private List<VariableDeclarationFragment> getVariableDeclarations(ASTNode astNode) {

		final List<VariableDeclarationFragment> variableDeclarations = new ArrayList<>();

		astNode.accept(new ASTVisitor() {

			@Override
			public boolean visit(VariableDeclarationFragment variableDeclarationFragment) {
				variableDeclarations.add(variableDeclarationFragment);
				return super.visit(variableDeclarationFragment);
			}
		});

		return variableDeclarations;
	}
}
