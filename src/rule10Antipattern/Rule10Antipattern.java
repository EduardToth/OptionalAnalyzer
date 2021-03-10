package rule10Antipattern;

import java.util.Optional;
import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.IfStatement;
import org.eclipse.jdt.core.dom.ReturnStatement;

import utilities.ASTNodeDoesNotBelongHere;
import utilities.Antipattern;

public class Rule10Antipattern extends Antipattern{

	public Rule10Antipattern(ASTNode wrappedElement) throws ASTNodeDoesNotBelongHere {
		super(wrappedElement);
	}

	@Override
	protected boolean belongs(ASTNode wrappedElement) {
		return wrappedElement instanceof IfStatement ||
				wrappedElement instanceof ReturnStatement;
	}
	
	public static Optional<Rule10Antipattern> getInstance(ASTNode wrapppedElement) {
		Rule10Antipattern instance = null;
		try {
			instance =  new Rule10Antipattern(wrapppedElement);
		} catch (ASTNodeDoesNotBelongHere e) {
			e.printStackTrace();
		}
		
		return Optional.ofNullable(instance);
	}

}
