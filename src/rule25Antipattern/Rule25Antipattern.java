package rule25Antipattern;

import java.util.Optional;
import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.InfixExpression;
import utilities.ASTNodeDoesNotBelongHere;
import utilities.Antipattern;

public class Rule25Antipattern extends Antipattern {

	public Rule25Antipattern(ASTNode wrappedElement) throws ASTNodeDoesNotBelongHere {
		super(wrappedElement);
	}

	@Override
	protected boolean belongs(ASTNode wrappedElement) {
		return wrappedElement instanceof InfixExpression;
	}
	
	public static Optional<Rule25Antipattern> getInstance(ASTNode atom) {
		Rule25Antipattern instance = null;
		try {
			instance =  new Rule25Antipattern(atom);
		} catch (ASTNodeDoesNotBelongHere e) {
			e.printStackTrace();
		}
		
		return Optional.ofNullable(instance);
	}
}
