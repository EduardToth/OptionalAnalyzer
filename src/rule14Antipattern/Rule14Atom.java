package rule14Antipattern;

import java.util.Optional;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.SingleVariableDeclaration;

import utilities.ASTNodeDoesNotBelongHere;
import utilities.Atom;

public class Rule14Atom extends Atom {

	public Rule14Atom(ASTNode atom) throws ASTNodeDoesNotBelongHere {
		super(atom);
	}

	@Override
	protected boolean belongs(ASTNode atom) {
		return atom instanceof SingleVariableDeclaration;
	}
	
	public static Optional<Rule14Atom> getInstance(ASTNode atom) {
		Rule14Atom instance = null;
		try {
			instance =  new Rule14Atom(atom);
		} catch (ASTNodeDoesNotBelongHere e) {
			e.printStackTrace();
		}
		
		return Optional.ofNullable(instance);
	}

}
