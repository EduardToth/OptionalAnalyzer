package rule16Antipattern;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.MethodDeclaration;

import optionalanalizer.metamodel.entity.MRule16Atom;
import optionalanalizer.metamodel.factory.Factory;
import utilities.UtilityClass;

public class Rule16AtomFinder {

	public List<MRule16Atom> getMAtoms(ASTNode astNode) {
		List<MethodDeclaration> methodDeclarations = UtilityClass.getMethodDeclarations(astNode);
        
		return methodDeclarations.stream()
				.filter(decl -> !decl.isConstructor())
				.filter(decl -> !UtilityClass.isSetter( decl ))
				.filter(decl -> UtilityClass.containsAtLeastOneOptionalAsParameter( decl ))
				.peek(el -> System.out.println("----------------------> " + UtilityClass.getFirstOptionalParameter( el )))
				.map(decl -> UtilityClass.getFirstOptionalParameter( decl ))
				.map(Rule16Atom::getInstance)
				.filter(Optional::isPresent)
				.map(Optional::get)
				.map(atom -> Factory.getInstance().createMRule16Atom(atom))
				.collect(Collectors.toList());
	}
}
