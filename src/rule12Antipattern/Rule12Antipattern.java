package rule12Antipattern;

import java.util.Optional;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.MethodInvocation;

import rule_9Antipattern.Rule9Antipattern;
import utilities.ASTNodeDoesNotBelongHere;
import utilities.Antipattern;

public class Rule12Antipattern extends Antipattern {

	public Rule12Antipattern(ASTNode astNode) throws ASTNodeDoesNotBelongHere {
		super(astNode);
	}

	@Override
	protected boolean belongs(ASTNode astNode) {
		return astNode instanceof MethodInvocation;
	}
	
	public static Optional<Rule12Antipattern> getInstance(ASTNode astNode) {
		Rule12Antipattern instance = null;
		try {
			instance =  new Rule12Antipattern(astNode);
		} catch (ASTNodeDoesNotBelongHere e) {
			e.printStackTrace();
		}
		
		return Optional.ofNullable(instance);
	}
}
