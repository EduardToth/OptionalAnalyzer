package rule14Antipattern;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.MethodDeclaration;

import optionalanalizer.metamodel.entity.MRule14Atom;
import optionalanalizer.metamodel.factory.Factory;
import utilities.UtilityClass;

public class Rule14AtomFinder {

	public List<MRule14Atom> getMAtoms(ASTNode astNode) {
		List<MethodDeclaration> methodDeclarations = UtilityClass.getMethodDeclarations(astNode);

		return methodDeclarations.stream()
				.filter(decl -> decl.isConstructor())
				.filter(decl -> UtilityClass.containsAtLeastOneOptionalAsParameter( decl ))
				.map(decl -> UtilityClass.getFirstOptionalParameter( decl ))
				.map(Rule14Atom::getInstance)
				.filter(Optional::isPresent)
				.map(Optional::get)
				.map(atom -> Factory.getInstance().createMRule14Atom(atom))
				.collect(Collectors.toList());

	}
}
