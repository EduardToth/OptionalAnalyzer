package optionalanalizer.metamodel.impl;

import optionalanalizer.metamodel.entity.*;
import compilationUnits.properties.ToString;
import compilationUnits.groups.OptionalInvocationsGroup;
import compilationUnits.groups.Rule2AntipatternGroup;
import compilationUnits.groups.Rule3AntipatternGroup;
import compilationUnits.groups.OptionalAssignmentToNullGroup;
import compilationUnits.actions.ShowInEditor;

public class MCompilationUnitImpl implements MCompilationUnit {

	private java.lang.Object underlyingObj_;

	private static final ToString ToString_INSTANCE = new ToString();
	private static final OptionalInvocationsGroup OptionalInvocationsGroup_INSTANCE = new OptionalInvocationsGroup();
	private static final Rule2AntipatternGroup Rule2AntipatternGroup_INSTANCE = new Rule2AntipatternGroup();
	private static final Rule3AntipatternGroup Rule3AntipatternGroup_INSTANCE = new Rule3AntipatternGroup();
	private static final OptionalAssignmentToNullGroup OptionalAssignmentToNullGroup_INSTANCE = new OptionalAssignmentToNullGroup();
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
	public ro.lrg.xcore.metametamodel.Group<MInvocation> optionalInvocationsGroup() {
		return OptionalInvocationsGroup_INSTANCE.buildGroup(this);
	}

	@Override
	@ro.lrg.xcore.metametamodel.ThisIsARelationBuilder
	public ro.lrg.xcore.metametamodel.Group<MInvocation> rule2AntipatternGroup() {
		return Rule2AntipatternGroup_INSTANCE.buildGroup(this);
	}

	@Override
	@ro.lrg.xcore.metametamodel.ThisIsARelationBuilder
	public ro.lrg.xcore.metametamodel.Group<MIfStatement> rule3AntipatternGroup() {
		return Rule3AntipatternGroup_INSTANCE.buildGroup(this);
	}

	@Override
	@ro.lrg.xcore.metametamodel.ThisIsARelationBuilder
	public ro.lrg.xcore.metametamodel.Group<MAssignment> optionalAssignmentToNullGroup() {
		return OptionalAssignmentToNullGroup_INSTANCE.buildGroup(this);
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
