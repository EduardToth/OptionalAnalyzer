package optionalanalizer.metamodel.entity;

public interface MProject extends ro.lrg.xcore.metametamodel.XEntity {

	@ro.lrg.xcore.metametamodel.ThisIsAProperty
	public java.lang.String toString();

	@ro.lrg.xcore.metametamodel.ThisIsARelationBuilder
	public ro.lrg.xcore.metametamodel.Group<MCompilationUnit> compilationUnitBuilder();

	@ro.lrg.xcore.metametamodel.ThisIsAnAction(numParams = 0) 
	public void showProjectInEditor ();

	java.lang.Object getUnderlyingObject();
}
