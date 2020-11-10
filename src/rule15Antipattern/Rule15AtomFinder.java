package rule15Antipattern;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.MethodDeclaration;

import optionalanalizer.metamodel.entity.MRule15Atom;
import optionalanalizer.metamodel.factory.Factory;
import utilities.UtilityClass;

public class Rule15AtomFinder {

	public List<MRule15Atom> getMAtoms(ASTNode astNode) {
		List<MethodDeclaration> methodDeclarations = UtilityClass.getMethodDeclarations(astNode);
        
		return methodDeclarations.stream()
				.filter(decl -> UtilityClass.isSetter(decl))
				.filter(decl -> UtilityClass.containsAtLeastOneOptionalAsParameter( decl ))
				.map(decl -> UtilityClass.getFirstOptionalParameter( decl ))
				.map(Rule15Atom::getInstance)
				.filter(Optional::isPresent)
				.map(Optional::get)
				.map(atom -> Factory.getInstance().createMRule15Atom(atom))
				.collect(Collectors.toList());
	}
}
