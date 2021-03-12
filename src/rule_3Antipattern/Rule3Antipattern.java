package rule_3Antipattern;

import java.util.Optional;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.IfStatement;
import org.eclipse.jdt.core.dom.MethodInvocation;
import utilities.ASTNodeDoesNotBelongHere;
import utilities.Antipattern;

public class Rule3Antipattern extends Antipattern {
	

	private Rule3Antipattern(ASTNode astNode) throws ASTNodeDoesNotBelongHere {
		super(astNode);
	}

	@Override
	protected boolean belongs(ASTNode astNode) {
		return astNode instanceof IfStatement;
	}
	
	
	public static Optional<Rule3Antipattern> getInstance(ASTNode astNode) {
		Rule3Antipattern instance = null;
		try {
			instance =  new Rule3Antipattern(astNode);
		} catch (ASTNodeDoesNotBelongHere e) {
			e.printStackTrace();
		}
		
		return Optional.ofNullable(instance);
	}

}
