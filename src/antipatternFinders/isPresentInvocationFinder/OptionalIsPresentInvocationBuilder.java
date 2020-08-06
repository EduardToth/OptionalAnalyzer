package antipatternFinders.isPresentInvocationFinder;

import java.util.Arrays;

import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.MethodInvocation;

import optionalanalizer.metamodel.entity.MCompilationUnit;
import optionalanalizer.metamodel.factory.Factory;
import optionalanalizer.metamodel.entity.MInvocation;
import ro.lrg.xcore.metametamodel.Group;
import ro.lrg.xcore.metametamodel.IRelationBuilder;
import ro.lrg.xcore.metametamodel.RelationBuilder;

@RelationBuilder
public class OptionalIsPresentInvocationBuilder implements IRelationBuilder<MInvocation, MCompilationUnit>{

	private boolean isInvocatorOfOptionalType(MethodInvocation invocation) {
		String[] optionalTypes = { "java.util.Optional", "java.util.OptionalInt", "java.Util.OptionalDouble"};
		try {
			String typeOfInvocator = invocation.getExpression().resolveTypeBinding().getQualifiedName();
			String rawType = typeOfInvocator.split("<.*>")[ 0 ];
			return Arrays.asList(optionalTypes).stream()
					.filter(type -> type.equals(rawType))
					.findFirst().isPresent();
		} catch(NullPointerException ex) {}
		return false;
	}

	@Override
	public Group<MInvocation> buildGroup(MCompilationUnit arg0) {
		ICompilationUnit iCompilationUnit = (ICompilationUnit)arg0.getUnderlyingObject();
		Group<MInvocation> group = new Group<>();

		CompilationUnit compilationUnit = parse(iCompilationUnit);



		compilationUnit.accept(new ASTVisitor() {

			@Override
			public boolean visit(MethodInvocation invocation) {
				String invokedMethodName = invocation.getName().toString();
				if(isInvocatorOfOptionalType(invocation) && invokedMethodName.equals("isPresent")) {
					int startLineNumber = compilationUnit.getLineNumber(invocation.getStartPosition()) - 1;
					String fileName = iCompilationUnit.getElementName();
					group.add(Factory.getInstance().createMInvocation(invocation));

				}
				return super.visit( invocation );
			}
		});

		return group;
	}

	private static CompilationUnit parse(ICompilationUnit unit) {
		ASTParser parser = ASTParser.newParser(AST.JLS3);
		parser.setKind(ASTParser.K_COMPILATION_UNIT);
		parser.setSource(unit);
		parser.setResolveBindings(true);
		return (CompilationUnit) parser.createAST(null); // parse
	}




}
