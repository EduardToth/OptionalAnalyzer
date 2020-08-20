package compilationUnits.groups;

import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.Assignment;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.VariableDeclarationFragment;

import optionalanalizer.metamodel.entity.MAssignment;
import optionalanalizer.metamodel.entity.MCompilationUnit;
import optionalanalizer.metamodel.factory.Factory;
import ro.lrg.xcore.metametamodel.Group;
import ro.lrg.xcore.metametamodel.IRelationBuilder;
import ro.lrg.xcore.metametamodel.RelationBuilder;
import utilities.UtilityClass;

@RelationBuilder
public class Rule1AntipatternBuilder implements IRelationBuilder<MAssignment, MCompilationUnit>{

	@Override
	public Group<MAssignment> buildGroup(MCompilationUnit arg0) {
		ICompilationUnit iCompilationUnit = (ICompilationUnit)arg0.getUnderlyingObject();
		Group<MAssignment> group = new Group<>();

		CompilationUnit compilationUnit = UtilityClass.parse(iCompilationUnit);
		compilationUnit.accept(new ASTVisitor() {

			@Override
			public boolean visit(Assignment assignment) {

				if(isAssignmentAnOptionalAssignedToNull(assignment)) {
					MAssignment mAssignment = Factory.getInstance().createMAssignment( assignment );
					group.add(mAssignment);
				}
				return super.visit( assignment );
			}

			@Override
			public boolean visit(VariableDeclarationFragment declaration) {


				String typeName = declaration.resolveBinding().getType().getQualifiedName();
				if(declaration.getInitializer() != null) {

					if(UtilityClass.isInvocatorOfOptionalType(typeName)
							&& declaration.getInitializer().toString().equals("null")) {
						group.add(Factory.getInstance().createMAssignment(declaration));
					}
				}
				return super.visit(declaration);
			}
		});
		return group;
	}

	private boolean isAssignmentAnOptionalAssignedToNull(Assignment assignment) {
		if(assignment.getLeftHandSide().
				resolveTypeBinding() == null) {
			return false;
		}
		Object value =  assignment.getRightHandSide().resolveConstantExpressionValue();

		return UtilityClass.isInvocatorOfOptionalType(assignment.getLeftHandSide().
				resolveTypeBinding().getQualifiedName())
				&& value == null;
	}
}


