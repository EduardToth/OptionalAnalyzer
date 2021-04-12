package rule18Antipattern;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.SimpleName;
import optionalanalyzer.metamodel.entity.MRule18sAntipattern;
import optionalanalyzer.metamodel.factory.Factory;
import utilities.UtilityClass;

public class Rule18AntipatternFinder {

	public List<MRule18sAntipattern> getMAntipatterns(ASTNode astNode) {

		List<SimpleName> simpleNames = getSimpleNames(astNode);

		List<Rule18Antipattern> antipatterns = simpleNames.stream()
				.map(this::convertIntoAntipatternIfPossible)
				.flatMap(Optional::stream)
				.collect(Collectors.toList());

		antipatterns = removeDuplicates(antipatterns);

		return antipatterns.stream()
				.map(Factory.getInstance()::createMRule18sAntipattern)
				.collect(Collectors.toList());
	}

	private Optional<Rule18Antipattern> convertIntoAntipatternIfPossible(SimpleName simpleName) {
		String typeName = getTypeName(simpleName);
		if(isRule18Antipattern(typeName)) {
			return Rule18Antipattern.getInstance(simpleName);
		}

		return Optional.empty();
	}

	private String getTypeName(SimpleName simpleName) {
		String typeName = "";
		try {
			typeName = simpleName.resolveTypeBinding().getQualifiedName();
		}catch(NullPointerException npe) {}

		return typeName;
	}

	private List<SimpleName> getSimpleNames(ASTNode astNode) {

		final List<SimpleName> simpleNames = new ArrayList<>();

		astNode.accept(new ASTVisitor() {

			@Override
			public boolean visit(SimpleName simpleName) {
				if(simpleName.isDeclaration()) {
					simpleNames.add(simpleName);
				}
				return super.visit(simpleName);
			}
		});

		return simpleNames;
	}

	private boolean isRule18Antipattern(String typeName) {
		if(UtilityClass.isCollectionType(typeName) && UtilityClass.hasOptionalTypeInside(typeName)) {
			return true;
		}

		String[] genericTypes = UtilityClass.getGenericTypes(typeName);

		boolean containsOptional = Arrays.asList(genericTypes)
				.stream()
				.anyMatch(this::isRule18Antipattern);

		return containsOptional;
	}

	private List<Rule18Antipattern> removeDuplicates(List<Rule18Antipattern> antipatterns) {

		Set<Integer> startPositions = new HashSet<>();
		List<Rule18Antipattern> newList = new ArrayList<>();
		for(Rule18Antipattern rule21Antipattern : antipatterns) {
			if(!startPositions.contains(rule21Antipattern.getStartingPosition())) {
				newList.add(rule21Antipattern);
				startPositions.add(rule21Antipattern.getStartingPosition());
			}

		}
		return newList;
	}
}
