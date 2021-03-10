package rule14Antipattern;

import java.util.Optional;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.SingleVariableDeclaration;

import utilities.ASTNodeDoesNotBelongHere;
import utilities.Antipattern;

public class Rule14Antipattern extends Antipattern {

	public Rule14Antipattern(ASTNode atom) throws ASTNodeDoesNotBelongHere {
		super(atom);
	}

	@Override
	protected boolean belongs(ASTNode atom) {
		return atom instanceof SingleVariableDeclaration;
	}
	
	public static Optional<Rule14Antipattern> getInstance(ASTNode atom) {
		Rule14Antipattern instance = null;
		try {
			instance =  new Rule14Antipattern(atom);
		} catch (ASTNodeDoesNotBelongHere e) {
			e.printStackTrace();
		}
		
		return Optional.ofNullable(instance);
	}

}
