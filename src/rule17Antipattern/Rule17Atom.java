package rule17Antipattern;

import java.util.Optional;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.MethodDeclaration;

import utilities.ASTNodeDoesNotBelongHere;
import utilities.Atom;

public class Rule17Atom extends Atom {


	public Rule17Atom(ASTNode atom) throws ASTNodeDoesNotBelongHere {
		super(atom);

	}

	@Override
	protected boolean belongs(ASTNode atom) {
		return atom instanceof MethodDeclaration;
	}
	
	public static Optional<Rule17Atom> getInstance(ASTNode atom) {
		Rule17Atom instance = null;
		try {
			instance =  new Rule17Atom(atom);
		} catch (ASTNodeDoesNotBelongHere e) {
			e.printStackTrace();
		}
		
		return Optional.ofNullable(instance);
	}

}
