package rule19Antipattern;

import java.util.Optional;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.MethodInvocation;

import rule18Antipattern.Rule18Atom;
import utilities.ASTNodeDoesNotBelongHere;
import utilities.Atom;

public class Rule19Atom extends Atom{

	public Rule19Atom(ASTNode atom) throws ASTNodeDoesNotBelongHere {
		super(atom);
	}

	@Override
	protected boolean belongs(ASTNode atom) {
		return atom instanceof MethodInvocation;
	}
	
	public static Optional<Rule19Atom> getInstance(ASTNode atom) {
		Rule19Atom instance = null;
		try {
			instance =  new Rule19Atom(atom);
		} catch (ASTNodeDoesNotBelongHere e) {
			e.printStackTrace();
		}
		
		return Optional.ofNullable(instance);
	}

}
