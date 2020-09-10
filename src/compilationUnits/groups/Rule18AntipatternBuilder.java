package compilationUnits.groups;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.SimpleName;
import org.eclipse.jdt.core.dom.StructuralPropertyDescriptor;
import org.eclipse.jdt.core.dom.TypeDeclarationStatement;
import org.eclipse.jdt.core.dom.TypeLiteral;
import org.eclipse.jdt.core.dom.TypeParameter;
import org.eclipse.jdt.core.dom.VariableDeclarationFragment;

import optionalanalizer.metamodel.entity.MCompilationUnit;
import optionalanalizer.metamodel.entity.MVariableDeclaration;
import optionalanalizer.metamodel.factory.Factory;
import ro.lrg.xcore.metametamodel.Group;
import ro.lrg.xcore.metametamodel.IRelationBuilder;
import ro.lrg.xcore.metametamodel.RelationBuilder;
import utilities.Unit;
import utilities.UtilityClass;
import org.eclipse.jdt.core.dom.Type;


@RelationBuilder
public class Rule18AntipatternBuilder  implements IRelationBuilder<MVariableDeclaration, MCompilationUnit>{

	@Override
	public Group<MVariableDeclaration> buildGroup(MCompilationUnit arg0) {
		ICompilationUnit iCompilationUnit = (ICompilationUnit)arg0.getUnderlyingObject();
		CompilationUnit compilationUnit = UtilityClass.parse(iCompilationUnit);
		Group<MVariableDeclaration> group = new Group<>();

		List<SimpleName> simpleNames = getVariableDeclarationFragments(compilationUnit);

		final Unit<String> typeName = new Unit<>(null);
		List<MVariableDeclaration> declarations = simpleNames.stream()
				.peek(simpleName -> typeName.setAt0(simpleName.resolveTypeBinding().getQualifiedName()))
				.filter(simpleName -> UtilityClass.isCollectionType(typeName.getValue0()))
				.filter(simpleName -> UtilityClass.hasOptionalTypeInside(typeName.getValue0()))
				.map(simpleName -> Factory.getInstance().createMVariableDeclaration(simpleName))
				.collect(Collectors.toList());

		group.addAll(removeDuplicates(declarations));

		return group;
	}


	private List<SimpleName> getVariableDeclarationFragments(CompilationUnit compilationUnit) {

		final List<SimpleName> simpleNames = new ArrayList<>();

		compilationUnit.accept(new ASTVisitor() {

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

	private List<MVariableDeclaration> removeDuplicates(List<MVariableDeclaration> mVariableDeclarations) {

		Map<Integer, MVariableDeclaration> myMap = new HashMap<>();

		for(MVariableDeclaration var : mVariableDeclarations) {
			ASTNode astNode = null;
			SimpleName simpleName = (SimpleName)var.getUnderlyingObject();
			if(simpleName.getParent() instanceof VariableDeclarationFragment) {
				astNode = simpleName.getParent().getParent();
			} else {
				astNode = simpleName.getParent();
			}
			if(!myMap.containsKey(astNode.getStartPosition())) {
				myMap.put(astNode.getStartPosition(), var);
			}
		}

		return new LinkedList<MVariableDeclaration>( myMap.values());
	}
}