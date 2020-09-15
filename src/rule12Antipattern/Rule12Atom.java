package rule12Antipattern;

import java.util.Optional;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.MethodInvocation;

import rule_9Antipattern.Rule9Atom;
import utilities.ASTNodeDoesNotBelongHere;
import utilities.Atom;

public class Rule12Atom extends Atom {

	public Rule12Atom(ASTNode atom) throws ASTNodeDoesNotBelongHere {
		super(atom);
	}

	@Override
	protected boolean belongs(ASTNode atom) {
		return atom instanceof MethodInvocation;
	}
	
	public static Optional<Rule12Atom> getInstance(ASTNode atom) {
		Rule12Atom instance = null;
		try {
			instance =  new Rule12Atom(atom);
		} catch (ASTNodeDoesNotBelongHere e) {
			e.printStackTrace();
		}
		
		return Optional.ofNullable(instance);
	}

}
