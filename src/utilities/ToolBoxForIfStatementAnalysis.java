package utilities;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.Assignment;
import org.eclipse.jdt.core.dom.DoStatement;
import org.eclipse.jdt.core.dom.ForStatement;
import org.eclipse.jdt.core.dom.IfStatement;
import org.eclipse.jdt.core.dom.MethodInvocation;
import org.eclipse.jdt.core.dom.PostfixExpression;
import org.eclipse.jdt.core.dom.PrefixExpression;
import org.eclipse.jdt.core.dom.ReturnStatement;
import org.eclipse.jdt.core.dom.Statement;
import org.eclipse.jdt.core.dom.ThrowStatement;
import org.eclipse.jdt.core.dom.VariableDeclarationFragment;
import org.eclipse.jdt.core.dom.WhileStatement;

public class ToolBoxForIfStatementAnalysis {

	public static Optional<ReturnStatement> getReturnStatement(Statement statement) {
		final Unit<ReturnStatement> unit = new Unit<>(null);
		statement.accept(new ASTVisitor() {

			@Override
			public boolean visit(ReturnStatement returnStatement) {
				unit.setAt0(returnStatement);
				return super.visit(returnStatement);
			}
		});

		return Optional.ofNullable(unit.getValue0());
	}

	public static void setInvocatorName(MethodInvocation invocation, Unit<String> invocatorName) {
		invocatorName.setAt0( UtilityClass.getInvocatorName(invocation).orElse(""));
	}

	public static IfStatement getIfStatement(MethodInvocation methodInvocation) {
		ASTNode astNode = methodInvocation.getParent();

		if(astNode instanceof IfStatement) {
			return (IfStatement)astNode;
		}

		return (IfStatement)astNode.getParent();
	}

	public static boolean isSuperParentIfStatement(MethodInvocation methodInvocation) {
		return ToolBoxForIfStatementAnalysis.isParentIfStatement(methodInvocation) ||
				(ToolBoxForIfStatementAnalysis.isParentNotStatement(methodInvocation) &&
						ToolBoxForIfStatementAnalysis.isParentIfStatement(methodInvocation.getParent()));
	}

	private static boolean isParentIfStatement(ASTNode astNode) {
		return astNode.getParent() instanceof IfStatement;
	}

	public static boolean isParentNotStatement(MethodInvocation methodInvocation) {
		return methodInvocation.getParent() instanceof PrefixExpression &&
				((PrefixExpression)methodInvocation.getParent()).getOperator() == PrefixExpression.Operator.NOT;
	}

	public static String takeOutStatement(ASTNode context, ASTNode statement) {
		String stringContext = context.toString();
		String stringStatement = statement.toString();
		int startIndex = stringContext.indexOf(stringStatement);
		int endIndex = startIndex + stringStatement.length();

		return stringContext.substring(0, startIndex) + stringContext.substring(endIndex);
	}

	public static Optional<ThrowStatement> getThrowStatement(Statement statement) {
		final Unit<ThrowStatement> unit = new Unit<>(null);
		statement.accept(new ASTVisitor() {

			@Override
			public boolean visit(ThrowStatement throwStatement) {
				unit.setAt0(throwStatement);
				return super.visit(throwStatement);
			}
		});

		return Optional.ofNullable(unit.getValue0());
	}

	public static boolean containsGetFromOptional(ASTNode astNode, String invocatorName) {
		VerifiedGetVisitor verifiedGetVisitor = new VerifiedGetVisitor(invocatorName);
		astNode.accept(verifiedGetVisitor);
		return verifiedGetVisitor.properGetInvocationFound();
	}

	public static boolean statementDoesNotContainNonConsumerElements(Statement statement) {
		/*
		 * Does not contain return statements, throw statements or assignment to outer variables
		 */
		return getReturnStatement(statement).isEmpty() && 
				getThrowStatement(statement).isEmpty() &&
				!thereAreModifiersToOuterVariables(statement);
	}

	public static boolean statementDoesNotContainNonConsumerElementsExceptReturnStatements(Statement statement) {
		/*
		 * Does not contain return statements, throw statements or assignment to outer variables
		 */
		return 
				getThrowStatement(statement).isEmpty() &&
				!thereAreModifiersToOuterVariables(statement);
	}

	private static boolean thereAreModifiersToOuterVariables(Statement statement) {
		final List<String> variableNames = getAllDeclaredvariablesInside(statement);
		Unit<Boolean> thereAre = new Unit<>(false);

		statement.accept(new ASTVisitor() {
			@Override
			public boolean visit(Assignment assignment) {
				String leftHandSideVariable = assignment.getLeftHandSide().toString();
				if(!variableNames.contains(leftHandSideVariable)) {
					thereAre.setAt0(true);
				}
				return super.visit(assignment);
			}

			@Override
			public boolean visit(PrefixExpression prefixExpression) {

				PrefixExpression.Operator operator = prefixExpression.getOperator();
				boolean thereIsAProblem = (operator == PrefixExpression.Operator.INCREMENT ||
						operator == PrefixExpression.Operator.DECREMENT) &&
						!variableNames.contains(prefixExpression.getOperand().toString());
				if(thereIsAProblem) {
					thereAre.setAt0(true);
				}
				return super.visit(prefixExpression);
			}

			@Override
			public boolean visit(PostfixExpression postfixExpression) {
				PostfixExpression.Operator operator = postfixExpression.getOperator();
				boolean thereIsAProblem = (operator == PostfixExpression.Operator.INCREMENT ||
						operator == PostfixExpression.Operator.DECREMENT) &&
						!variableNames.contains(postfixExpression.getOperand().toString());
				if(thereIsAProblem) {
					thereAre.setAt0(true);
				}
				return super.visit(postfixExpression);
			}
		});
		return thereAre.getValue0();
	}

	private static List<String> getAllDeclaredvariablesInside(Statement statement) {

		final List<String> variableNames = new LinkedList<>();

		statement.accept(new ASTVisitor() {
			@Override
			public boolean visit(VariableDeclarationFragment variableDeclarationFragment) {
				variableNames.add(variableDeclarationFragment.getName().toString());
				return super.visit(variableDeclarationFragment);
			}
		});
		return variableNames;
	}

	public static String removeWhiteSpaces(String str) {

		String result = "";
		for(int i=0; i < str.length(); i++) {
			final char ch = str.charAt( i );
			if(!Character.isWhitespace( ch ) ) {
				result += ch;
			}
		}
		return result;
	}

	public static boolean isEmptyStatement(Statement statement) {
		String stringForm = statement.toString();

		stringForm = removeWhiteSpaces(stringForm);

		return stringForm.equals("{}");
	}

	public static boolean containsMethodInvocation(ReturnStatement returnStatement) {
		String stringExpression = returnStatement.getExpression().toString();
		return stringExpression.matches(".*\\(.*\\).*");
	}

	public static int getCyclomaticComplexity(Statement statement) {
		AtomicInteger cyclomaticComplexity = new AtomicInteger(1);

		statement.accept(new ASTVisitor() {

			public boolean visit(IfStatement ifStatement) {
				cyclomaticComplexity.incrementAndGet();
				return super.visit(ifStatement);
			}

			public boolean visit(ForStatement forStatement) {
				cyclomaticComplexity.incrementAndGet();
				return super.visit(forStatement);
			}

			public boolean visit(WhileStatement whileStatement) {
				cyclomaticComplexity.incrementAndGet();
				return super.visit(whileStatement);
			}

			public boolean visit(DoStatement doStatement) {
				cyclomaticComplexity.incrementAndGet();
				return super.visit(doStatement);
			}
		});

		return cyclomaticComplexity.get();
	}

	public static boolean isStatementComposedByASimgleAction(Statement statement) {
		String str = statement.toString();
		str = removeStringFromInside(str);
		return str.indexOf(";") == str.lastIndexOf(";");
	}

	private static String removeStringFromInside(String str) {
		String regEx = "\".*\"";
		return Arrays.asList(str.split(regEx))
				.stream()
				.reduce((str1, str2) -> str1 + str2)
				.orElse("");
	}
}
