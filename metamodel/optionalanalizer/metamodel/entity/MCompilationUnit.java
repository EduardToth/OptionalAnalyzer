package optionalanalizer.metamodel.entity;

public interface MCompilationUnit extends ro.lrg.xcore.metametamodel.XEntity {

	@ro.lrg.xcore.metametamodel.ThisIsAProperty
	public java.lang.String toString();

	@ro.lrg.xcore.metametamodel.ThisIsARelationBuilder
	public ro.lrg.xcore.metametamodel.Group<MInvocation> optionalInvocationsBuilder();

	@ro.lrg.xcore.metametamodel.ThisIsARelationBuilder
	public ro.lrg.xcore.metametamodel.Group<MIfStatement> rule3AntipatternBuilder();

	@ro.lrg.xcore.metametamodel.ThisIsARelationBuilder
	public ro.lrg.xcore.metametamodel.Group<MIfStatement> rule5AntipatternBuilder();

	@ro.lrg.xcore.metametamodel.ThisIsARelationBuilder
	public ro.lrg.xcore.metametamodel.Group<MIfStatement> rule6AntipatternBuilder();

	@ro.lrg.xcore.metametamodel.ThisIsARelationBuilder
	public ro.lrg.xcore.metametamodel.Group<MIfStatement> rule4AntipatternBuilder();

	@ro.lrg.xcore.metametamodel.ThisIsARelationBuilder
	public ro.lrg.xcore.metametamodel.Group<MInvocation> rule2AntipatternBuilder();

	@ro.lrg.xcore.metametamodel.ThisIsARelationBuilder
	public ro.lrg.xcore.metametamodel.Group<MAssignment> rule1AntipatternBuilder();

	@ro.lrg.xcore.metametamodel.ThisIsAnAction(numParams = 0) 
	public java.lang.String showInEditor ();

	java.lang.Object getUnderlyingObject();
}
