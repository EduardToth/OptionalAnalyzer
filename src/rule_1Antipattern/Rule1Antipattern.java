package rule_1Antipattern;

import java.util.Optional;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.Assignment;
import org.eclipse.jdt.core.dom.VariableDeclarationFragment;

import utilities.ASTNodeDoesNotBelongHere;
import utilities.Antipattern;

public class Rule1Antipattern extends Antipattern{
	private Rule1Antipattern(ASTNode antipattern) throws ASTNodeDoesNotBelongHere{
		super(antipattern);
	}

	@Override
	protected boolean belongs(ASTNode antipattern) {
		return antipattern instanceof VariableDeclarationFragment ||
				antipattern instanceof Assignment;
	}


	public static Optional<Rule1Antipattern> getInstance(ASTNode antipattern) {
		Rule1Antipattern rule1Antipattern = null;
		try {
			rule1Antipattern =  new Rule1Antipattern(antipattern);
		} catch (ASTNodeDoesNotBelongHere e) {
			e.printStackTrace();
		}

		return Optional.ofNullable(rule1Antipattern);
	}

}
