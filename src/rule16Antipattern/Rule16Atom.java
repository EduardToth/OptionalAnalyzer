package rule16Antipattern;

import java.util.Optional;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.SingleVariableDeclaration;

import utilities.ASTNodeDoesNotBelongHere;
import utilities.Atom;

public class Rule16Atom extends Atom {

	public Rule16Atom(ASTNode atom) throws ASTNodeDoesNotBelongHere {
		super(atom);
	}

	@Override
	protected boolean belongs(ASTNode atom) {
		return atom instanceof SingleVariableDeclaration;
	}
	
	public static Optional<Rule16Atom> getInstance(ASTNode atom) {
		Rule16Atom instance = null;
		try {
			instance =  new Rule16Atom(atom);
		} catch (ASTNodeDoesNotBelongHere e) {
			e.printStackTrace();
		}
		
		return Optional.ofNullable(instance);
	}
}
