package rule_6Antipattern;

import java.util.Optional;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.IfStatement;

import utilities.ASTNodeDoesNotBelongHere;
import utilities.Antipattern;

public class Rule6Antipattern extends Antipattern {

	public Rule6Antipattern(ASTNode astNode) throws ASTNodeDoesNotBelongHere {
		super(astNode);
	}

	@Override
	protected boolean belongs(ASTNode astNode) {
		return astNode instanceof IfStatement;
	}
	
	public static Optional<Rule6Antipattern> getInstance(ASTNode astNode) {
		Rule6Antipattern instance = null;
		try {
			instance =  new Rule6Antipattern(astNode);
		} catch (ASTNodeDoesNotBelongHere e) {
			e.printStackTrace();
		}
		
		return Optional.ofNullable(instance);
	}
}
