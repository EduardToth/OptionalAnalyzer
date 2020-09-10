package compilationUnits.groups.rule14_15_16AntipatternBuilders;

import java.util.LinkedList;
import java.util.List;

import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.SingleVariableDeclaration;

import optionalanalizer.metamodel.entity.MCompilationUnit;
import optionalanalizer.metamodel.entity.MVariableDeclaration;
import optionalanalizer.metamodel.factory.Factory;
import ro.lrg.xcore.metametamodel.Group;
import ro.lrg.xcore.metametamodel.IRelationBuilder;
import ro.lrg.xcore.metametamodel.RelationBuilder;

@RelationBuilder
public class Rule16AntipatternBuilder  implements IRelationBuilder<MVariableDeclaration, MCompilationUnit>{

	@Override
	public Group<MVariableDeclaration> buildGroup(MCompilationUnit arg0) {

		ICompilationUnit iCompilationUnit = (ICompilationUnit) arg0.getUnderlyingObject();
		UtilityClass utilityClass = new UtilityClass();
		List<MethodDeclaration> methodDeclarations = utilityClass.getMethodDeclarations(iCompilationUnit);
		Group<MVariableDeclaration> group = new Group<>();

		methodDeclarations.stream()
		.filter(decl -> !decl.isConstructor())
		.filter(decl -> !utilityClass.isSetter( decl ))
		.filter(decl -> utilityClass.containsAtLeastOneOptionalAsParameter( decl ))
		.map(decl -> utilityClass.getFirstOptionalParameter( decl ))
		.map(elem -> Factory.getInstance().createMVariableDeclaration(elem))
		.forEach(elem -> group.add( elem ) );

		return group;
	}
}