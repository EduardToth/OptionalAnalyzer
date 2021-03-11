package rule_6Antipattern;

import java.util.Optional;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.IfStatement;

import utilities.ASTNodeDoesNotBelongHere;
import utilities.Antipattern;

public class Rule6Antipattern extends Antipattern {

	public Rule6Antipattern(ASTNode antipattern) throws ASTNodeDoesNotBelongHere {
		super(antipattern);
	}

	@Override
	protected boolean belongs(ASTNode antipattern) {
		return antipattern instanceof IfStatement;
	}
	
	public static Optional<Rule6Antipattern> getInstance(ASTNode antipattern) {
		Rule6Antipattern instance = null;
		try {
			instance =  new Rule6Antipattern(antipattern);
		} catch (ASTNodeDoesNotBelongHere e) {
			e.printStackTrace();
		}
		
		return Optional.ofNullable(instance);
	}
}
