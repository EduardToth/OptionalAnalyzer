package compilationUnits.groups;

import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.Assignment;
import org.eclipse.jdt.core.dom.CompilationUnit;

import optionalanalizer.metamodel.entity.MAssignment;
import optionalanalizer.metamodel.entity.MCompilationUnit;
import optionalanalizer.metamodel.factory.Factory;
import ro.lrg.xcore.metametamodel.Group;
import ro.lrg.xcore.metametamodel.IRelationBuilder;
import ro.lrg.xcore.metametamodel.RelationBuilder;
import utilities.UtilityClass;

@RelationBuilder
public class OptionalAssignmentToNullGroup implements IRelationBuilder<MAssignment, MCompilationUnit>{

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


