package rule18Antipattern;

import java.util.Optional;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.SimpleName;
import org.eclipse.jdt.core.dom.VariableDeclarationFragment;
import org.eclipse.ui.texteditor.ITextEditor;

import utilities.ASTNodeDoesNotBelongHere;
import utilities.Antipattern;
import utilities.UtilityClass;

public class Rule18Antipattern extends Antipattern {

	public Rule18Antipattern(ASTNode atom) throws ASTNodeDoesNotBelongHere {
		super(atom);
	}

	@Override
	protected boolean belongs(ASTNode atom) {
		return atom instanceof SimpleName;
	}

	@Override
	public boolean equals(Object o) { 
		if(o instanceof Rule18Antipattern) {
			return this.hashCode() == o.hashCode();
		}
		return false;
	}
	
	@Override
	public int hashCode() {
		return getParentNode(wrappedElement).getStartPosition();
	}

	private ASTNode getParentNode(ASTNode astNode) {
		if(astNode.getParent() instanceof VariableDeclarationFragment) {
			return astNode.getParent().getParent();
		} else if(astNode.getParent() instanceof MethodDeclaration){
			return astNode;
		} else {
			return astNode.getParent();
		}
	}

	@Override
	public void showInEditor() {
		ASTNode astNode = getParentNode(wrappedElement);
		ITextEditor ite = UtilityClass.getITextEditor(wrappedElement);
		ite.selectAndReveal(astNode.getStartPosition(),
				astNode.getLength());
	}

	public static Optional<Rule18Antipattern> getInstance(ASTNode atom) {
		Rule18Antipattern instance = null;
		try {
			instance =  new Rule18Antipattern(atom);
		} catch (ASTNodeDoesNotBelongHere e) {
			e.printStackTrace();
		}

		return Optional.ofNullable(instance);
	}
}
