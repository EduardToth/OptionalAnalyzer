package rule10Antipattern;

import java.util.Optional;
import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.IfStatement;
import org.eclipse.jdt.core.dom.ReturnStatement;

import utilities.ASTNodeDoesNotBelongHere;
import utilities.Atom;

public class Rule10Atom extends Atom{

	public Rule10Atom(ASTNode wrappedElement) throws ASTNodeDoesNotBelongHere {
		super(wrappedElement);
	}

	@Override
	protected boolean belongs(ASTNode wrappedElement) {
		return wrappedElement instanceof IfStatement ||
				wrappedElement instanceof ReturnStatement;
	}
	
	public static Optional<Rule10Atom> getInstance(ASTNode wrapppedElement) {
		Rule10Atom instance = null;
		try {
			instance =  new Rule10Atom(wrapppedElement);
		} catch (ASTNodeDoesNotBelongHere e) {
			e.printStackTrace();
		}
		
		return Optional.ofNullable(instance);
	}

}
