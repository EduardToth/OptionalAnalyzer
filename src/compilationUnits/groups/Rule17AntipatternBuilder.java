package compilationUnits.groups;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.Expression;
import org.eclipse.jdt.core.dom.ITypeBinding;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.ReturnStatement;

import optionalanalizer.metamodel.entity.MCompilationUnit;
import optionalanalizer.metamodel.entity.MMethod;
import optionalanalizer.metamodel.entity.MReturnStatement;
import optionalanalizer.metamodel.factory.Factory;
import ro.lrg.xcore.metametamodel.Group;
import ro.lrg.xcore.metametamodel.IRelationBuilder;
import ro.lrg.xcore.metametamodel.RelationBuilder;
import utilities.Unit;
import utilities.UtilityClass;

@RelationBuilder
public class Rule17AntipatternBuilder  implements IRelationBuilder<MMethod, MCompilationUnit>{

	@Override
	public Group<MMethod> buildGroup(MCompilationUnit arg0) {
		ICompilationUnit iCompilationUnit = (ICompilationUnit)arg0.getUnderlyingObject();
		CompilationUnit compilationUnit = UtilityClass.parse(iCompilationUnit);

		ArrayList<MethodDeclaration> methodDeclarations = getReturnStatements(compilationUnit);
		Group<MMethod> group = new Group<>();

		
		final Unit<MethodDeclaration> currentMethodDeclaration = new Unit<>(null);
		methodDeclarations.stream()
		.peek(decl -> currentMethodDeclaration.setAt0( decl ))
		.filter(decl -> !decl.isConstructor())
		.map(decl -> decl.resolveBinding().getReturnType().getQualifiedName())
		.filter(this::isExpressionOfTypeOptionalContainingArrayOrCollection)
		.map(decl -> Factory.getInstance().createMMethod(currentMethodDeclaration.getValue0()))
		.forEach(mMethod -> group.add(mMethod));

		return group;
	}

	private final ArrayList<MethodDeclaration> getReturnStatements(CompilationUnit compilationUnit) {

		final ArrayList<MethodDeclaration> methodDeclarations = new ArrayList<>();
		compilationUnit.accept(new ASTVisitor() {
			@Override
			public boolean visit(MethodDeclaration methodDeclaration) {
				methodDeclarations.add(methodDeclaration);
				return super.visit(methodDeclaration);
			}
		});
		return methodDeclarations;
	}

	private boolean isExpressionOfTypeOptionalContainingArrayOrCollection(String typeName) {
		return UtilityClass.isTypeOptional(typeName) && 
				(isArrayTypeInside(typeName) || isCollectionTypeInside(typeName));
	}

	private boolean isCollectionTypeInside(String typeName) {
		String[] chosenTypes = getChosenTypes(typeName);

		return Arrays.asList(chosenTypes)
				.stream()
				.filter(UtilityClass::isCollectionType)
				.findAny()
				.isPresent();
	}

	private boolean isArrayTypeInside(String typeName) {
		return typeName.indexOf("[]") != -1;
	}

	private String[] getChosenTypes(String typeName) {
		int myIndex = -1;

		for(int i=0; i < typeName.length(); i++) {
			if(typeName.charAt( i ) == '<') {
				myIndex = i;
				break;
			}
		}

		if(myIndex == -1) {
			return new String[0];
		}

		String chosenTypesAsString = typeName.substring(myIndex + 1, typeName.length() - 1);
		return chosenTypesAsString.split(", *");
	}
}