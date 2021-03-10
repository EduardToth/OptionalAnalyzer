package rule_1Antipattern;

import java.util.Optional;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.Assignment;
import org.eclipse.jdt.core.dom.VariableDeclarationFragment;

import utilities.ASTNodeDoesNotBelongHere;
import utilities.Antipattern;

public class Rule1Antipattern extends Antipattern{
	private Rule1Antipattern(ASTNode atom) throws ASTNodeDoesNotBelongHere{
		super(atom);
	}

	@Override
	protected boolean belongs(ASTNode atom) {
		return atom instanceof VariableDeclarationFragment ||
				atom instanceof Assignment;
	}


	public static Optional<Rule1Antipattern> getInstance(ASTNode atom) {
		Rule1Antipattern rule1Atom = null;
		try {
			rule1Atom =  new Rule1Antipattern(atom);
		} catch (ASTNodeDoesNotBelongHere e) {
			e.printStackTrace();
		}

		return Optional.ofNullable(rule1Atom);
	}

}
