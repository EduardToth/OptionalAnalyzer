package rule_8Antipattern;

import java.util.Optional;
import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.IfStatement;

import utilities.ASTNodeDoesNotBelongHere;
import utilities.Atom;

public class Rule8Atom extends Atom {

	public Rule8Atom(ASTNode atom) throws ASTNodeDoesNotBelongHere {
		super(atom);
	}

	@Override
	protected boolean belongs(ASTNode atom) {
		return atom instanceof IfStatement;
	}
	
	public static Optional<Rule8Atom> getInstance(ASTNode atom) {
		Rule8Atom instance = null;
		try {
			instance =  new Rule8Atom(atom);
		} catch (ASTNodeDoesNotBelongHere e) {
			e.printStackTrace();
		}
		
		return Optional.ofNullable(instance);
	}
}
