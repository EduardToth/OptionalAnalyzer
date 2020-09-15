package rule_7Antipattern;

import java.util.Optional;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.IfStatement;

import utilities.ASTNodeDoesNotBelongHere;
import utilities.Atom;

public class Rule7Atom extends Atom {

	public Rule7Atom(ASTNode atom) throws ASTNodeDoesNotBelongHere {
		super(atom);
		
	}

	@Override
	protected boolean belongs(ASTNode atom) {
		return atom instanceof IfStatement;
	}
	
	public static Optional<Rule7Atom> getInstance(ASTNode atom) {
		Rule7Atom instance = null;
		try {
			instance =  new Rule7Atom(atom);
		} catch (ASTNodeDoesNotBelongHere e) {
			e.printStackTrace();
		}
		
		return Optional.ofNullable(instance);
	}

}
