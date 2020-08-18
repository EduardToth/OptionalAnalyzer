package compilationUnits.groups;

import java.util.Optional;

import org.eclipse.jdt.core.dom.IfStatement;
import org.eclipse.jdt.core.dom.MethodInvocation;

import optionalanalizer.metamodel.entity.MCompilationUnit;
import optionalanalizer.metamodel.entity.MIfStatement;
import optionalanalizer.metamodel.entity.MInvocation;
import optionalanalizer.metamodel.factory.Factory;
import ro.lrg.xcore.metametamodel.Group;
import ro.lrg.xcore.metametamodel.IRelationBuilder;
import ro.lrg.xcore.metametamodel.RelationBuilder;
import utilities.Antipattern;
import utilities.AntipatternFinderInIfStatements;
import utilities.Unit;
import utilities.UtilityClass;

@RelationBuilder
public class Rule4AntipatternGroup implements IRelationBuilder<MIfStatement, MCompilationUnit>{

	@Override
	public Group<MIfStatement> buildGroup(MCompilationUnit arg0) {
		OptionalInvocationsGroup optionalInvocationsGroup = 
				new OptionalInvocationsGroup();
		Group<MInvocation> isPresentInvocationGroup = optionalInvocationsGroup.buildGroup(arg0);

		return collectRule3Antipatterns(isPresentInvocationGroup);
	}

	private Group<MIfStatement> collectRule3Antipatterns(Group<MInvocation> isPresentGroup) {

		Unit<Optional<String>> invocatorName = new Unit<>(Optional.empty());
		Group<MIfStatement> mIfStatementGroup = new Group<>();

		isPresentGroup.getElements()
		.stream()
		.map(this::convertToMethodInvocation)
		.peek(inv -> setInvocatorName(inv, invocatorName))
		.filter(this::isParentIfStatement)
		.map(inv -> (IfStatement)inv.getParent())
		.filter(ifStatement -> isRule4sAntipattern(ifStatement, invocatorName.getValue0()))
		.map(this::convertToMIfStatement)
		.forEach(mIfStatement -> mIfStatementGroup.add(mIfStatement));

		return mIfStatementGroup;
	}

	private boolean isParentIfStatement(MethodInvocation methodInvocation) {
		return methodInvocation.getParent() instanceof IfStatement;
	}

	private MIfStatement convertToMIfStatement(IfStatement ifStatement) {
		return Factory.getInstance().createMIfStatement(ifStatement);
	}

	private void setInvocatorName(MethodInvocation invocation, Unit<Optional<String>> invocatorName) {
		invocatorName.setAt0( UtilityClass.getInvocatorName(invocation));
		//System.out.println("------------> " +  UtilityClass.getInvocatorName(invocation));
	}

	private MethodInvocation convertToMethodInvocation(MInvocation mInvocation) {
		return (MethodInvocation)mInvocation.getUnderlyingObject();
	}


	private boolean isRule4sAntipattern(IfStatement ifStatement, Optional<String> invocatorName) {
		AntipatternFinderInIfStatements finder = new AntipatternFinderInIfStatements();
		if(invocatorName.isPresent()) {
			Antipattern foundAntipattern = finder.lookForAntipatternInIfStatement(ifStatement, invocatorName.get());
			return foundAntipattern  == Antipattern.RULE4_ANTIPATTERN;
		} else {
			return false;
		}
	}

}
