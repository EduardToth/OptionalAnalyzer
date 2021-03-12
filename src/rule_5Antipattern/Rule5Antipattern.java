package rule_5Antipattern;

import java.util.Optional;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.IfStatement;
import utilities.ASTNodeDoesNotBelongHere;
import utilities.Antipattern;

public class Rule5Antipattern extends Antipattern {

	public Rule5Antipattern(ASTNode astNode) throws ASTNodeDoesNotBelongHere {
		super(astNode);
	}

	@Override
	protected boolean belongs(ASTNode astNode) {
		
		return astNode instanceof IfStatement;
	}
	
	public static Optional<Rule5Antipattern> getInstance(ASTNode astNode) {
		Rule5Antipattern instance = null;
		try {
			instance =  new Rule5Antipattern(astNode);
		} catch (ASTNodeDoesNotBelongHere e) {
			e.printStackTrace();
		}
		
		return Optional.ofNullable(instance);
	}

}
