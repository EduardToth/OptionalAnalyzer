package rule_8Antipattern;

import java.util.Optional;
import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.IfStatement;

import utilities.ASTNodeDoesNotBelongHere;
import utilities.Antipattern;

public class Rule8Antipattern extends Antipattern {

	public Rule8Antipattern(ASTNode antipattern) throws ASTNodeDoesNotBelongHere {
		super(antipattern);
	}

	@Override
	protected boolean belongs(ASTNode antipattern) {
		return antipattern instanceof IfStatement;
	}
	
	public static Optional<Rule8Antipattern> getInstance(ASTNode antipattern) {
		Rule8Antipattern instance = null;
		try {
			instance =  new Rule8Antipattern(antipattern);
		} catch (ASTNodeDoesNotBelongHere e) {
			e.printStackTrace();
		}
		
		return Optional.ofNullable(instance);
	}
}
