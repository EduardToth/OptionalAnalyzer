package optionalanalizer.metamodel.entity;

public interface MCompilationUnit extends ro.lrg.xcore.metametamodel.XEntity {

	@ro.lrg.xcore.metametamodel.ThisIsAProperty
	public java.lang.String toString();

	@ro.lrg.xcore.metametamodel.ThisIsARelationBuilder
	public ro.lrg.xcore.metametamodel.Group<MInvocation> optionalIsPresentInvocationBuilder();

	@ro.lrg.xcore.metametamodel.ThisIsARelationBuilder
	public ro.lrg.xcore.metametamodel.Group<MVariableDeclarationFragment> optionalInitializedToNullBuilder();

	@ro.lrg.xcore.metametamodel.ThisIsARelationBuilder
	public ro.lrg.xcore.metametamodel.Group<MIfStatement> rule3AntipatternGroup();

	@ro.lrg.xcore.metametamodel.ThisIsARelationBuilder
	public ro.lrg.xcore.metametamodel.Group<MAssignment> optionalAssignmentToNullGroup();

	@ro.lrg.xcore.metametamodel.ThisIsAnAction(numParams = 0) 
	public java.lang.String showInEditor ();

	java.lang.Object getUnderlyingObject();
}
