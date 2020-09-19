package rule18Antipattern;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.SimpleName;

import optionalanalizer.metamodel.entity.MRule18Atom;
import optionalanalizer.metamodel.factory.Factory;
import utilities.Unit;
import utilities.UtilityClass;

public class Rule18AtomFinder {
	
	public List<MRule18Atom> getMAtoms(ASTNode astNode) {

		List<SimpleName> simpleNames = getSimpleNames(astNode);

		final Unit<String> typeName = new Unit<>(null);
		return simpleNames.stream()
				.peek(simpleName -> typeName.setAt0(simpleName.resolveTypeBinding().getQualifiedName()))
				.filter(simpleName -> UtilityClass.isCollectionType(typeName.getValue0()))
				.filter(simpleName -> UtilityClass.hasOptionalTypeInside(typeName.getValue0()))
				.map(Rule18Atom::getInstance)
				.filter(Optional::isPresent)
				.map(Optional::get)
				.distinct()
				.map(atom -> Factory.getInstance().createMRule18Atom(atom))
				.collect(Collectors.toList());
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

}