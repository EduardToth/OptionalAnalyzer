package optionalanalizer.metamodel.entity;

public interface MCompilationUnit extends ro.lrg.xcore.metametamodel.XEntity {

	@ro.lrg.xcore.metametamodel.ThisIsARelationBuilder
	public ro.lrg.xcore.metametamodel.Group<MInvocation> optionalIsPresentInvocationBuilder();

	java.lang.Object getUnderlyingObject();
}
