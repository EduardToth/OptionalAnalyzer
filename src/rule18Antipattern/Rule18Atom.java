package rule18Antipattern;

import java.util.Optional;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.SimpleName;

import utilities.ASTNodeDoesNotBelongHere;
import utilities.Atom;

public class Rule18Atom extends Atom {

	public Rule18Atom(ASTNode atom) throws ASTNodeDoesNotBelongHere {
		super(atom);
	}

	@Override
	protected boolean belongs(ASTNode atom) {
		return atom instanceof SimpleName;
	}
	
	public static Optional<Rule18Atom> getInstance(ASTNode atom) {
		Rule18Atom instance = null;
		try {
			instance =  new Rule18Atom(atom);
		} catch (ASTNodeDoesNotBelongHere e) {
			e.printStackTrace();
		}
		
		return Optional.ofNullable(instance);
	}
}
