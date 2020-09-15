package rule_2Antipattern;

import java.util.Optional;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.MethodInvocation;

import utilities.ASTNodeDoesNotBelongHere;
import utilities.Atom;

public class Rule2Atom extends Atom{
	private Rule2Atom(ASTNode atom) throws ASTNodeDoesNotBelongHere{
		super(atom);
	}

	@Override
	protected boolean belongs(ASTNode atom) {
		return atom instanceof MethodInvocation;
	}
	
	
	public static Optional<Rule2Atom> getInstance(ASTNode atom) {
		Rule2Atom rule2Atom = null;
		try {
			rule2Atom =  new Rule2Atom(atom);
		} catch (ASTNodeDoesNotBelongHere e) {
			e.printStackTrace();
		}
		
		return Optional.ofNullable(rule2Atom);
	}
}