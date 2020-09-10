package compilationUnits.groups;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.MethodInvocation;
import org.eclipse.jdt.core.dom.PrefixExpression;

import optionalanalizer.metamodel.entity.MCompilationUnit;
import optionalanalizer.metamodel.entity.MInvocation;
import optionalanalizer.metamodel.entity.MPrefixExpression;
import optionalanalizer.metamodel.factory.Factory;
import ro.lrg.xcore.metametamodel.Group;
import ro.lrg.xcore.metametamodel.IRelationBuilder;
import ro.lrg.xcore.metametamodel.RelationBuilder;

@RelationBuilder
public class Rule12AntipatternBuilder implements IRelationBuilder<MInvocation, MCompilationUnit>{


	private final String matchingRegEx = ".*\\.ofNullable\\(.*\\)\\.(orElse\\(.*\\)|orElseGet\\(.*\\))";
	@Override
	public Group<MInvocation> buildGroup(MCompilationUnit arg0) {
		OptionalInvocationsBuilder optionalInvocationsGroup = new OptionalInvocationsBuilder();
		Group<MInvocation> ofNullableGroup = optionalInvocationsGroup.buildGroup(arg0, "ofNullable");
		List<MInvocation> antipatternList = ofNullableGroup.getElements().stream()
				.filter(mInv -> mInv.getUnderlyingObject() instanceof MethodInvocation)
				.map(mInv -> (MethodInvocation)mInv.getUnderlyingObject())
				.filter(this::respectsThePattern)
				.map(methodInvocation -> methodInvocation.getParent())
				.filter(el -> el instanceof MethodInvocation)
				.map(methodInvocation -> Factory.getInstance().createMInvocation(methodInvocation))
				.collect(Collectors.toList());
		Group<MInvocation> group = new Group<>();
		group.addAll(antipatternList);
		return group;
	}

	private boolean respectsThePattern(MethodInvocation methodInvocation) {
		return methodInvocation.getParent().toString().matches(matchingRegEx);
	}

}