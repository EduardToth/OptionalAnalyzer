package optionalanalizer.metamodel.impl;

import optionalanalizer.metamodel.entity.*;
import antipatternFinders.isPresentInvocationFinder.CompilationUnitBuilder;

public class MProjectImpl implements MProject {

	private java.lang.Object underlyingObj_;

	private static final CompilationUnitBuilder CompilationUnitBuilder_INSTANCE = new CompilationUnitBuilder();

	public MProjectImpl(java.lang.Object underlyingObj) {
		underlyingObj_ = underlyingObj;
	}

	@Override
	public java.lang.Object getUnderlyingObject() {
		return underlyingObj_;
	}

	@Override
	@ro.lrg.xcore.metametamodel.ThisIsARelationBuilder
	public ro.lrg.xcore.metametamodel.Group<MCompilationUnit> compilationUnitBuilder() {
		return CompilationUnitBuilder_INSTANCE.buildGroup(this);
	}

	public boolean equals(Object obj) {
		if (null == obj || !(obj instanceof MProjectImpl)) {
			return false;
		}
		MProjectImpl iObj = (MProjectImpl)obj;
		return underlyingObj_.equals(iObj.underlyingObj_);
	}

	public int hashCode() {
		return 97 * underlyingObj_.hashCode();
	}
}