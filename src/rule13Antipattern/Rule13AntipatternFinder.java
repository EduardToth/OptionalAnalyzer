package rule13Antipattern;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.FieldDeclaration;

import optionalanalizer.metamodel.entity.MRule13Atom;
import optionalanalizer.metamodel.factory.Factory;
import utilities.UtilityClass;

public class Rule13AntipatternFinder {

	public List<MRule13Atom> getMAtoms(ASTNode astNode) {
		return getFieldDeclarations(astNode)
				.stream()
				.filter(this::isFieldDeclarationOfTypeOptional)
				.map(Rule13Antipattern::getInstance)
				.filter(Optional::isPresent)
				.map(Optional::get)
				.map(Factory.getInstance()::createMRule13Atom)
				.collect(Collectors.toList());
	}

	private List<FieldDeclaration> getFieldDeclarations(ASTNode astNode) {
		List<FieldDeclaration> fieldDeclarations = new LinkedList<>();
		astNode.accept(new ASTVisitor() {

			@Override
			public boolean visit(FieldDeclaration fieldDeclaration) {
				fieldDeclarations.add(fieldDeclaration);
				return super.visit(fieldDeclaration);
			}
		});

		return fieldDeclarations;
	}

	private boolean isFieldDeclarationOfTypeOptional(FieldDeclaration fieldDeclaration) {

		String typeName = "";

		try {
			typeName = fieldDeclaration.getType()
					.resolveBinding()
					.getQualifiedName();
		} catch(NullPointerException npe) {
			npe.printStackTrace();
		}
		
		return UtilityClass.isTypeOptional(typeName);
	}

}