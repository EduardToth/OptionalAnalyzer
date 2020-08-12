package compilationUnits.groups;

import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.VariableDeclarationFragment;

import optionalanalizer.metamodel.entity.MCompilationUnit;
import optionalanalizer.metamodel.entity.MVariableDeclarationFragment;
import optionalanalizer.metamodel.factory.Factory;
import ro.lrg.xcore.metametamodel.Group;
import ro.lrg.xcore.metametamodel.IRelationBuilder;
import ro.lrg.xcore.metametamodel.RelationBuilder;
import utilities.UtilityClass;

@RelationBuilder
public class OptionalInitializedToNullGroup implements IRelationBuilder<MVariableDeclarationFragment, MCompilationUnit>{

	@Override
	public Group<MVariableDeclarationFragment> buildGroup(MCompilationUnit arg0) {
		ICompilationUnit iCompilationUnit = (ICompilationUnit)arg0.getUnderlyingObject();
		Group<MVariableDeclarationFragment> group = new Group<>();
		CompilationUnit compilationUnit = UtilityClass.parse(iCompilationUnit);
		compilationUnit.accept(new ASTVisitor() {

			@Override
			public boolean visit(VariableDeclarationFragment declaration) {


				String typeName = declaration.resolveBinding().getType().getQualifiedName();
				if(declaration.getInitializer() != null) {

					if(UtilityClass.isInvocatorOfOptionalType(typeName)
							&& declaration.getInitializer().toString().equals("null")) {
						group.add(Factory.getInstance().createMVariableDeclarationFragment(declaration));
					}
				}
				return super.visit(declaration);
			}
		});
		return group;
	}

}
