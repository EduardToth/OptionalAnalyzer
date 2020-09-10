package optionalanalizer.metamodel.impl;

import optionalanalizer.metamodel.entity.*;
import methodInvocations.properties.InvocationToOptionalsPresent;
import methodInvocations.properties.ToString;
import methodInvocations.actions.ShowInEditor;

public class MInvocationImpl implements MInvocation {

	private java.lang.Object underlyingObj_;

	private static final InvocationToOptionalsPresent InvocationToOptionalsPresent_INSTANCE = new InvocationToOptionalsPresent();
	private static final ToString ToString_INSTANCE = new ToString();
	private static final ShowInEditor ShowInEditor_INSTANCE = new ShowInEditor();

	public MInvocationImpl(java.lang.Object underlyingObj) {
		underlyingObj_ = underlyingObj;
	}

	@Override
	public java.lang.Object getUnderlyingObject() {
		return underlyingObj_;
	}

	@Override
	@ro.lrg.xcore.metametamodel.ThisIsAProperty
	public java.lang.String invocationToOptionalsPresent() {
		return InvocationToOptionalsPresent_INSTANCE.compute(this);
	}

	@Override
	@ro.lrg.xcore.metametamodel.ThisIsAProperty
	public java.lang.String toString() {
		return ToString_INSTANCE.compute(this);
	}

	@Override
	@ro.lrg.xcore.metametamodel.ThisIsAnAction
	public void showInEditor() {
		 ShowInEditor_INSTANCE.performAction(this, ro.lrg.xcore.metametamodel.HListEmpty.getInstance());
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
