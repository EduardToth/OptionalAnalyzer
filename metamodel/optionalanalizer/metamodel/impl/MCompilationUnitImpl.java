package optionalanalizer.metamodel.impl;

import optionalanalizer.metamodel.entity.*;
import compilationUnits.properties.LineCounter;
import compilationUnits.properties.ToString;
import compilationUnits.groups.Rule18AntipatternDetector;
import compilationUnits.groups.Rule_3AntipatternDetector;
import compilationUnits.groups.Rule14AntipatternDetector;
import compilationUnits.groups.Rule10AntipatternDetector;
import compilationUnits.groups.Rule15AntipatternDetector;
import compilationUnits.groups.Rule17AntipatternDetector;
import compilationUnits.groups.Rule19AntipatternDetector;
import compilationUnits.groups.Rule13AntipatternDetector;
import FullAnalysis.groups.FullAnalysis;
import compilationUnits.groups.Rule26AntipatternDetector;
import compilationUnits.groups.Rule20AntipatternDetector;
import compilationUnits.groups.Rule21AntipatternDetector;
import compilationUnits.groups.Rule25AntipatternDetector;
import compilationUnits.groups.Rule16AntipatternDetector;
import compilationUnits.groups.Rule_1AntipatternDetector;
import compilationUnits.groups.Rule_5AntipatternDetector;
import compilationUnits.groups.Rule12AntipatternDetector;
import compilationUnits.groups.UncategorizedIsPresentInvocationBasedAntipatternDetector;
import compilationUnits.groups.Rule_2AntipatternDetector;
import compilationUnits.groups.Rule_4AntipatternDetector;
import compilationUnits.groups.Rule_6AntipatternDetector;
import compilationUnits.groups.Rule_9AntipatternDetector;
import compilationUnits.groups.Rule_7AntipatternDetector;
import compilationUnits.groups.Rule_8AntipatternDetector;

public class MCompilationUnitImpl implements MCompilationUnit {

	private java.lang.Object underlyingObj_;

	private static final LineCounter LineCounter_INSTANCE = new LineCounter();
	private static final ToString ToString_INSTANCE = new ToString();
	private static final Rule18AntipatternDetector Rule18AntipatternDetector_INSTANCE = new Rule18AntipatternDetector();
	private static final Rule_3AntipatternDetector Rule_3AntipatternDetector_INSTANCE = new Rule_3AntipatternDetector();
	private static final Rule14AntipatternDetector Rule14AntipatternDetector_INSTANCE = new Rule14AntipatternDetector();
	private static final Rule10AntipatternDetector Rule10AntipatternDetector_INSTANCE = new Rule10AntipatternDetector();
	private static final Rule15AntipatternDetector Rule15AntipatternDetector_INSTANCE = new Rule15AntipatternDetector();
	private static final Rule17AntipatternDetector Rule17AntipatternDetector_INSTANCE = new Rule17AntipatternDetector();
	private static final Rule19AntipatternDetector Rule19AntipatternDetector_INSTANCE = new Rule19AntipatternDetector();
	private static final Rule13AntipatternDetector Rule13AntipatternDetector_INSTANCE = new Rule13AntipatternDetector();
	private static final FullAnalysis FullAnalysis_INSTANCE = new FullAnalysis();
	private static final Rule26AntipatternDetector Rule26AntipatternDetector_INSTANCE = new Rule26AntipatternDetector();
	private static final Rule20AntipatternDetector Rule20AntipatternDetector_INSTANCE = new Rule20AntipatternDetector();
	private static final Rule21AntipatternDetector Rule21AntipatternDetector_INSTANCE = new Rule21AntipatternDetector();
	private static final Rule25AntipatternDetector Rule25AntipatternDetector_INSTANCE = new Rule25AntipatternDetector();
	private static final Rule16AntipatternDetector Rule16AntipatternDetector_INSTANCE = new Rule16AntipatternDetector();
	private static final Rule_1AntipatternDetector Rule_1AntipatternDetector_INSTANCE = new Rule_1AntipatternDetector();
	private static final Rule_5AntipatternDetector Rule_5AntipatternDetector_INSTANCE = new Rule_5AntipatternDetector();
	private static final Rule12AntipatternDetector Rule12AntipatternDetector_INSTANCE = new Rule12AntipatternDetector();
	private static final UncategorizedIsPresentInvocationBasedAntipatternDetector UncategorizedIsPresentInvocationBasedAntipatternDetector_INSTANCE = new UncategorizedIsPresentInvocationBasedAntipatternDetector();
	private static final Rule_2AntipatternDetector Rule_2AntipatternDetector_INSTANCE = new Rule_2AntipatternDetector();
	private static final Rule_4AntipatternDetector Rule_4AntipatternDetector_INSTANCE = new Rule_4AntipatternDetector();
	private static final Rule_6AntipatternDetector Rule_6AntipatternDetector_INSTANCE = new Rule_6AntipatternDetector();
	private static final Rule_9AntipatternDetector Rule_9AntipatternDetector_INSTANCE = new Rule_9AntipatternDetector();
	private static final Rule_7AntipatternDetector Rule_7AntipatternDetector_INSTANCE = new Rule_7AntipatternDetector();
	private static final Rule_8AntipatternDetector Rule_8AntipatternDetector_INSTANCE = new Rule_8AntipatternDetector();

	public MCompilationUnitImpl(java.lang.Object underlyingObj) {
		underlyingObj_ = underlyingObj;
	}

	@Override
	public java.lang.Object getUnderlyingObject() {
		return underlyingObj_;
	}

	@Override
	@ro.lrg.xcore.metametamodel.ThisIsAProperty
	public java.lang.Integer lineCounter() {
		return LineCounter_INSTANCE.compute(this);
	}

	@Override
	@ro.lrg.xcore.metametamodel.ThisIsAProperty
	public java.lang.String toString() {
		return ToString_INSTANCE.compute(this);
	}

	@Override
	@ro.lrg.xcore.metametamodel.ThisIsARelationBuilder
	public ro.lrg.xcore.metametamodel.Group<MRule18Atom> rule18AntipatternDetector() {
		return Rule18AntipatternDetector_INSTANCE.buildGroup(this);
	}

	@Override
	@ro.lrg.xcore.metametamodel.ThisIsARelationBuilder
	public ro.lrg.xcore.metametamodel.Group<MRule3Atom> rule_3AntipatternDetector() {
		return Rule_3AntipatternDetector_INSTANCE.buildGroup(this);
	}

	@Override
	@ro.lrg.xcore.metametamodel.ThisIsARelationBuilder
	public ro.lrg.xcore.metametamodel.Group<MRule14Atom> rule14AntipatternDetector() {
		return Rule14AntipatternDetector_INSTANCE.buildGroup(this);
	}

	@Override
	@ro.lrg.xcore.metametamodel.ThisIsARelationBuilder
	public ro.lrg.xcore.metametamodel.Group<MRule10Atom> rule10AntipatternDetector() {
		return Rule10AntipatternDetector_INSTANCE.buildGroup(this);
	}

	@Override
	@ro.lrg.xcore.metametamodel.ThisIsARelationBuilder
	public ro.lrg.xcore.metametamodel.Group<MRule15Atom> rule15AntipatternDetector() {
		return Rule15AntipatternDetector_INSTANCE.buildGroup(this);
	}

	@Override
	@ro.lrg.xcore.metametamodel.ThisIsARelationBuilder
	public ro.lrg.xcore.metametamodel.Group<MRule17Atom> rule17AntipatternDetector() {
		return Rule17AntipatternDetector_INSTANCE.buildGroup(this);
	}

	@Override
	@ro.lrg.xcore.metametamodel.ThisIsARelationBuilder
	public ro.lrg.xcore.metametamodel.Group<MRule19Atom> rule19AntipatternDetector() {
		return Rule19AntipatternDetector_INSTANCE.buildGroup(this);
	}

	@Override
	@ro.lrg.xcore.metametamodel.ThisIsARelationBuilder
	public ro.lrg.xcore.metametamodel.Group<MRule13Atom> rule13AntipatternDetector() {
		return Rule13AntipatternDetector_INSTANCE.buildGroup(this);
	}

	@Override
	@ro.lrg.xcore.metametamodel.ThisIsARelationBuilder
	public ro.lrg.xcore.metametamodel.Group<MAnalysis> fullAnalysis() {
		return FullAnalysis_INSTANCE.buildGroup(this);
	}

	@Override
	@ro.lrg.xcore.metametamodel.ThisIsARelationBuilder
	public ro.lrg.xcore.metametamodel.Group<MRule26Atom> rule26AntipatternDetector() {
		return Rule26AntipatternDetector_INSTANCE.buildGroup(this);
	}

	@Override
	@ro.lrg.xcore.metametamodel.ThisIsARelationBuilder
	public ro.lrg.xcore.metametamodel.Group<MRule20Atom> rule20AntipatternDetector() {
		return Rule20AntipatternDetector_INSTANCE.buildGroup(this);
	}

	@Override
	@ro.lrg.xcore.metametamodel.ThisIsARelationBuilder
	public ro.lrg.xcore.metametamodel.Group<MRule21Atom> rule21AntipatternDetector() {
		return Rule21AntipatternDetector_INSTANCE.buildGroup(this);
	}

	@Override
	@ro.lrg.xcore.metametamodel.ThisIsARelationBuilder
	public ro.lrg.xcore.metametamodel.Group<MRule25Atom> rule25AntipatternDetector() {
		return Rule25AntipatternDetector_INSTANCE.buildGroup(this);
	}

	@Override
	@ro.lrg.xcore.metametamodel.ThisIsARelationBuilder
	public ro.lrg.xcore.metametamodel.Group<MRule16Atom> rule16AntipatternDetector() {
		return Rule16AntipatternDetector_INSTANCE.buildGroup(this);
	}

	@Override
	@ro.lrg.xcore.metametamodel.ThisIsARelationBuilder
	public ro.lrg.xcore.metametamodel.Group<MRule1Atom> rule_1AntipatternDetector() {
		return Rule_1AntipatternDetector_INSTANCE.buildGroup(this);
	}

	@Override
	@ro.lrg.xcore.metametamodel.ThisIsARelationBuilder
	public ro.lrg.xcore.metametamodel.Group<MRule5Atom> rule_5AntipatternDetector() {
		return Rule_5AntipatternDetector_INSTANCE.buildGroup(this);
	}

	@Override
	@ro.lrg.xcore.metametamodel.ThisIsARelationBuilder
	public ro.lrg.xcore.metametamodel.Group<MRule12Atom> rule12AntipatternDetector() {
		return Rule12AntipatternDetector_INSTANCE.buildGroup(this);
	}

	@Override
	@ro.lrg.xcore.metametamodel.ThisIsARelationBuilder
	public ro.lrg.xcore.metametamodel.Group<MUncategorizedIsPresentAtom> uncategorizedIsPresentInvocationBasedAntipatternDetector() {
		return UncategorizedIsPresentInvocationBasedAntipatternDetector_INSTANCE.buildGroup(this);
	}

	@Override
	@ro.lrg.xcore.metametamodel.ThisIsARelationBuilder
	public ro.lrg.xcore.metametamodel.Group<MRule2Atom> rule_2AntipatternDetector() {
		return Rule_2AntipatternDetector_INSTANCE.buildGroup(this);
	}

	@Override
	@ro.lrg.xcore.metametamodel.ThisIsARelationBuilder
	public ro.lrg.xcore.metametamodel.Group<MRule4Atom> rule_4AntipatternDetector() {
		return Rule_4AntipatternDetector_INSTANCE.buildGroup(this);
	}

	@Override
	@ro.lrg.xcore.metametamodel.ThisIsARelationBuilder
	public ro.lrg.xcore.metametamodel.Group<MRule6Atom> rule_6AntipatternDetector() {
		return Rule_6AntipatternDetector_INSTANCE.buildGroup(this);
	}

	@Override
	@ro.lrg.xcore.metametamodel.ThisIsARelationBuilder
	public ro.lrg.xcore.metametamodel.Group<MRule9Atom> rule_9AntipatternDetector() {
		return Rule_9AntipatternDetector_INSTANCE.buildGroup(this);
	}

	@Override
	@ro.lrg.xcore.metametamodel.ThisIsARelationBuilder
	public ro.lrg.xcore.metametamodel.Group<MRule7Atom> rule_7AntipatternDetector() {
		return Rule_7AntipatternDetector_INSTANCE.buildGroup(this);
	}

	@Override
	@ro.lrg.xcore.metametamodel.ThisIsARelationBuilder
	public ro.lrg.xcore.metametamodel.Group<MRule8Atom> rule_8AntipatternDetector() {
		return Rule_8AntipatternDetector_INSTANCE.buildGroup(this);
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
