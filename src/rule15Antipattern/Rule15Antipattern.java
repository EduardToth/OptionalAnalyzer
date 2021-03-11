package rule15Antipattern;

import java.util.Optional;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.SingleVariableDeclaration;

import utilities.ASTNodeDoesNotBelongHere;
import utilities.Antipattern;

public class Rule15Antipattern extends Antipattern{

	public Rule15Antipattern(ASTNode astNode) throws ASTNodeDoesNotBelongHere {
		super(astNode);
	}

	@Override
	protected boolean belongs(ASTNode astNode) {
		return astNode instanceof SingleVariableDeclaration;
	}
	
	
	
	public static Optional<Rule15Antipattern> getInstance(ASTNode astNode) {
		Rule15Antipattern instance = null;
		try {
			instance =  new Rule15Antipattern(astNode);
		} catch (ASTNodeDoesNotBelongHere e) {
			e.printStackTrace();
		}
		
		return Optional.ofNullable(instance);
	}
}
