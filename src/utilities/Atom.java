package utilities;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.ui.texteditor.ITextEditor;


public abstract class Atom {

	protected ASTNode wrappedElement;

	public Atom(ASTNode wrappedElement) throws ASTNodeDoesNotBelongHere{
		if(!belongs(wrappedElement)) {
			throw new ASTNodeDoesNotBelongHere();
		}
		this.wrappedElement = wrappedElement;
	}

	public void showInEditor() {
		ITextEditor ite = UtilityClass.getITextEditor(wrappedElement);
		ite.selectAndReveal(wrappedElement.getStartPosition(), wrappedElement.getLength());
	}

	@Override
	public boolean equals(Object o) {
		if(o instanceof Atom) {
			if(this.wrappedElement.getStartPosition() - ((Atom)o).wrappedElement.getStartPosition() == 0) {
				return true;
			}
		}
		return false;
	}

	@Override
	public String toString() {
		CompilationUnit compilationUnit = UtilityClass.getCompilationUnit(wrappedElement);
		int startLineNumber = compilationUnit.getLineNumber(wrappedElement.getStartPosition()) - 1;
		return "On line: " + startLineNumber;
	}
	protected abstract boolean belongs(ASTNode wrappedElement);
}
