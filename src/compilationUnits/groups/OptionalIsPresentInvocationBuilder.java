package compilationUnits.groups;

import java.util.Arrays;

import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.MethodInvocation;
import optionalanalizer.metamodel.factory.*;

import optionalanalizer.metamodel.entity.MCompilationUnit;
import optionalanalizer.metamodel.entity.MInvocation;
import ro.lrg.xcore.metametamodel.Group;
import ro.lrg.xcore.metametamodel.IRelationBuilder;
import ro.lrg.xcore.metametamodel.RelationBuilder;
import utilities.UtilityClass;

@RelationBuilder
public class OptionalIsPresentInvocationBuilder implements IRelationBuilder<MInvocation, MCompilationUnit>{

	@Override
	public Group<MInvocation> buildGroup(MCompilationUnit arg0) {
		ICompilationUnit iCompilationUnit = (ICompilationUnit)arg0.getUnderlyingObject();
		Group<MInvocation> group = new Group<>();

		CompilationUnit compilationUnit = parse(iCompilationUnit);



		compilationUnit.accept(new ASTVisitor() {

			@Override
			public boolean visit(MethodInvocation invocation) {
				String invokedMethodName = invocation.getName().toString();
				if(invocation.getExpression() != null &&
						UtilityClass.isInvocatorOfOptionalType(invocation.getExpression()
								.resolveTypeBinding().getQualifiedName())
						&& invokedMethodName.equals("isPresent")) {
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
		return (CompilationUnit) parser.createAST(null);
	}


}
