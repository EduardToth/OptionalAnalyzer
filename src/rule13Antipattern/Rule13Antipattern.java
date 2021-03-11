package rule13Antipattern;

import java.util.Optional;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.FieldDeclaration;

import utilities.ASTNodeDoesNotBelongHere;
import utilities.Antipattern;

public class Rule13Antipattern extends Antipattern {

	public Rule13Antipattern(ASTNode astNode) throws ASTNodeDoesNotBelongHere {
		super(astNode);
	}

	@Override
	protected boolean belongs(ASTNode astNode) {
		return astNode instanceof FieldDeclaration;
	}
	
	public static Optional<Rule13Antipattern> getInstance(ASTNode astNode) {
		Rule13Antipattern instance = null;
		try {
			instance =  new Rule13Antipattern(astNode);
		} catch (ASTNodeDoesNotBelongHere e) {
			e.printStackTrace();
		}
		
		return Optional.ofNullable(instance);
	}
}
