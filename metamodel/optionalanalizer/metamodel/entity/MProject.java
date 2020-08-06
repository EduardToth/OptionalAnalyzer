package optionalanalizer.metamodel.entity;

public interface MProject extends ro.lrg.xcore.metametamodel.XEntity {

	@ro.lrg.xcore.metametamodel.ThisIsARelationBuilder
	public ro.lrg.xcore.metametamodel.Group<MCompilationUnit> compilationUnitBuilder();

	java.lang.Object getUnderlyingObject();
}
