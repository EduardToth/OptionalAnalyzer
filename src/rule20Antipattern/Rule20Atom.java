package rule20Antipattern;

import java.util.Optional;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.VariableDeclarationFragment;
import org.eclipse.ui.texteditor.ITextEditor;

import utilities.ASTNodeDoesNotBelongHere;
import utilities.Atom;
import utilities.UtilityClass;

public class Rule20Atom extends Atom{

	public Rule20Atom(ASTNode atom) throws ASTNodeDoesNotBelongHere {
		super(atom);
	}

	@Override
	protected boolean belongs(ASTNode atom) {
		return atom instanceof VariableDeclarationFragment;
	}
	
	@Override
	public void showInEditor() {
		ITextEditor ite = UtilityClass.getITextEditor(wrappedElement);
		ite.selectAndReveal(wrappedElement.getParent().getStartPosition(), wrappedElement.getParent().getLength());
	}
	
	public static Optional<Rule20Atom> getInstance(ASTNode atom) {
		Rule20Atom instance = null;
		try {
			instance =  new Rule20Atom(atom);
		} catch (ASTNodeDoesNotBelongHere e) {
			e.printStackTrace();
		}
		
		return Optional.ofNullable(instance);
	}
}
