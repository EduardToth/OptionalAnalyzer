package rule16Antipattern;

import java.util.Optional;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.SingleVariableDeclaration;

import utilities.ASTNodeDoesNotBelongHere;
import utilities.Antipattern;

public class Rule16Antipattern extends Antipattern {

	public Rule16Antipattern(ASTNode astNode) throws ASTNodeDoesNotBelongHere {
		super(astNode);
	}

	@Override
	protected boolean belongs(ASTNode astNode) {
		return astNode instanceof SingleVariableDeclaration;
	}
	
	public static Optional<Rule16Antipattern> getInstance(ASTNode astNode) {
		Rule16Antipattern instance = null;
		try {
			instance =  new Rule16Antipattern(astNode);
		} catch (ASTNodeDoesNotBelongHere e) {
			e.printStackTrace();
		}
		
		return Optional.ofNullable(instance);
	}
}
