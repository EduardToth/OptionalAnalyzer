package rule14Antipattern;

import java.util.Optional;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.SingleVariableDeclaration;

import utilities.ASTNodeDoesNotBelongHere;
import utilities.Antipattern;

public class Rule14Antipattern extends Antipattern {

	public Rule14Antipattern(ASTNode astNode) throws ASTNodeDoesNotBelongHere {
		super(astNode);
	}

	@Override
	protected boolean belongs(ASTNode astNode) {
		return astNode instanceof SingleVariableDeclaration;
	}
	
	public static Optional<Rule14Antipattern> getInstance(ASTNode astNode) {
		Rule14Antipattern instance = null;
		try {
			instance =  new Rule14Antipattern(astNode);
		} catch (ASTNodeDoesNotBelongHere e) {
			e.printStackTrace();
		}
		
		return Optional.ofNullable(instance);
	}

}
