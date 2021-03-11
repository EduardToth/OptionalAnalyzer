package rule19Antipattern;

import java.util.Optional;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.MethodInvocation;

import rule18Antipattern.Rule18Antipattern;
import utilities.ASTNodeDoesNotBelongHere;
import utilities.Antipattern;

public class Rule19Antipattern extends Antipattern{

	public Rule19Antipattern(ASTNode astNode) throws ASTNodeDoesNotBelongHere {
		super(astNode);
	}

	@Override
	protected boolean belongs(ASTNode astNode) {
		return astNode instanceof MethodInvocation;
	}
	
	public static Optional<Rule19Antipattern> getInstance(ASTNode astNode) {
		Rule19Antipattern instance = null;
		try {
			instance =  new Rule19Antipattern(astNode);
		} catch (ASTNodeDoesNotBelongHere e) {
			e.printStackTrace();
		}
		
		return Optional.ofNullable(instance);
	}

}
