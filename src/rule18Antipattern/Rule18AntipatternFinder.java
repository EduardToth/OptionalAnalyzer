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
import optionalanalizer.metamodel.entity.MRule18sAntipattern;
import optionalanalizer.metamodel.factory.Factory;
import utilities.Unit;
import utilities.UtilityClass;

public class Rule18AntipatternFinder {

	public List<MRule18sAntipattern> getMAntipatterns(ASTNode astNode) {

		List<SimpleName> simpleNames = getSimpleNames(astNode);

		final Unit<String> typeName = new Unit<>(null);

		List<Rule18Antipattern> antipatterns = simpleNames.stream()
		.peek(simpleName -> typeName.setAt0(getTypeName(simpleName)))
		.filter(el -> isRule18Antipattern(typeName.getValue0()))
		.map(Rule18Antipattern::getInstance)
		.filter(Optional::isPresent)
		.map(Optional::get)
		.collect(Collectors.toList());
		
		antipatterns = removeDuplicates(antipatterns);
		
		return antipatterns.stream()
				.map(Factory.getInstance()::createMRule18sAntipattern)
				.collect(Collectors.toList());
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

		boolean containsOptional = Arrays.asList(genericTypes).stream()
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
