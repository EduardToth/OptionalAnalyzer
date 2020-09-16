package rule20Antipattern;

import java.util.Optional;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.VariableDeclarationFragment;
import org.eclipse.ui.texteditor.ITextEditor;

import utilities.ASTNodeDoesNotBelongHere;
import utilities.Atom;
import utilities.UtilityClass;

public class Rule20Atom extends Atom{

	public Rule20Atom(ASTNode wrappedElement) throws ASTNodeDoesNotBelongHere {
		super(wrappedElement);
	}

	@Override
	protected boolean belongs(ASTNode wrappedElement) {
		boolean itIs = wrappedElement instanceof VariableDeclarationFragment;
		
		return itIs;
	}
	
	@Override
	public void showInEditor() {
		ITextEditor ite = UtilityClass.getITextEditor(wrappedElement);
		ite.selectAndReveal(wrappedElement.getParent().getStartPosition(), wrappedElement.getParent().getLength());
	}
	
	public static Optional<Rule20Atom> getInstance(ASTNode wrappedElement) {
		Rule20Atom instance = null;
		try {
			instance =  new Rule20Atom(wrappedElement);
		} catch (ASTNodeDoesNotBelongHere e) {
			e.printStackTrace();
		}
		
		return Optional.ofNullable(instance);
	}
}
