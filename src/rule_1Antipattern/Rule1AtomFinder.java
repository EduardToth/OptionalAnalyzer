package rule_1Antipattern;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.Assignment;
import org.eclipse.jdt.core.dom.VariableDeclarationFragment;

import optionalanalizer.metamodel.entity.MRule1Atom;
import optionalanalizer.metamodel.factory.Factory;

import utilities.UtilityClass;

public class Rule1AtomFinder{

	public List<MRule1Atom> getMAtoms(ASTNode astNode) {

		return getAtoms(astNode).stream()
				.map(Factory.getInstance()::createMRule1Atom)
				.collect(Collectors.toList());
	}

	private List<Rule1Atom> getAtoms(ASTNode astNode) {
		List<Rule1Atom> antipatterns = new LinkedList<>();

		astNode.accept(new ASTVisitor() {

			@Override
			public boolean visit(Assignment assignment) {

				if(isAssignmentAnOptionalAssignedToNull(assignment)) {			
					Rule1Atom.getInstance(assignment)
					.ifPresent(atom -> antipatterns.add(atom));
				}
				return super.visit( assignment );
			}

			@Override
			public boolean visit(VariableDeclarationFragment declaration) {
				String typeName = "";
				try {
					typeName = declaration.resolveBinding().getType().getQualifiedName();
				} catch(NullPointerException npe) {}
				if(declaration.getInitializer() != null) {

					if(UtilityClass.isTypeOptional(typeName)
							&& declaration.getInitializer().toString().equals("null")) {
						Rule1Atom.getInstance(declaration)
						.ifPresent(atom -> antipatterns.add(atom));
					}
				}
				return super.visit(declaration);
			}
		});
		return antipatterns;
	}

	private boolean isAssignmentAnOptionalAssignedToNull(Assignment assignment) {
		if(assignment.getLeftHandSide().
				resolveTypeBinding() == null) {
			return false;
		}
		Object value =  assignment.getRightHandSide().resolveConstantExpressionValue();

		String typeName = "";
		try {
			typeName = assignment.getLeftHandSide().
					resolveTypeBinding().getQualifiedName();
		} catch(NullPointerException npe) {}
		return UtilityClass.isTypeOptional(typeName)
				&& value == null;
	}

}
