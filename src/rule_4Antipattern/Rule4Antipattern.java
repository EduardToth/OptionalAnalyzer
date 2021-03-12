package rule_4Antipattern;

import java.util.Optional;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.IfStatement;
import org.eclipse.jdt.core.dom.ReturnStatement;

import rule_3Antipattern.Rule3Antipattern;
import utilities.ASTNodeDoesNotBelongHere;
import utilities.Antipattern;

public class Rule4Antipattern extends Antipattern {

	public Rule4Antipattern(ASTNode astNode) throws ASTNodeDoesNotBelongHere {
		super(astNode);
	}

	@Override
	protected boolean belongs(ASTNode astNode) {
		return astNode instanceof IfStatement ||
				astNode instanceof ReturnStatement;
	}
	
	public static Optional<Rule4Antipattern> getInstance(ASTNode astNode) {
		Rule4Antipattern instance = null;
		try {
			instance =  new Rule4Antipattern(astNode);
		} catch (ASTNodeDoesNotBelongHere e) {
			e.printStackTrace();
		}
		
		return Optional.ofNullable(instance);
	}

}
