package rule17Antipattern;

import java.util.Optional;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.MethodDeclaration;

import utilities.ASTNodeDoesNotBelongHere;
import utilities.Antipattern;

public class Rule17Antipattern extends Antipattern {


	public Rule17Antipattern(ASTNode atom) throws ASTNodeDoesNotBelongHere {
		super(atom);

	}

	@Override
	protected boolean belongs(ASTNode atom) {
		return atom instanceof MethodDeclaration;
	}
	
	public static Optional<Rule17Antipattern> getInstance(ASTNode atom) {
		Rule17Antipattern instance = null;
		try {
			instance =  new Rule17Antipattern(atom);
		} catch (ASTNodeDoesNotBelongHere e) {
			e.printStackTrace();
		}
		
		return Optional.ofNullable(instance);
	}

}
