package rule_1Antipattern;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.Assignment;
import org.eclipse.jdt.core.dom.VariableDeclarationFragment;

import optionalanalyzer.metamodel.entity.MRule1sAntipattern;
import optionalanalyzer.metamodel.factory.Factory;

import utilities.UtilityClass;

public class Rule1AntipatternFinder{

	public List<MRule1sAntipattern> getMAntipatterns(ASTNode astNode) {
		
		return getAntipatterns(astNode).stream()
				.map(Factory.getInstance()::createMRule1sAntipattern)
				.collect(Collectors.toList());
	}

	private List<Rule1Antipattern> getAntipatterns(ASTNode astNode) {
		List<Rule1Antipattern> antipatterns = new LinkedList<>();

		astNode.accept(new ASTVisitor() {

			@Override
			public boolean visit(Assignment assignment) {

				if(isAssignmentAnOptionalAssignedToNull(assignment)) {			
					Rule1Antipattern.getInstance(assignment).ifPresent(antipattern -> antipatterns.add(antipattern));
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
						Rule1Antipattern.getInstance(declaration)
						.ifPresent(antipattern -> antipatterns.add(antipattern));
					}
				}
				return super.visit(declaration);
			}
		});
		return antipatterns;
	}

	private boolean isAssignmentAnOptionalAssignedToNull(Assignment assignment) {
		if(assignment.getLeftHandSide().resolveTypeBinding() == null) {
			return false;
		}

		String typeName = "";
		try {
			typeName = assignment.getLeftHandSide().
					resolveTypeBinding().getQualifiedName();
		} catch(NullPointerException npe) {}
		return UtilityClass.isTypeOptional(typeName)
				&& assignment.toString().matches(".*= *null *;?");
	}
}
