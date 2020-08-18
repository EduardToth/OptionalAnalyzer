package compilationUnits.groups;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.Expression;
import org.eclipse.jdt.core.dom.IfStatement;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.MethodInvocation;

import optionalanalizer.metamodel.entity.MCompilationUnit;
import optionalanalizer.metamodel.entity.MIfStatement;
import optionalanalizer.metamodel.entity.MInvocation;
import ro.lrg.xcore.metametamodel.Group;
import ro.lrg.xcore.metametamodel.IRelationBuilder;
import ro.lrg.xcore.metametamodel.RelationBuilder;
import utilities.UtilityClass;

@RelationBuilder
public class Rule2AntipatternGroup  implements IRelationBuilder<MInvocation, MCompilationUnit>{

	@Override
	public Group<MInvocation> buildGroup(MCompilationUnit arg0) {
		OptionalInvocationsGroup optionalInvocationsGroup = new OptionalInvocationsGroup();
		Group<MInvocation> group = optionalInvocationsGroup.buildGroup(arg0, "get");
		Group<MInvocation> mInvocationGroup = new Group<MInvocation>();

		group.getElements()
		.parallelStream()
		.filter(this::isTheSecondRulesAntipattern)
		.forEach(mInvocation -> mInvocationGroup.add(mInvocation));
		return mInvocationGroup;
	}

	private boolean isTheSecondRulesAntipattern(MInvocation mInvocation) {

		MethodInvocation methodInvocation = (MethodInvocation) mInvocation.getUnderlyingObject();
		String invocatorName = UtilityClass
				.getInvocatorName(methodInvocation)
				.orElse(null);
		return verifyInParents(methodInvocation, invocatorName);
	}

	private boolean verifyInParents(MethodInvocation methodInvocation, String invocatorName) {
		ASTNode astNode = methodInvocation.getParent();
		for(; !(astNode instanceof MethodDeclaration);) {

			if(astNode instanceof IfStatement 
					&& containsIsPresentInvocationForTheVariable(((IfStatement) astNode).getExpression(), invocatorName)) {
				return false;
			}
			astNode = astNode.getParent();
		}
		return true;
	}

	private boolean containsIsPresentInvocationForTheVariable(Expression expression, String invocatorName) {
		return expression.toString().indexOf(invocatorName + ".isPresent()") != -1;
	}

}
