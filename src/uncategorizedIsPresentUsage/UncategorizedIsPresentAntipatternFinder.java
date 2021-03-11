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
import optionalanalizer.metamodel.entity.MRule10sAntipattern;
import optionalanalizer.metamodel.entity.MRule26sAntipattern;
import optionalanalizer.metamodel.entity.MRule3sAntipattern;
import optionalanalizer.metamodel.entity.MRule4sAntipattern;
import optionalanalizer.metamodel.entity.MRule5sAntipattern;
import optionalanalizer.metamodel.entity.MRule6sAntipattern;
import optionalanalizer.metamodel.entity.MRule7sAntipattern;
import optionalanalizer.metamodel.entity.MRule8sAntipattern;
import optionalanalizer.metamodel.entity.MRule9sAntipattern;
import optionalanalizer.metamodel.entity.MUncategorizedIsPresentPossibleAntipattern;
import optionalanalizer.metamodel.factory.Factory;
import rule26Antipattern.Rule26Antipattern;
import utilities.Antipattern;
import utilities.OptionalInvocationFinder;
import utilities.UtilityClass;


public class UncategorizedIsPresentAntipatternFinder {

	private final OptionalInvocationFinder optionalInvocationFinder = new OptionalInvocationFinder();

	public List<MUncategorizedIsPresentPossibleAntipattern> getMAntipatterns(MCompilationUnit mCompilationUnit) {

		List<Stream<Object>> isPresentInvocationBasedMAntipatternStreamList = getIsPresentInvocationBasedCategorizedAntipatterns(mCompilationUnit);
		Set<MethodInvocation> categorizedIsPresentInvocations = getCenzoredIsPresentAntipatterns(isPresentInvocationBasedMAntipatternStreamList);
		categorizedIsPresentInvocations.addAll(getRule26BaseIsPresentInvocation(mCompilationUnit));
		ICompilationUnit iCompilationUnit = (ICompilationUnit) mCompilationUnit.getUnderlyingObject();
		CompilationUnit compilationUnit = UtilityClass.parse(iCompilationUnit);
		List<MethodInvocation> isPresentMethodInvocations = optionalInvocationFinder.getInvocations(compilationUnit);
		
		return isPresentMethodInvocations.stream()
				.filter(methodInvocation -> !contains(categorizedIsPresentInvocations, methodInvocation))
				.map(UncategorizedIsPresentAntipattern::getInstance)
				.flatMap(Optional::stream)
				.map(Factory.getInstance()::createMUncategorizedIsPresentPossibleAntipattern)
				.collect(Collectors.toList());
	}
	
	private Set<MethodInvocation> getRule26BaseIsPresentInvocation(MCompilationUnit mCompilationUnit) {
		return mCompilationUnit.rule26AntipatternDetector()
				.getElements()
				.stream()
				.map(MRule26sAntipattern::getUnderlyingObject)
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

	private List<Stream<Object>> getIsPresentInvocationBasedCategorizedAntipatterns(MCompilationUnit mCompilationUnit) {
		List<Stream<Object>> isPresentInvocationBasedMAntipatternStreamList = new LinkedList<>();

		isPresentInvocationBasedMAntipatternStreamList
		.add(mCompilationUnit.rule_3AntipatternDetector().getElements().stream().map(MRule3sAntipattern::getUnderlyingObject));
		isPresentInvocationBasedMAntipatternStreamList
		.add(mCompilationUnit.rule_4AntipatternDetector().getElements().stream().map(MRule4sAntipattern::getUnderlyingObject));
		isPresentInvocationBasedMAntipatternStreamList
		.add(mCompilationUnit.rule_5AntipatternDetector().getElements().stream().map(MRule5sAntipattern::getUnderlyingObject));
		isPresentInvocationBasedMAntipatternStreamList
		.add(mCompilationUnit.rule_6AntipatternDetector().getElements().stream().map(MRule6sAntipattern::getUnderlyingObject));
		isPresentInvocationBasedMAntipatternStreamList
		.add(mCompilationUnit.rule_7AntipatternDetector().getElements().stream().map(MRule7sAntipattern::getUnderlyingObject));
		isPresentInvocationBasedMAntipatternStreamList
		.add(mCompilationUnit.rule_8AntipatternDetector().getElements().stream().map(MRule8sAntipattern::getUnderlyingObject));
		isPresentInvocationBasedMAntipatternStreamList
		.add(mCompilationUnit.rule_9AntipatternDetector().getElements().stream().map(MRule9sAntipattern::getUnderlyingObject));
		isPresentInvocationBasedMAntipatternStreamList
		.add(mCompilationUnit.rule10AntipatternDetector().getElements().stream().map(MRule10sAntipattern::getUnderlyingObject));
	

		return isPresentInvocationBasedMAntipatternStreamList;
	}


	Set<MethodInvocation> getCenzoredIsPresentAntipatterns(List<Stream<Object>> isPresentInvocationBasedMAntipatternSets) {

		return isPresentInvocationBasedMAntipatternSets
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
