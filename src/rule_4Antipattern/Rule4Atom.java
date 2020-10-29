package rule_4Antipattern;

import java.util.Optional;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.IfStatement;
import org.eclipse.jdt.core.dom.ReturnStatement;

import rule_3Antipattern.Rule3Atom;
import utilities.ASTNodeDoesNotBelongHere;
import utilities.Atom;

public class Rule4Atom extends Atom {

	public Rule4Atom(ASTNode atom) throws ASTNodeDoesNotBelongHere {
		super(atom);
	}

	@Override
	protected boolean belongs(ASTNode atom) {
		return atom instanceof IfStatement ||
				atom instanceof ReturnStatement;
	}
	
	public static Optional<Rule4Atom> getInstance(ASTNode atom) {
		Rule4Atom instance = null;
		try {
			instance =  new Rule4Atom(atom);
		} catch (ASTNodeDoesNotBelongHere e) {
			e.printStackTrace();
		}
		
		return Optional.ofNullable(instance);
	}

}
