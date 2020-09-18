package rule_9Antipattern;

import java.util.Optional;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.IfStatement;

import utilities.ASTNodeDoesNotBelongHere;
import utilities.Atom;

public class Rule9Atom extends Atom {

	public Rule9Atom(ASTNode wrapppedElement) throws ASTNodeDoesNotBelongHere {
		super(wrapppedElement);
	}

	@Override
	protected boolean belongs(ASTNode wrapppedElement) {
		return wrapppedElement instanceof IfStatement;
	}
	
	public static Optional<Rule9Atom> getInstance(ASTNode wrapppedElement) {
		Rule9Atom instance = null;
		try {
			instance =  new Rule9Atom(wrapppedElement);
		} catch (ASTNodeDoesNotBelongHere e) {
			e.printStackTrace();
		}
		
		return Optional.ofNullable(instance);
	}

}
