package rule_7Antipattern;

import java.util.Optional;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.IfStatement;

import utilities.ASTNodeDoesNotBelongHere;
import utilities.Antipattern;

public class Rule7Antipattern extends Antipattern {

	public Rule7Antipattern(ASTNode astNode) throws ASTNodeDoesNotBelongHere {
		super(astNode);
		
	}

	@Override
	protected boolean belongs(ASTNode astNode) {
		return astNode instanceof IfStatement;
	}
	
	public static Optional<Rule7Antipattern> getInstance(ASTNode astNode) {
		Rule7Antipattern instance = null;
		try {
			instance =  new Rule7Antipattern(astNode);
		} catch (ASTNodeDoesNotBelongHere e) {
			e.printStackTrace();
		}
		
		return Optional.ofNullable(instance);
	}

}
