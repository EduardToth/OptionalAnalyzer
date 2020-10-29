package rule_6Antipattern;

import java.util.Optional;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.IfStatement;

import utilities.ASTNodeDoesNotBelongHere;
import utilities.Atom;

public class Rule6Atom extends Atom {

	public Rule6Atom(ASTNode atom) throws ASTNodeDoesNotBelongHere {
		super(atom);
	}

	@Override
	protected boolean belongs(ASTNode atom) {
		return atom instanceof IfStatement;
	}
	
	public static Optional<Rule6Atom> getInstance(ASTNode atom) {
		Rule6Atom instance = null;
		try {
			instance =  new Rule6Atom(atom);
		} catch (ASTNodeDoesNotBelongHere e) {
			e.printStackTrace();
		}
		
		return Optional.ofNullable(instance);
	}
}
