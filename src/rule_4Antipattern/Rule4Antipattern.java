package rule_4Antipattern;

import java.util.Optional;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.IfStatement;
import org.eclipse.jdt.core.dom.ReturnStatement;

import rule_3Antipattern.Rule3Antipattern;
import utilities.ASTNodeDoesNotBelongHere;
import utilities.Antipattern;

public class Rule4Antipattern extends Antipattern {

	public Rule4Antipattern(ASTNode atom) throws ASTNodeDoesNotBelongHere {
		super(atom);
	}

	@Override
	protected boolean belongs(ASTNode atom) {
		return atom instanceof IfStatement ||
				atom instanceof ReturnStatement;
	}
	
	public static Optional<Rule4Antipattern> getInstance(ASTNode atom) {
		Rule4Antipattern instance = null;
		try {
			instance =  new Rule4Antipattern(atom);
		} catch (ASTNodeDoesNotBelongHere e) {
			e.printStackTrace();
		}
		
		return Optional.ofNullable(instance);
	}

}
