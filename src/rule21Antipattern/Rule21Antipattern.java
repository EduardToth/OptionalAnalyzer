package rule21Antipattern;

import java.util.Optional;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.MethodInvocation;

import utilities.ASTNodeDoesNotBelongHere;
import utilities.Antipattern;

public class Rule21Antipattern extends Antipattern{

	public Rule21Antipattern(ASTNode wrappedElement) throws ASTNodeDoesNotBelongHere {
		super(wrappedElement);
	}
	
	@Override
	protected boolean belongs(ASTNode wrappedElement) {
		return wrappedElement instanceof MethodInvocation;
	}
	
	public static Optional<Rule21Antipattern> getInstance(ASTNode atom) {
		Rule21Antipattern instance = null;
		try {
			instance =  new Rule21Antipattern(atom);
		} catch (ASTNodeDoesNotBelongHere e) {
			e.printStackTrace();
		}
		
		return Optional.ofNullable(instance);
	}

}
