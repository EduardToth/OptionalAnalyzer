package rule_5Antipattern;

import java.util.Optional;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.IfStatement;
import utilities.ASTNodeDoesNotBelongHere;
import utilities.Antipattern;

public class Rule5Antipattern extends Antipattern {

	public Rule5Antipattern(ASTNode antipattern) throws ASTNodeDoesNotBelongHere {
		super(antipattern);
	}

	@Override
	protected boolean belongs(ASTNode antipattern) {
		
		return antipattern instanceof IfStatement;
	}
	
	public static Optional<Rule5Antipattern> getInstance(ASTNode antipattern) {
		Rule5Antipattern instance = null;
		try {
			instance =  new Rule5Antipattern(antipattern);
		} catch (ASTNodeDoesNotBelongHere e) {
			e.printStackTrace();
		}
		
		return Optional.ofNullable(instance);
	}

}
