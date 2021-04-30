package uncategorizedIsPresentUsage;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.IfStatement;
import org.eclipse.jdt.core.dom.MethodInvocation;
import org.eclipse.jdt.core.dom.PrefixExpression;
import org.javatuples.Pair;

import optionalanalyzer.metamodel.entity.MCompilationUnit;
import optionalanalyzer.metamodel.entity.MRule10sAntipattern;
import optionalanalyzer.metamodel.entity.MRule26sAntipattern;
import optionalanalyzer.metamodel.entity.MRule3sAntipattern;
import optionalanalyzer.metamodel.entity.MRule4sAntipattern;
import optionalanalyzer.metamodel.entity.MRule5sAntipattern;
import optionalanalyzer.metamodel.entity.MRule6sAntipattern;
import optionalanalyzer.metamodel.entity.MRule7sAntipattern;
import optionalanalyzer.metamodel.entity.MRule8sAntipattern;
import optionalanalyzer.metamodel.entity.MRule9sAntipattern;
import optionalanalyzer.metamodel.entity.MUncategorizedIsPresentPossibleAntipattern;
import optionalanalyzer.metamodel.factory.Factory;
import ro.lrg.xcore.metametamodel.Group;
import ro.lrg.xcore.metametamodel.XEntity;
import rule26Antipattern.Rule26Antipattern;
import utilities.Antipattern;
import utilities.OptionalInvocationFinder;
import utilities.UtilityClass;
import java.util.function.Supplier;

public class UncategorizedIsPresentAntipatternFinder {

	private final OptionalInvocationFinder optionalInvocationFinder = new OptionalInvocationFinder();

	public List<MUncategorizedIsPresentPossibleAntipattern> getMAntipatterns(MCompilationUnit mCompilationUnit) {

		List<Stream<Object>> isPresentInvocationBasedMAntipatternStreamList = getIsPresentInvocationBasedCategorizedAntipatterns(mCompilationUnit);
		Set<MethodInvocation> categorizedIsPresentInvocations = getCenzoredIsPresentAntipatterns(isPresentInvocationBasedMAntipatternStreamList);
		ICompilationUnit iCompilationUnit = (ICompilationUnit) mCompilationUnit.getUnderlyingObject();
		CompilationUnit compilationUnit = UtilityClass.parse(iCompilationUnit);
		List<MethodInvocation> isPresentMethodInvocations = optionalInvocationFinder.getInvocations(compilationUnit);

		categorizedIsPresentInvocations.addAll(getRule26BaseIsPresentInvocation(mCompilationUnit));

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

	private Stream<Object> getAntipatterns(Supplier<Group<? extends XEntity>> groupSupplier, Function<XEntity, Object> wrappedObjectGetterFunction) {

		return groupSupplier.get()
				.getElements()
				.stream()
				.map(wrappedObjectGetterFunction);
	}

	private List<Stream<Object>> getIsPresentInvocationBasedCategorizedAntipatterns(MCompilationUnit mCompilationUnit) {

		var rule3AntipatternStream = getAntipatterns(mCompilationUnit::rule_3AntipatternDetector, xEntity -> ((MRule3sAntipattern)xEntity).getUnderlyingObject());
		var rule4AntipatternStream = getAntipatterns(mCompilationUnit::rule_4AntipatternDetector, xEntity -> ((MRule4sAntipattern)xEntity).getUnderlyingObject());
		var rule5AntipatternStream = getAntipatterns(mCompilationUnit::rule_5AntipatternDetector, xEntity -> ((MRule5sAntipattern)xEntity).getUnderlyingObject());
		var rule6AntipatternStream = getAntipatterns(mCompilationUnit::rule_6AntipatternDetector, xEntity -> ((MRule6sAntipattern)xEntity).getUnderlyingObject());
		var rule7AntipatternStream = getAntipatterns(mCompilationUnit::rule_7AntipatternDetector, xEntity -> ((MRule7sAntipattern)xEntity).getUnderlyingObject());
		var rule8AntipatternStream = getAntipatterns(mCompilationUnit::rule_8AntipatternDetector, xEntity -> ((MRule8sAntipattern)xEntity).getUnderlyingObject());
		var rule9AntipatternStream = getAntipatterns(mCompilationUnit::rule_9AntipatternDetector, xEntity -> ((MRule9sAntipattern)xEntity).getUnderlyingObject());
		var rule10AntipatternStream = getAntipatterns(mCompilationUnit::rule10AntipatternDetector, xEntity -> ((MRule10sAntipattern)xEntity).getUnderlyingObject());

		return List.of(rule3AntipatternStream,
				rule4AntipatternStream,
				rule5AntipatternStream,
				rule6AntipatternStream,
				rule7AntipatternStream,
				rule8AntipatternStream,
				rule9AntipatternStream,
				rule10AntipatternStream);
	}

	private Set<MethodInvocation> getCenzoredIsPresentAntipatterns(List<Stream<Object>> isPresentInvocationBasedMAntipatternSets) {

		return isPresentInvocationBasedMAntipatternSets
				.stream()
				.flatMap(Function.identity())
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

		return categorizedIfPresentInvocations.stream()
				.map(this::getPositions)
				.anyMatch(positions::equals);
	}

	private Pair<Integer, Integer> getPositions(MethodInvocation ifPresentMethodInvocation) {
		return Pair.with(ifPresentMethodInvocation.getStartPosition(), ifPresentMethodInvocation.getLength());
	}
}
