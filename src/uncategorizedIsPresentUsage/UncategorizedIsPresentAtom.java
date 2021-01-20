package uncategorizedIsPresentUsage;

import java.util.Optional;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.MethodInvocation;

import utilities.ASTNodeDoesNotBelongHere;
import utilities.Atom;

public class UncategorizedIsPresentAtom extends Atom {

	public UncategorizedIsPresentAtom(ASTNode wrappedElement) throws ASTNodeDoesNotBelongHere {
		super(wrappedElement);
	}

	@Override
	protected boolean belongs(ASTNode wrappedElement) {
		
		return wrappedElement instanceof MethodInvocation;
	}
	
	public static Optional<UncategorizedIsPresentAtom> getInstance(ASTNode atom) {
		UncategorizedIsPresentAtom instance = null;
		try {
			instance =  new UncategorizedIsPresentAtom(atom);
		} catch (ASTNodeDoesNotBelongHere e) {
			e.printStackTrace();
		}
		
		return Optional.ofNullable(instance);
	}

}
