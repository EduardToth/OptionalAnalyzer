package rule12Antipattern;

import java.util.Optional;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.MethodInvocation;

import rule_9Antipattern.Rule9Antipattern;
import utilities.ASTNodeDoesNotBelongHere;
import utilities.Antipattern;

public class Rule12Antipattern extends Antipattern {

	public Rule12Antipattern(ASTNode atom) throws ASTNodeDoesNotBelongHere {
		super(atom);
	}

	@Override
	protected boolean belongs(ASTNode atom) {
		return atom instanceof MethodInvocation;
	}
	
	public static Optional<Rule12Antipattern> getInstance(ASTNode atom) {
		Rule12Antipattern instance = null;
		try {
			instance =  new Rule12Antipattern(atom);
		} catch (ASTNodeDoesNotBelongHere e) {
			e.printStackTrace();
		}
		
		return Optional.ofNullable(instance);
	}
}
