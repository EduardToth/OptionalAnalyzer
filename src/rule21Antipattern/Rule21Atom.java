package rule21Antipattern;

import java.util.Optional;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.MethodInvocation;

import utilities.ASTNodeDoesNotBelongHere;
import utilities.Atom;

public class Rule21Atom extends Atom{

	public Rule21Atom(ASTNode wrappedElement) throws ASTNodeDoesNotBelongHere {
		super(wrappedElement);
	}
	
	

	@Override
	protected boolean belongs(ASTNode wrappedElement) {
		return wrappedElement instanceof MethodInvocation;
	}
	
	public static Optional<Rule21Atom> getInstance(ASTNode atom) {
		Rule21Atom instance = null;
		try {
			instance =  new Rule21Atom(atom);
		} catch (ASTNodeDoesNotBelongHere e) {
			e.printStackTrace();
		}
		
		return Optional.ofNullable(instance);
	}

}
