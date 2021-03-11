package rule_7Antipattern;

import java.util.Optional;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.IfStatement;

import utilities.ASTNodeDoesNotBelongHere;
import utilities.Antipattern;

public class Rule7Antipattern extends Antipattern {

	public Rule7Antipattern(ASTNode antipattern) throws ASTNodeDoesNotBelongHere {
		super(antipattern);
		
	}

	@Override
	protected boolean belongs(ASTNode antipattern) {
		return antipattern instanceof IfStatement;
	}
	
	public static Optional<Rule7Antipattern> getInstance(ASTNode antipattern) {
		Rule7Antipattern instance = null;
		try {
			instance =  new Rule7Antipattern(antipattern);
		} catch (ASTNodeDoesNotBelongHere e) {
			e.printStackTrace();
		}
		
		return Optional.ofNullable(instance);
	}

}
