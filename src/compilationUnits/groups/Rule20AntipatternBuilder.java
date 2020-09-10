package compilationUnits.groups;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.VariableDeclarationFragment;

import optionalanalizer.metamodel.entity.MCompilationUnit;
import optionalanalizer.metamodel.entity.MVariableDeclaration;
import optionalanalizer.metamodel.factory.Factory;
import ro.lrg.xcore.metametamodel.Group;
import ro.lrg.xcore.metametamodel.IRelationBuilder;
import ro.lrg.xcore.metametamodel.RelationBuilder;
import utilities.Unit;
import utilities.UtilityClass;

@RelationBuilder
public class Rule20AntipatternBuilder  implements IRelationBuilder<MVariableDeclaration, MCompilationUnit>{

	@Override
	public Group<MVariableDeclaration> buildGroup(MCompilationUnit arg0) {

		ICompilationUnit iCompilationUnit = (ICompilationUnit) arg0.getUnderlyingObject();
		CompilationUnit compilationUnit = UtilityClass.parse(iCompilationUnit);
		final List<VariableDeclarationFragment> variableDeclarations = getVariableDeclarations(compilationUnit);
		Group<MVariableDeclaration> group = new Group<>();

		final Unit<String> typeName = new Unit<>(null);
		List<MVariableDeclaration> optionalDeclarationsWithBadGenericType = variableDeclarations
				.stream()
				.peek(decl -> setTypeName(typeName, decl))
				.filter(decl -> UtilityClass.isSimpleOptionalType(typeName.getValue0()))
				.filter(decl -> UtilityClass.hasBadGenericTypeArgumentForOptional(typeName.getValue0()))
				.map(decl -> Factory.getInstance().createMVariableDeclaration(decl))
				.collect(Collectors.toList());
		group.addAll(this.removeDuplicates(optionalDeclarationsWithBadGenericType));
		return group;
	}

	private void setTypeName(final Unit<String> typeName, VariableDeclarationFragment variableDeclarationFragment) {
		typeName.setAt0(variableDeclarationFragment.resolveBinding().getType().getQualifiedName());
	}

	private List<VariableDeclarationFragment> getVariableDeclarations(CompilationUnit compilationUnit) {

		final List<VariableDeclarationFragment> variableDeclarations = new ArrayList<>();
		compilationUnit.accept(new ASTVisitor() {

			@Override
			public boolean visit(VariableDeclarationFragment variableDeclarationFragment) {

				variableDeclarations.add(variableDeclarationFragment);
				return super.visit(variableDeclarationFragment);
			}
		});

		return variableDeclarations;
	}

	private List<MVariableDeclaration> removeDuplicates(List<MVariableDeclaration> mVariableDeclarations) {

		Map<Integer, MVariableDeclaration> myMap = new HashMap<>();

		for(MVariableDeclaration var : mVariableDeclarations) {
			VariableDeclarationFragment variableDeclarationFragment = (VariableDeclarationFragment)var.getUnderlyingObject();
			if(!myMap.containsKey(variableDeclarationFragment.getParent().getStartPosition())) {
				myMap.put(variableDeclarationFragment.getParent().getStartPosition(), var);
			}
		}


		return new LinkedList<MVariableDeclaration>( myMap.values());
	}
}