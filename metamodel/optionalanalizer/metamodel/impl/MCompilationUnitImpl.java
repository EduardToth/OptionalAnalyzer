package optionalanalizer.metamodel.impl;

import optionalanalizer.metamodel.entity.*;
import compilationUnits.properties.ToString;
import compilationUnits.groups.Rule17AntipatternBuilder;
import compilationUnits.groups.Rule20AntipatternBuilder;
import compilationUnits.groups.Rule_3AntipatternBuilder;
import compilationUnits.groups.Rule26AntipatternBuilder;
import compilationUnits.groups.Rule_7AntipatternBuilder;
import compilationUnits.groups.Rule16AntipatternBuilder;
import compilationUnits.groups.Rule18AntipatternBuilder;
import compilationUnits.groups.Rule_2AntipatternBuilder;
import compilationUnits.groups.Rule_5AntipatternBuilder;
import compilationUnits.groups.Rule15AntipatternBuilder;
import compilationUnits.groups.Rule25AntipatternBuilder;
import compilationUnits.groups.Rule_8AntipatternBuilder;
import compilationUnits.groups.Rule12AntipatternBuilder;
import compilationUnits.groups.Rule19AntipatternBuilder;
import compilationUnits.groups.Rule_4AntipatternBuilder;
import compilationUnits.groups.Rule_6AntipatternBuilder;
import compilationUnits.groups.Rule_1AntipatternBuilder;
import compilationUnits.groups.Rule14AntipatternBuilder;
import compilationUnits.groups.Rule21AntipatternBuilder;
import compilationUnits.groups.Rule_9AntipatternBuilder;

public class MCompilationUnitImpl implements MCompilationUnit {

	private java.lang.Object underlyingObj_;

	private static final ToString ToString_INSTANCE = new ToString();
	private static final Rule17AntipatternBuilder Rule17AntipatternBuilder_INSTANCE = new Rule17AntipatternBuilder();
	private static final Rule20AntipatternBuilder Rule20AntipatternBuilder_INSTANCE = new Rule20AntipatternBuilder();
	private static final Rule_3AntipatternBuilder Rule_3AntipatternBuilder_INSTANCE = new Rule_3AntipatternBuilder();
	private static final Rule26AntipatternBuilder Rule26AntipatternBuilder_INSTANCE = new Rule26AntipatternBuilder();
	private static final Rule_7AntipatternBuilder Rule_7AntipatternBuilder_INSTANCE = new Rule_7AntipatternBuilder();
	private static final Rule16AntipatternBuilder Rule16AntipatternBuilder_INSTANCE = new Rule16AntipatternBuilder();
	private static final Rule18AntipatternBuilder Rule18AntipatternBuilder_INSTANCE = new Rule18AntipatternBuilder();
	private static final Rule_2AntipatternBuilder Rule_2AntipatternBuilder_INSTANCE = new Rule_2AntipatternBuilder();
	private static final Rule_5AntipatternBuilder Rule_5AntipatternBuilder_INSTANCE = new Rule_5AntipatternBuilder();
	private static final Rule15AntipatternBuilder Rule15AntipatternBuilder_INSTANCE = new Rule15AntipatternBuilder();
	private static final Rule25AntipatternBuilder Rule25AntipatternBuilder_INSTANCE = new Rule25AntipatternBuilder();
	private static final Rule_8AntipatternBuilder Rule_8AntipatternBuilder_INSTANCE = new Rule_8AntipatternBuilder();
	private static final Rule12AntipatternBuilder Rule12AntipatternBuilder_INSTANCE = new Rule12AntipatternBuilder();
	private static final Rule19AntipatternBuilder Rule19AntipatternBuilder_INSTANCE = new Rule19AntipatternBuilder();
	private static final Rule_4AntipatternBuilder Rule_4AntipatternBuilder_INSTANCE = new Rule_4AntipatternBuilder();
	private static final Rule_6AntipatternBuilder Rule_6AntipatternBuilder_INSTANCE = new Rule_6AntipatternBuilder();
	private static final Rule_1AntipatternBuilder Rule_1AntipatternBuilder_INSTANCE = new Rule_1AntipatternBuilder();
	private static final Rule14AntipatternBuilder Rule14AntipatternBuilder_INSTANCE = new Rule14AntipatternBuilder();
	private static final Rule21AntipatternBuilder Rule21AntipatternBuilder_INSTANCE = new Rule21AntipatternBuilder();
	private static final Rule_9AntipatternBuilder Rule_9AntipatternBuilder_INSTANCE = new Rule_9AntipatternBuilder();

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
	public ro.lrg.xcore.metametamodel.Group<MRule17Atom> rule17AntipatternBuilder() {
		return Rule17AntipatternBuilder_INSTANCE.buildGroup(this);
	}

	@Override
	@ro.lrg.xcore.metametamodel.ThisIsARelationBuilder
	public ro.lrg.xcore.metametamodel.Group<MRule20Atom> rule20AntipatternBuilder() {
		return Rule20AntipatternBuilder_INSTANCE.buildGroup(this);
	}

	@Override
	@ro.lrg.xcore.metametamodel.ThisIsARelationBuilder
	public ro.lrg.xcore.metametamodel.Group<MRule3Atom> rule_3AntipatternBuilder() {
		return Rule_3AntipatternBuilder_INSTANCE.buildGroup(this);
	}

	@Override
	@ro.lrg.xcore.metametamodel.ThisIsARelationBuilder
	public ro.lrg.xcore.metametamodel.Group<MRule26Atom> rule26AntipatternBuilder() {
		return Rule26AntipatternBuilder_INSTANCE.buildGroup(this);
	}

	@Override
	@ro.lrg.xcore.metametamodel.ThisIsARelationBuilder
	public ro.lrg.xcore.metametamodel.Group<MRule7Atom> rule_7AntipatternBuilder() {
		return Rule_7AntipatternBuilder_INSTANCE.buildGroup(this);
	}

	@Override
	@ro.lrg.xcore.metametamodel.ThisIsARelationBuilder
	public ro.lrg.xcore.metametamodel.Group<MRule16Atom> rule16AntipatternBuilder() {
		return Rule16AntipatternBuilder_INSTANCE.buildGroup(this);
	}

	@Override
	@ro.lrg.xcore.metametamodel.ThisIsARelationBuilder
	public ro.lrg.xcore.metametamodel.Group<MRule18Atom> rule18AntipatternBuilder() {
		return Rule18AntipatternBuilder_INSTANCE.buildGroup(this);
	}

	@Override
	@ro.lrg.xcore.metametamodel.ThisIsARelationBuilder
	public ro.lrg.xcore.metametamodel.Group<MRule2Atom> rule_2AntipatternBuilder() {
		return Rule_2AntipatternBuilder_INSTANCE.buildGroup(this);
	}

	@Override
	@ro.lrg.xcore.metametamodel.ThisIsARelationBuilder
	public ro.lrg.xcore.metametamodel.Group<MRule5Atom> rule_5AntipatternBuilder() {
		return Rule_5AntipatternBuilder_INSTANCE.buildGroup(this);
	}

	@Override
	@ro.lrg.xcore.metametamodel.ThisIsARelationBuilder
	public ro.lrg.xcore.metametamodel.Group<MRule15Atom> rule15AntipatternBuilder() {
		return Rule15AntipatternBuilder_INSTANCE.buildGroup(this);
	}

	@Override
	@ro.lrg.xcore.metametamodel.ThisIsARelationBuilder
	public ro.lrg.xcore.metametamodel.Group<MRule25Atom> rule25AntipatternBuilder() {
		return Rule25AntipatternBuilder_INSTANCE.buildGroup(this);
	}

	@Override
	@ro.lrg.xcore.metametamodel.ThisIsARelationBuilder
	public ro.lrg.xcore.metametamodel.Group<MRule8Atom> rule_8AntipatternBuilder() {
		return Rule_8AntipatternBuilder_INSTANCE.buildGroup(this);
	}

	@Override
	@ro.lrg.xcore.metametamodel.ThisIsARelationBuilder
	public ro.lrg.xcore.metametamodel.Group<MRule12Atom> rule12AntipatternBuilder() {
		return Rule12AntipatternBuilder_INSTANCE.buildGroup(this);
	}

	@Override
	@ro.lrg.xcore.metametamodel.ThisIsARelationBuilder
	public ro.lrg.xcore.metametamodel.Group<MRule19Atom> rule19AntipatternBuilder() {
		return Rule19AntipatternBuilder_INSTANCE.buildGroup(this);
	}

	@Override
	@ro.lrg.xcore.metametamodel.ThisIsARelationBuilder
	public ro.lrg.xcore.metametamodel.Group<MRule4Atom> rule_4AntipatternBuilder() {
		return Rule_4AntipatternBuilder_INSTANCE.buildGroup(this);
	}

	@Override
	@ro.lrg.xcore.metametamodel.ThisIsARelationBuilder
	public ro.lrg.xcore.metametamodel.Group<MRule6Atom> rule_6AntipatternBuilder() {
		return Rule_6AntipatternBuilder_INSTANCE.buildGroup(this);
	}

	@Override
	@ro.lrg.xcore.metametamodel.ThisIsARelationBuilder
	public ro.lrg.xcore.metametamodel.Group<MRule1Atom> rule_1AntipatternBuilder() {
		return Rule_1AntipatternBuilder_INSTANCE.buildGroup(this);
	}

	@Override
	@ro.lrg.xcore.metametamodel.ThisIsARelationBuilder
	public ro.lrg.xcore.metametamodel.Group<MRule14Atom> rule14AntipatternBuilder() {
		return Rule14AntipatternBuilder_INSTANCE.buildGroup(this);
	}

	@Override
	@ro.lrg.xcore.metametamodel.ThisIsARelationBuilder
	public ro.lrg.xcore.metametamodel.Group<MRule21Atom> rule21AntipatternBuilder() {
		return Rule21AntipatternBuilder_INSTANCE.buildGroup(this);
	}

	@Override
	@ro.lrg.xcore.metametamodel.ThisIsARelationBuilder
	public ro.lrg.xcore.metametamodel.Group<MRule9Atom> rule_9AntipatternBuilder() {
		return Rule_9AntipatternBuilder_INSTANCE.buildGroup(this);
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
