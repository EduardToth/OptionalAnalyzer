package rule_2Antipattern;

import java.util.Optional;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.MethodInvocation;

import utilities.ASTNodeDoesNotBelongHere;
import utilities.Antipattern;

public class Rule2Antipattern extends Antipattern{
	private Rule2Antipattern(ASTNode atom) throws ASTNodeDoesNotBelongHere{
		super(atom);
	}

	@Override
	protected boolean belongs(ASTNode atom) {
		return atom instanceof MethodInvocation;
	}
	
	
	public static Optional<Rule2Antipattern> getInstance(ASTNode atom) {
		Rule2Antipattern rule2Atom = null;
		try {
			rule2Atom =  new Rule2Antipattern(atom);
		} catch (ASTNodeDoesNotBelongHere e) {
			e.printStackTrace();
		}
		
		return Optional.ofNullable(rule2Atom);
	}
}