package rule13Antipattern;

import java.util.Optional;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.FieldDeclaration;

import utilities.ASTNodeDoesNotBelongHere;
import utilities.Atom;

public class Rule13Atom extends Atom {

	public Rule13Atom(ASTNode atom) throws ASTNodeDoesNotBelongHere {
		super(atom);
	}

	@Override
	protected boolean belongs(ASTNode atom) {
		return atom instanceof FieldDeclaration;
	}
	
	public static Optional<Rule13Atom> getInstance(ASTNode atom) {
		Rule13Atom instance = null;
		try {
			instance =  new Rule13Atom(atom);
		} catch (ASTNodeDoesNotBelongHere e) {
			e.printStackTrace();
		}
		
		return Optional.ofNullable(instance);
	}
}
