package optionalanalizer.metamodel.impl;

import optionalanalizer.metamodel.entity.*;
import compilationUnits.properties.ToString;
import compilationUnits.groups.Rule17AntipatternBuilder;
import compilationUnits.groups.Rule20AntipatternBuilder;
import compilationUnits.groups.Rule3_4_5_6_8_9AntipatternBuilders.Rule5AntipatternBuilder;
import compilationUnits.groups.Rule26AntipatternBuilder;
import compilationUnits.groups.rule14_15_16AntipatternBuilders.Rule16AntipatternBuilder;
import compilationUnits.groups.Rule18AntipatternBuilder;
import compilationUnits.groups.Rule2AntipatternBuilder;
import compilationUnits.groups.Rule3_4_5_6_8_9AntipatternBuilders.Rule8AntipatternBuilder;
import compilationUnits.groups.OptionalInvocationsBuilder;
import compilationUnits.groups.Rule3_4_5_6_8_9AntipatternBuilders.Rule3AntipatternBuilder;
import compilationUnits.groups.rule14_15_16AntipatternBuilders.Rule15AntipatternBuilder;
import compilationUnits.groups.Rule25AntipatternBuilder;
import compilationUnits.groups.Rule3_4_5_6_8_9AntipatternBuilders.Rule6AntipatternBuilder;
import compilationUnits.groups.Rule12AntipatternBuilder;
import compilationUnits.groups.Rule19AntipatternBuilder;
import compilationUnits.groups.Rule3_4_5_6_8_9AntipatternBuilders.Rule9AntipatternBuilder;
import compilationUnits.groups.Rule3_4_5_6_8_9AntipatternBuilders.Rule4AntipatternBuilder;
import compilationUnits.groups.Rule3_4_5_6_8_9AntipatternBuilders.Rule7AntipatternBuilder;
import compilationUnits.groups.rule14_15_16AntipatternBuilders.Rule14AntipatternBuilder;
import compilationUnits.groups.rule21AntipatternBuilder.Rule21AntipatternBuilder;
import compilationUnits.groups.Rule1AntipatternBuilder;

public class MCompilationUnitImpl implements MCompilationUnit {

	private java.lang.Object underlyingObj_;

	private static final ToString ToString_INSTANCE = new ToString();
	private static final Rule17AntipatternBuilder Rule17AntipatternBuilder_INSTANCE = new Rule17AntipatternBuilder();
	private static final Rule20AntipatternBuilder Rule20AntipatternBuilder_INSTANCE = new Rule20AntipatternBuilder();
	private static final Rule5AntipatternBuilder Rule5AntipatternBuilder_INSTANCE = new Rule5AntipatternBuilder();
	private static final Rule26AntipatternBuilder Rule26AntipatternBuilder_INSTANCE = new Rule26AntipatternBuilder();
	private static final Rule16AntipatternBuilder Rule16AntipatternBuilder_INSTANCE = new Rule16AntipatternBuilder();
	private static final Rule18AntipatternBuilder Rule18AntipatternBuilder_INSTANCE = new Rule18AntipatternBuilder();
	private static final Rule2AntipatternBuilder Rule2AntipatternBuilder_INSTANCE = new Rule2AntipatternBuilder();
	private static final Rule8AntipatternBuilder Rule8AntipatternBuilder_INSTANCE = new Rule8AntipatternBuilder();
	private static final OptionalInvocationsBuilder OptionalInvocationsBuilder_INSTANCE = new OptionalInvocationsBuilder();
	private static final Rule3AntipatternBuilder Rule3AntipatternBuilder_INSTANCE = new Rule3AntipatternBuilder();
	private static final Rule15AntipatternBuilder Rule15AntipatternBuilder_INSTANCE = new Rule15AntipatternBuilder();
	private static final Rule25AntipatternBuilder Rule25AntipatternBuilder_INSTANCE = new Rule25AntipatternBuilder();
	private static final Rule6AntipatternBuilder Rule6AntipatternBuilder_INSTANCE = new Rule6AntipatternBuilder();
	private static final Rule12AntipatternBuilder Rule12AntipatternBuilder_INSTANCE = new Rule12AntipatternBuilder();
	private static final Rule19AntipatternBuilder Rule19AntipatternBuilder_INSTANCE = new Rule19AntipatternBuilder();
	private static final Rule9AntipatternBuilder Rule9AntipatternBuilder_INSTANCE = new Rule9AntipatternBuilder();
	private static final Rule4AntipatternBuilder Rule4AntipatternBuilder_INSTANCE = new Rule4AntipatternBuilder();
	private static final Rule7AntipatternBuilder Rule7AntipatternBuilder_INSTANCE = new Rule7AntipatternBuilder();
	private static final Rule14AntipatternBuilder Rule14AntipatternBuilder_INSTANCE = new Rule14AntipatternBuilder();
	private static final Rule21AntipatternBuilder Rule21AntipatternBuilder_INSTANCE = new Rule21AntipatternBuilder();
	private static final Rule1AntipatternBuilder Rule1AntipatternBuilder_INSTANCE = new Rule1AntipatternBuilder();

	public MCompilationUnitImpl(java.lang.Object underlyingObj) {
		underlyingObj_ = underlyingObj;
	}

	@Override
	public java.lang.Object getUnderlyingObject() {
		return underlyingObj_;
	}

	@Override
	@ro.lrg.xcore.metametamodel.ThisIsAProperty
	public java.lang.String toString() {
		return ToString_INSTANCE.compute(this);
	}

	@Override
	@ro.lrg.xcore.metametamodel.ThisIsARelationBuilder
	public ro.lrg.xcore.metametamodel.Group<MMethod> rule17AntipatternBuilder() {
		return Rule17AntipatternBuilder_INSTANCE.buildGroup(this);
	}

	@Override
	@ro.lrg.xcore.metametamodel.ThisIsARelationBuilder
	public ro.lrg.xcore.metametamodel.Group<MVariableDeclaration> rule20AntipatternBuilder() {
		return Rule20AntipatternBuilder_INSTANCE.buildGroup(this);
	}

	@Override
	@ro.lrg.xcore.metametamodel.ThisIsARelationBuilder
	public ro.lrg.xcore.metametamodel.Group<MIfStatement> rule5AntipatternBuilder() {
		return Rule5AntipatternBuilder_INSTANCE.buildGroup(this);
	}

	@Override
	@ro.lrg.xcore.metametamodel.ThisIsARelationBuilder
	public ro.lrg.xcore.metametamodel.Group<MPrefixExpression> rule26AntipatternBuilder() {
		return Rule26AntipatternBuilder_INSTANCE.buildGroup(this);
	}

	@Override
	@ro.lrg.xcore.metametamodel.ThisIsARelationBuilder
	public ro.lrg.xcore.metametamodel.Group<MVariableDeclaration> rule16AntipatternBuilder() {
		return Rule16AntipatternBuilder_INSTANCE.buildGroup(this);
	}

	@Override
	@ro.lrg.xcore.metametamodel.ThisIsARelationBuilder
	public ro.lrg.xcore.metametamodel.Group<MVariableDeclaration> rule18AntipatternBuilder() {
		return Rule18AntipatternBuilder_INSTANCE.buildGroup(this);
	}

	@Override
	@ro.lrg.xcore.metametamodel.ThisIsARelationBuilder
	public ro.lrg.xcore.metametamodel.Group<MInvocation> rule2AntipatternBuilder() {
		return Rule2AntipatternBuilder_INSTANCE.buildGroup(this);
	}

	@Override
	@ro.lrg.xcore.metametamodel.ThisIsARelationBuilder
	public ro.lrg.xcore.metametamodel.Group<MIfStatement> rule8AntipatternBuilder() {
		return Rule8AntipatternBuilder_INSTANCE.buildGroup(this);
	}

	@Override
	@ro.lrg.xcore.metametamodel.ThisIsARelationBuilder
	public ro.lrg.xcore.metametamodel.Group<MInvocation> optionalInvocationsBuilder() {
		return OptionalInvocationsBuilder_INSTANCE.buildGroup(this);
	}

	@Override
	@ro.lrg.xcore.metametamodel.ThisIsARelationBuilder
	public ro.lrg.xcore.metametamodel.Group<MIfStatement> rule3AntipatternBuilder() {
		return Rule3AntipatternBuilder_INSTANCE.buildGroup(this);
	}

	@Override
	@ro.lrg.xcore.metametamodel.ThisIsARelationBuilder
	public ro.lrg.xcore.metametamodel.Group<MVariableDeclaration> rule15AntipatternBuilder() {
		return Rule15AntipatternBuilder_INSTANCE.buildGroup(this);
	}

	@Override
	@ro.lrg.xcore.metametamodel.ThisIsARelationBuilder
	public ro.lrg.xcore.metametamodel.Group<MInfixExpression> rule25AntipatternBuilder() {
		return Rule25AntipatternBuilder_INSTANCE.buildGroup(this);
	}

	@Override
	@ro.lrg.xcore.metametamodel.ThisIsARelationBuilder
	public ro.lrg.xcore.metametamodel.Group<MIfStatement> rule6AntipatternBuilder() {
		return Rule6AntipatternBuilder_INSTANCE.buildGroup(this);
	}

	@Override
	@ro.lrg.xcore.metametamodel.ThisIsARelationBuilder
	public ro.lrg.xcore.metametamodel.Group<MInvocation> rule12AntipatternBuilder() {
		return Rule12AntipatternBuilder_INSTANCE.buildGroup(this);
	}

	@Override
	@ro.lrg.xcore.metametamodel.ThisIsARelationBuilder
	public ro.lrg.xcore.metametamodel.Group<MInvocation> rule19AntipatternBuilder() {
		return Rule19AntipatternBuilder_INSTANCE.buildGroup(this);
	}

	@Override
	@ro.lrg.xcore.metametamodel.ThisIsARelationBuilder
	public ro.lrg.xcore.metametamodel.Group<MIfStatement> rule9AntipatternBuilder() {
		return Rule9AntipatternBuilder_INSTANCE.buildGroup(this);
	}

	@Override
	@ro.lrg.xcore.metametamodel.ThisIsARelationBuilder
	public ro.lrg.xcore.metametamodel.Group<MIfStatement> rule4AntipatternBuilder() {
		return Rule4AntipatternBuilder_INSTANCE.buildGroup(this);
	}

	@Override
	@ro.lrg.xcore.metametamodel.ThisIsARelationBuilder
	public ro.lrg.xcore.metametamodel.Group<MIfStatement> rule7AntipatternBuilder() {
		return Rule7AntipatternBuilder_INSTANCE.buildGroup(this);
	}

	@Override
	@ro.lrg.xcore.metametamodel.ThisIsARelationBuilder
	public ro.lrg.xcore.metametamodel.Group<MVariableDeclaration> rule14AntipatternBuilder() {
		return Rule14AntipatternBuilder_INSTANCE.buildGroup(this);
	}

	@Override
	@ro.lrg.xcore.metametamodel.ThisIsARelationBuilder
	public ro.lrg.xcore.metametamodel.Group<MInvocation> rule21AntipatternBuilder() {
		return Rule21AntipatternBuilder_INSTANCE.buildGroup(this);
	}

	@Override
	@ro.lrg.xcore.metametamodel.ThisIsARelationBuilder
	public ro.lrg.xcore.metametamodel.Group<MAssignment> rule1AntipatternBuilder() {
		return Rule1AntipatternBuilder_INSTANCE.buildGroup(this);
	}

	public boolean equals(Object obj) {
		if (null == obj || !(obj instanceof MCompilationUnitImpl)) {
			return false;
		}
		MCompilationUnitImpl iObj = (MCompilationUnitImpl)obj;
		return underlyingObj_.equals(iObj.underlyingObj_);
	}

	public int hashCode() {
		return 97 * underlyingObj_.hashCode();
	}
}
