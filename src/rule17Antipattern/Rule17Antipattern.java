package rule17Antipattern;

import java.util.Optional;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.MethodDeclaration;

import utilities.ASTNodeDoesNotBelongHere;
import utilities.Antipattern;

public class Rule17Antipattern extends Antipattern {


	public Rule17Antipattern(ASTNode astNode) throws ASTNodeDoesNotBelongHere {
		super(astNode);

	}

	@Override
	protected boolean belongs(ASTNode astNode) {
		return astNode instanceof MethodDeclaration;
	}
	
	public static Optional<Rule17Antipattern> getInstance(ASTNode astNode) {
		Rule17Antipattern instance = null;
		try {
			instance =  new Rule17Antipattern(astNode);
		} catch (ASTNodeDoesNotBelongHere e) {
			e.printStackTrace();
		}
		
		return Optional.ofNullable(instance);
	}

}
