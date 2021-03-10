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
	protected boolean belongs(ASTNode atom) {
		return atom instanceof PrefixExpression;
	}
	
	public static Optional<Rule26Antipattern> getInstance(ASTNode atom) {
		Rule26Antipattern instance = null;
		try {
			instance =  new Rule26Antipattern(atom);
		} catch (ASTNodeDoesNotBelongHere e) {
			e.printStackTrace();
		}
		
		return Optional.ofNullable(instance);
	}

}
