package rule26Antipattern;

import java.util.Optional;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.PrefixExpression;

import utilities.ASTNodeDoesNotBelongHere;
import utilities.Antipattern;

public class Rule26Antipattern extends Antipattern {

	public Rule26Antipattern(ASTNode wrappedElement) throws ASTNodeDoesNotBelongHere {
		super(wrappedElement);
	}

	@Override
	protected boolean belongs(ASTNode astNode) {
		return astNode instanceof PrefixExpression;
	}
	
	public static Optional<Rule26Antipattern> getInstance(ASTNode astNode) {
		Rule26Antipattern instance = null;
		try {
			instance =  new Rule26Antipattern(astNode);
		} catch (ASTNodeDoesNotBelongHere e) {
			e.printStackTrace();
		}
		
		return Optional.ofNullable(instance);
	}

}
