package tests;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.Test;

import optionalanalizer.metamodel.entity.MUncategorizedIsPresentAtom;
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
	protected List<Antipattern> getAtoms() {
		Group<MUncategorizedIsPresentAtom> group = getMCompilationUnit()
				.uncategorizedIsPresentInvocationBasedAntipatternDetector();
		
		return group.getElements().stream()
			.map(mAtom -> (Antipattern)mAtom.getUnderlyingObject())
			.collect(Collectors.toList());
	}
	
	@Test
	@Override
	public void test() {
		super.test();
	}
}
