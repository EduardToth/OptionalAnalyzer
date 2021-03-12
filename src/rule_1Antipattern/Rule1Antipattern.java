package rule_1Antipattern;

import java.util.Optional;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.Assignment;
import org.eclipse.jdt.core.dom.VariableDeclarationFragment;

import utilities.ASTNodeDoesNotBelongHere;
import utilities.Antipattern;

public class Rule1Antipattern extends Antipattern{
	private Rule1Antipattern(ASTNode astNode) throws ASTNodeDoesNotBelongHere{
		super(astNode);
	}

	@Override
	protected boolean belongs(ASTNode astNode) {
		return astNode instanceof VariableDeclarationFragment ||
				astNode instanceof Assignment;
	}


	public static Optional<Rule1Antipattern> getInstance(ASTNode astNode) {
		Rule1Antipattern rule1Antipattern = null;
		try {
			rule1Antipattern =  new Rule1Antipattern(astNode);
		} catch (ASTNodeDoesNotBelongHere e) {
			e.printStackTrace();
		}

		return Optional.ofNullable(rule1Antipattern);
	}

}
