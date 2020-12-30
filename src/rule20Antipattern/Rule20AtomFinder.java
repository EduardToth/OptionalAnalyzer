package rule20Antipattern;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.VariableDeclarationFragment;

import optionalanalizer.metamodel.entity.MRule20Atom;
import optionalanalizer.metamodel.factory.Factory;
import utilities.Unit;
import utilities.UtilityClass;

public class Rule20AtomFinder {
	public List<MRule20Atom> getMAtoms(ASTNode astNode) {

		final List<VariableDeclarationFragment> variableDeclarations = getVariableDeclarations(astNode);

		final Unit<String> typeName = new Unit<>(null);
		return variableDeclarations.stream()
				.peek(decl -> setTypeName(typeName, decl))
				.filter(decl -> UtilityClass.isSimpleOptionalType(typeName.getValue0()))
				.filter(decl -> UtilityClass.hasBadGenericTypeArgumentForOptional(typeName.getValue0()))
				.map(Rule20Atom::getInstance)
				.filter(Optional::isPresent)
				.map(Optional::get)
				.map(Factory.getInstance()::createMRule20Atom)
				.collect(Collectors.toList());
	}

	private void setTypeName(final Unit<String> typeName, VariableDeclarationFragment variableDeclarationFragment) {
		
		String typeName2 = "";
		
		try {
			typeName2 = variableDeclarationFragment.resolveBinding().getType().getQualifiedName();
		}catch(NullPointerException npe) {
			
		}
		typeName.setAt0(typeName2);
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
