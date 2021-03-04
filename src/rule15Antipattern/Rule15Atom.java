package rule15Antipattern;

import java.util.Optional;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.SingleVariableDeclaration;

import utilities.ASTNodeDoesNotBelongHere;
import utilities.Atom;

public class Rule15Atom extends Atom{

	public Rule15Atom(ASTNode atom) throws ASTNodeDoesNotBelongHere {
		super(atom);
	}

	@Override
	protected boolean belongs(ASTNode atom) {
		return atom instanceof SingleVariableDeclaration;
	}
	
	
	
	public static Optional<Rule15Atom> getInstance(ASTNode atom) {
		Rule15Atom instance = null;
		try {
			instance =  new Rule15Atom(atom);
		} catch (ASTNodeDoesNotBelongHere e) {
			e.printStackTrace();
		}
		
		return Optional.ofNullable(instance);
	}
}
