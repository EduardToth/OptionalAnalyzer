package rule_3Antipattern;

import java.util.Optional;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.IfStatement;
import org.eclipse.jdt.core.dom.MethodInvocation;
import utilities.ASTNodeDoesNotBelongHere;
import utilities.Atom;

public class Rule3Atom extends Atom {

	private Rule3Atom(ASTNode atom) throws ASTNodeDoesNotBelongHere {
		super(atom);
	}

	@Override
	protected boolean belongs(ASTNode atom) {
		return atom instanceof IfStatement;
	}
	
	
	public static Optional<Rule3Atom> getInstance(ASTNode atom) {
		Rule3Atom instance = null;
		try {
			instance =  new Rule3Atom(atom);
		} catch (ASTNodeDoesNotBelongHere e) {
			e.printStackTrace();
		}
		
		return Optional.ofNullable(instance);
	}

}
