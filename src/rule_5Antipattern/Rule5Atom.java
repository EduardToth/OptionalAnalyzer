package rule_5Antipattern;

import java.util.Optional;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.IfStatement;
import utilities.ASTNodeDoesNotBelongHere;
import utilities.Atom;

public class Rule5Atom extends Atom {

	public Rule5Atom(ASTNode atom) throws ASTNodeDoesNotBelongHere {
		super(atom);
	}

	@Override
	protected boolean belongs(ASTNode atom) {
		
		return atom instanceof IfStatement;
	}
	
	public static Optional<Rule5Atom> getInstance(ASTNode atom) {
		Rule5Atom instance = null;
		try {
			instance =  new Rule5Atom(atom);
		} catch (ASTNodeDoesNotBelongHere e) {
			e.printStackTrace();
		}
		
		return Optional.ofNullable(instance);
	}

}
