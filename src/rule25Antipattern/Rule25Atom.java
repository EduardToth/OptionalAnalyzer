package rule25Antipattern;

import java.util.Optional;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.InfixExpression;

import rule21Antipattern.Rule21Atom;
import utilities.ASTNodeDoesNotBelongHere;
import utilities.Atom;

public class Rule25Atom extends Atom {

	public Rule25Atom(ASTNode wrappedElement) throws ASTNodeDoesNotBelongHere {
		super(wrappedElement);
	}

	@Override
	protected boolean belongs(ASTNode wrappedElement) {
		return wrappedElement instanceof InfixExpression;
	}
	
	public static Optional<Rule25Atom> getInstance(ASTNode atom) {
		Rule25Atom instance = null;
		try {
			instance =  new Rule25Atom(atom);
		} catch (ASTNodeDoesNotBelongHere e) {
			e.printStackTrace();
		}
		
		return Optional.ofNullable(instance);
	}

}
