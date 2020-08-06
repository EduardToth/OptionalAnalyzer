package optionalanalizer.metamodel.impl;

import optionalanalizer.metamodel.entity.*;
import antipatternFinders.isPresentInvocationFinder.OptionalIsPresentInvocationBuilder;

public class MCompilationUnitImpl implements MCompilationUnit {

	private java.lang.Object underlyingObj_;

	private static final OptionalIsPresentInvocationBuilder OptionalIsPresentInvocationBuilder_INSTANCE = new OptionalIsPresentInvocationBuilder();

	public MCompilationUnitImpl(java.lang.Object underlyingObj) {
		underlyingObj_ = underlyingObj;
	}

	@Override
	public java.lang.Object getUnderlyingObject() {
		return underlyingObj_;
	}

	@Override
	@ro.lrg.xcore.metametamodel.ThisIsARelationBuilder
	public ro.lrg.xcore.metametamodel.Group<MInvocation> optionalIsPresentInvocationBuilder() {
		return OptionalIsPresentInvocationBuilder_INSTANCE.buildGroup(this);
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
