package optionalanalizer.metamodel.impl;

import optionalanalizer.metamodel.entity.*;
import compilationUnits.properties.ToString;
import compilationUnits.groups.OptionalInvocationsBuilder;
import compilationUnits.groups.Rule3_4_5_6AntipatternBuilders.Rule3AntipatternBuilder;
import compilationUnits.groups.Rule3_4_5_6AntipatternBuilders.Rule5AntipatternBuilder;
import compilationUnits.groups.Rule3_4_5_6AntipatternBuilders.Rule6AntipatternBuilder;
import compilationUnits.groups.Rule3_4_5_6AntipatternBuilders.Rule4AntipatternBuilder;
import compilationUnits.groups.Rule2AntipatternBuilder;
import compilationUnits.groups.Rule1AntipatternBuilder;
import compilationUnits.actions.ShowInEditor;

public class MCompilationUnitImpl implements MCompilationUnit {

	private java.lang.Object underlyingObj_;

	private static final ToString ToString_INSTANCE = new ToString();
	private static final OptionalInvocationsBuilder OptionalInvocationsBuilder_INSTANCE = new OptionalInvocationsBuilder();
	private static final Rule3AntipatternBuilder Rule3AntipatternBuilder_INSTANCE = new Rule3AntipatternBuilder();
	private static final Rule5AntipatternBuilder Rule5AntipatternBuilder_INSTANCE = new Rule5AntipatternBuilder();
	private static final Rule6AntipatternBuilder Rule6AntipatternBuilder_INSTANCE = new Rule6AntipatternBuilder();
	private static final Rule4AntipatternBuilder Rule4AntipatternBuilder_INSTANCE = new Rule4AntipatternBuilder();
	private static final Rule2AntipatternBuilder Rule2AntipatternBuilder_INSTANCE = new Rule2AntipatternBuilder();
	private static final Rule1AntipatternBuilder Rule1AntipatternBuilder_INSTANCE = new Rule1AntipatternBuilder();
	private static final ShowInEditor ShowInEditor_INSTANCE = new ShowInEditor();

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
	public ro.lrg.xcore.metametamodel.Group<MIfStatement> rule5AntipatternBuilder() {
		return Rule5AntipatternBuilder_INSTANCE.buildGroup(this);
	}

	@Override
	@ro.lrg.xcore.metametamodel.ThisIsARelationBuilder
	public ro.lrg.xcore.metametamodel.Group<MIfStatement> rule6AntipatternBuilder() {
		return Rule6AntipatternBuilder_INSTANCE.buildGroup(this);
	}

	@Override
	@ro.lrg.xcore.metametamodel.ThisIsARelationBuilder
	public ro.lrg.xcore.metametamodel.Group<MIfStatement> rule4AntipatternBuilder() {
		return Rule4AntipatternBuilder_INSTANCE.buildGroup(this);
	}

	@Override
	@ro.lrg.xcore.metametamodel.ThisIsARelationBuilder
	public ro.lrg.xcore.metametamodel.Group<MInvocation> rule2AntipatternBuilder() {
		return Rule2AntipatternBuilder_INSTANCE.buildGroup(this);
	}

	@Override
	@ro.lrg.xcore.metametamodel.ThisIsARelationBuilder
	public ro.lrg.xcore.metametamodel.Group<MAssignment> rule1AntipatternBuilder() {
		return Rule1AntipatternBuilder_INSTANCE.buildGroup(this);
	}

	@Override
	@ro.lrg.xcore.metametamodel.ThisIsAnAction
	public java.lang.String showInEditor() {
		return ShowInEditor_INSTANCE.performAction(this, ro.lrg.xcore.metametamodel.HListEmpty.getInstance());
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
