package rule_3Antipattern;

import java.util.Optional;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.IfStatement;
import org.eclipse.jdt.core.dom.MethodInvocation;
import utilities.ASTNodeDoesNotBelongHere;
import utilities.Antipattern;

public class Rule3Antipattern extends Antipattern {
	

	private Rule3Antipattern(ASTNode antipattern) throws ASTNodeDoesNotBelongHere {
		super(antipattern);
	}

	@Override
	protected boolean belongs(ASTNode antipattern) {
		return antipattern instanceof IfStatement;
	}
	
	
	public static Optional<Rule3Antipattern> getInstance(ASTNode antipattern) {
		Rule3Antipattern instance = null;
		try {
			instance =  new Rule3Antipattern(antipattern);
		} catch (ASTNodeDoesNotBelongHere e) {
			e.printStackTrace();
		}
		
		return Optional.ofNullable(instance);
	}

}
