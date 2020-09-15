package rule26Antipattern;

import java.util.Optional;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.PrefixExpression;

import utilities.ASTNodeDoesNotBelongHere;
import utilities.Atom;

public class Rule26Atom extends Atom {

	public Rule26Atom(ASTNode wrappedElement) throws ASTNodeDoesNotBelongHere {
		super(wrappedElement);
	}

	@Override
	protected boolean belongs(ASTNode atom) {
		return atom instanceof PrefixExpression;
	}
	
	public static Optional<Rule26Atom> getInstance(ASTNode atom) {
		Rule26Atom instance = null;
		try {
			instance =  new Rule26Atom(atom);
		} catch (ASTNodeDoesNotBelongHere e) {
			e.printStackTrace();
		}
		
		return Optional.ofNullable(instance);
	}

}
