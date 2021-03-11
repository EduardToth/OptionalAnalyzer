package tests;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.Test;

import optionalanalizer.metamodel.entity.MUncategorizedIsPresentPossibleAntipattern;
import ro.lrg.xcore.metametamodel.Group;
import utilities.Antipattern;

public class UncategorizedIsPresentInvocationBasedTests extends TestBaseClass{

	private static final  String packageName = "uncategorizedIsPresentInvocationBasedExample";
	private static final  String testFileName = "Example1.java"; 
	private static final int[] linesWithProblems = {12, 17, 26};
	public UncategorizedIsPresentInvocationBasedTests()
			throws BadNamingException {
		super(packageName, testFileName, linesWithProblems);
	}
	
	@Override
	protected List<Antipattern> getAntipatterns() {
		Group<MUncategorizedIsPresentPossibleAntipattern> group = getMCompilationUnit()
				.uncategorizedIsPresentInvocationBasedAntipatternDetector();
		
		return group.getElements().stream()
			.map(mAntipattern -> (Antipattern)mAntipattern.getUnderlyingObject())
			.collect(Collectors.toList());
	}
	
	@Test
	@Override
	public void test() {
		super.test();
	}
}
