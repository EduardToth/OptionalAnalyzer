package uncategorizedIsPresentUsage;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.IfStatement;
import org.eclipse.jdt.core.dom.MethodInvocation;
import org.eclipse.jdt.core.dom.PrefixExpression;
import org.javatuples.Pair;

import optionalanalizer.metamodel.entity.MCompilationUnit;
import optionalanalizer.metamodel.entity.MRule10Atom;
import optionalanalizer.metamodel.entity.MRule26Atom;
import optionalanalizer.metamodel.entity.MRule3Atom;
import optionalanalizer.metamodel.entity.MRule4Atom;
import optionalanalizer.metamodel.entity.MRule5Atom;
import optionalanalizer.metamodel.entity.MRule6Atom;
import optionalanalizer.metamodel.entity.MRule7Atom;
import optionalanalizer.metamodel.entity.MRule8Atom;
import optionalanalizer.metamodel.entity.MRule9Atom;
import optionalanalizer.metamodel.entity.MUncategorizedIsPresentAtom;
import optionalanalizer.metamodel.factory.Factory;
import rule26Antipattern.Rule26Antipattern;
import utilities.Antipattern;
import utilities.OptionalInvocationFinder;
import utilities.UtilityClass;


public class UncategorizedIsPresentAntipatternFinder {

	private final OptionalInvocationFinder optionalInvocationFinder = new OptionalInvocationFinder();

	public List<MUncategorizedIsPresentAtom> getMAtoms(MCompilationUnit mCompilationUnit) {

		List<Stream<Object>> isPresentInvocationBasedMAtomStreamList = getIsPresentInvocationBasedCategorizedAtoms(mCompilationUnit);
		Set<MethodInvocation> categorizedIsPresentInvocations = getCenzoredIsPresentAtoms(isPresentInvocationBasedMAtomStreamList);
		categorizedIsPresentInvocations.addAll(getRule26BaseIsPresentInvocation(mCompilationUnit));
		ICompilationUnit iCompilationUnit = (ICompilationUnit) mCompilationUnit.getUnderlyingObject();
		CompilationUnit compilationUnit = UtilityClass.parse(iCompilationUnit);
		List<MethodInvocation> isPresentMethodInvocations = optionalInvocationFinder.getInvocations(compilationUnit);
		
		return isPresentMethodInvocations.stream()
				.filter(methodInvocation -> !contains(categorizedIsPresentInvocations, methodInvocation))
				.map(UncategorizedIsPresentAntipattern::getInstance)
				.filter(Optional::isPresent)
				.map(Optional::get)
				.map(Factory.getInstance()::createMUncategorizedIsPresentAtom)
				.collect(Collectors.toList());
	}
	
	private Set<MethodInvocation> getRule26BaseIsPresentInvocation(MCompilationUnit mCompilationUnit) {
		return mCompilationUnit.rule26AntipatternDetector()
				.getElements()
				.stream()
				.map(MRule26Atom::getUnderlyingObject)
				.filter(Rule26Antipattern.class::isInstance)
				.map(Rule26Antipattern.class::cast)
				.map(Rule26Antipattern::getWrappedElement)
				.filter(PrefixExpression.class::isInstance)
				.map(PrefixExpression.class::cast)
				.map(PrefixExpression::getOperand)
				.filter(MethodInvocation.class::isInstance)
				.map(MethodInvocation.class::cast)
				.collect(Collectors.toSet());
	}

	private List<Stream<Object>> getIsPresentInvocationBasedCategorizedAtoms(MCompilationUnit mCompilationUnit) {
		List<Stream<Object>> isPresentInvocationBasedMAtomStreamList = new LinkedList<>();

		isPresentInvocationBasedMAtomStreamList
		.add(mCompilationUnit.rule_3AntipatternDetector().getElements().stream().map(MRule3Atom::getUnderlyingObject));
		isPresentInvocationBasedMAtomStreamList
		.add(mCompilationUnit.rule_4AntipatternDetector().getElements().stream().map(MRule4Atom::getUnderlyingObject));
		isPresentInvocationBasedMAtomStreamList
		.add(mCompilationUnit.rule_5AntipatternDetector().getElements().stream().map(MRule5Atom::getUnderlyingObject));
		isPresentInvocationBasedMAtomStreamList
		.add(mCompilationUnit.rule_6AntipatternDetector().getElements().stream().map(MRule6Atom::getUnderlyingObject));
		isPresentInvocationBasedMAtomStreamList
		.add(mCompilationUnit.rule_7AntipatternDetector().getElements().stream().map(MRule7Atom::getUnderlyingObject));
		isPresentInvocationBasedMAtomStreamList
		.add(mCompilationUnit.rule_8AntipatternDetector().getElements().stream().map(MRule8Atom::getUnderlyingObject));
		isPresentInvocationBasedMAtomStreamList
		.add(mCompilationUnit.rule_9AntipatternDetector().getElements().stream().map(MRule9Atom::getUnderlyingObject));
		isPresentInvocationBasedMAtomStreamList
		.add(mCompilationUnit.rule10AntipatternDetector().getElements().stream().map(MRule10Atom::getUnderlyingObject));
	

		return isPresentInvocationBasedMAtomStreamList;
	}


	Set<MethodInvocation> getCenzoredIsPresentAtoms(List<Stream<Object>> isPresentInvocationBasedMAtomSets) {

		return isPresentInvocationBasedMAtomSets
				.stream()
				.flatMap(stream -> stream)
				.filter(Antipattern.class::isInstance)
				.map(Antipattern.class::cast)
				.map(Antipattern::getWrappedElement)
				.filter(IfStatement.class::isInstance)
				.map(IfStatement.class::cast)
				.map(IfStatement::getExpression)
				.map(optionalInvocationFinder::getInvocations)
				.flatMap(List::stream)
				.collect(Collectors.toSet());
	}

	private boolean contains(Set<MethodInvocation> categorizedIfPresentInvocations, MethodInvocation ifPresentMethodInvocation) {
		Pair<Integer, Integer> positions = getPositions(ifPresentMethodInvocation);

		return categorizedIfPresentInvocations
				.stream()
				.map(this::getPositions)
				.anyMatch(positions::equals);
	}

	private Pair<Integer, Integer> getPositions(MethodInvocation ifPresentMethodInvocation) {
		return Pair.with(ifPresentMethodInvocation.getStartPosition(), ifPresentMethodInvocation.getLength());
	}
}
