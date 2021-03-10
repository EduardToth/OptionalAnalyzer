package rule_9Antipattern;

import java.util.Optional;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.IfStatement;

import utilities.ASTNodeDoesNotBelongHere;
import utilities.Antipattern;

public class Rule9Antipattern extends Antipattern {

	public Rule9Antipattern(ASTNode wrapppedElement) throws ASTNodeDoesNotBelongHere {
		super(wrapppedElement);
	}

	@Override
	protected boolean belongs(ASTNode wrapppedElement) {
		return wrapppedElement instanceof IfStatement;
	}
	
	public static Optional<Rule9Antipattern> getInstance(ASTNode wrapppedElement) {
		Rule9Antipattern instance = null;
		try {
			instance =  new Rule9Antipattern(wrapppedElement);
		} catch (ASTNodeDoesNotBelongHere e) {
			e.printStackTrace();
		}
		
		return Optional.ofNullable(instance);
	}

}
