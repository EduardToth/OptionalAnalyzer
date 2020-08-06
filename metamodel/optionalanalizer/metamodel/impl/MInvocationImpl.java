package optionalanalizer.metamodel.impl;

import optionalanalizer.metamodel.entity.*;
import antipatternFinders.isPresentInvocationFinder.InvocationInfoGetter;

public class MInvocationImpl implements MInvocation {

	private java.lang.Object underlyingObj_;

	private static final InvocationInfoGetter InvocationInfoGetter_INSTANCE = new InvocationInfoGetter();

	public MInvocationImpl(java.lang.Object underlyingObj) {
		underlyingObj_ = underlyingObj;
	}

	@Override
	public java.lang.Object getUnderlyingObject() {
		return underlyingObj_;
	}

	@Override
	@ro.lrg.xcore.metametamodel.ThisIsAProperty
	public java.lang.String invocationInfoGetter() {
		return InvocationInfoGetter_INSTANCE.compute(this);
	}

	public boolean equals(Object obj) {
		if (null == obj || !(obj instanceof MInvocationImpl)) {
			return false;
		}
		MInvocationImpl iObj = (MInvocationImpl)obj;
		return underlyingObj_.equals(iObj.underlyingObj_);
	}

	public int hashCode() {
		return 97 * underlyingObj_.hashCode();
	}
}
