package rule_1Antipattern;

import java.util.Optional;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.Assignment;
import org.eclipse.jdt.core.dom.MethodInvocation;
import org.eclipse.jdt.core.dom.VariableDeclarationFragment;

import rule_2Antipattern.Rule2Atom;
import utilities.ASTNodeDoesNotBelongHere;
import utilities.Atom;

public class Rule1Atom extends Atom{
	private Rule1Atom(ASTNode atom) throws ASTNodeDoesNotBelongHere{
		super(atom);
	}

	@Override
	protected boolean belongs(ASTNode atom) {
		return atom instanceof VariableDeclarationFragment ||
				atom instanceof Assignment;
	}


	public static Optional<Rule1Atom> getInstance(ASTNode atom) {
		Rule1Atom rule1Atom = null;
		try {
			rule1Atom =  new Rule1Atom(atom);
		} catch (ASTNodeDoesNotBelongHere e) {
			e.printStackTrace();
		}

		return Optional.ofNullable(rule1Atom);
	}

}
