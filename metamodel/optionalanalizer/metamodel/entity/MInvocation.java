package optionalanalizer.metamodel.entity;

public interface MInvocation extends ro.lrg.xcore.metametamodel.XEntity {

	@ro.lrg.xcore.metametamodel.ThisIsAProperty
	public java.lang.String invocationToOptionalsPresent();

	@ro.lrg.xcore.metametamodel.ThisIsAProperty
	public java.lang.String toString();

	@ro.lrg.xcore.metametamodel.ThisIsAnAction(numParams = 0) 
	public void optionalIsPresentLocatorInEditor ();

	java.lang.Object getUnderlyingObject();
}
