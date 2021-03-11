package uncategorizedIsPresentUsage;

import java.util.Optional;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.MethodInvocation;

import utilities.ASTNodeDoesNotBelongHere;
import utilities.Antipattern;

public class UncategorizedIsPresentAntipattern extends Antipattern {

	public UncategorizedIsPresentAntipattern(ASTNode wrappedElement) throws ASTNodeDoesNotBelongHere {
		super(wrappedElement);
	}

	@Override
	protected boolean belongs(ASTNode wrappedElement) {
		
		return wrappedElement instanceof MethodInvocation;
	}
	
	public static Optional<UncategorizedIsPresentAntipattern> getInstance(ASTNode antipattern) {
		UncategorizedIsPresentAntipattern instance = null;
		try {
			instance =  new UncategorizedIsPresentAntipattern(antipattern);
		} catch (ASTNodeDoesNotBelongHere e) {
			e.printStackTrace();
		}
		
		return Optional.ofNullable(instance);
	}

}
