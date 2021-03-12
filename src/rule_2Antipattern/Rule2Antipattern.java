package rule_2Antipattern;

import java.util.Optional;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.MethodInvocation;

import utilities.ASTNodeDoesNotBelongHere;
import utilities.Antipattern;

public class Rule2Antipattern extends Antipattern{
	private Rule2Antipattern(ASTNode astNode) throws ASTNodeDoesNotBelongHere{
		super(astNode);
	}

	@Override
	protected boolean belongs(ASTNode astNode) {
		return astNode instanceof MethodInvocation;
	}
	
	
	public static Optional<Rule2Antipattern> getInstance(ASTNode astNode) {
		Rule2Antipattern rule2Antipattern = null;
		try {
			rule2Antipattern =  new Rule2Antipattern(astNode);
		} catch (ASTNodeDoesNotBelongHere e) {
			e.printStackTrace();
		}
		
		return Optional.ofNullable(rule2Antipattern);
	}
}