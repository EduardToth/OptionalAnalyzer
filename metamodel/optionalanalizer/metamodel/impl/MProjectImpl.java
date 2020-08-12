package optionalanalizer.metamodel.impl;

import optionalanalizer.metamodel.entity.*;
import projects.properties.ToString;
import projects.groups.CompilationUnitBuilder;
import projects.actions.ShowProjectInEditor;

public class MProjectImpl implements MProject {

	private java.lang.Object underlyingObj_;

	private static final ToString ToString_INSTANCE = new ToString();
	private static final CompilationUnitBuilder CompilationUnitBuilder_INSTANCE = new CompilationUnitBuilder();
	private static final ShowProjectInEditor ShowProjectInEditor_INSTANCE = new ShowProjectInEditor();

	public MProjectImpl(java.lang.Object underlyingObj) {
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
	public ro.lrg.xcore.metametamodel.Group<MCompilationUnit> compilationUnitBuilder() {
		return CompilationUnitBuilder_INSTANCE.buildGroup(this);
	}

	@Override
	@ro.lrg.xcore.metametamodel.ThisIsAnAction
	public void showProjectInEditor() {
		 ShowProjectInEditor_INSTANCE.performAction(this, ro.lrg.xcore.metametamodel.HListEmpty.getInstance());
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
