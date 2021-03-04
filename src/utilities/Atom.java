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
	public String toString() {
		CompilationUnit compilationUnit = UtilityClass.getCompilationUnit(wrappedElement);

		String fileName = compilationUnit.getJavaElement().getElementName();
		int startLineNumber = compilationUnit.getLineNumber(wrappedElement.getStartPosition());
		return "In file: " + fileName + " "
					+ "On line: " + startLineNumber;
	}

	protected abstract boolean belongs(ASTNode wrappedElement);

	public int getStartingPosition() {

		return wrappedElement.getStartPosition();
	}

	public ASTNode getWrappedElement() {
		return wrappedElement;
	}
}
