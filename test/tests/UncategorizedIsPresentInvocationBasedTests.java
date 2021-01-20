package tests;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.Test;

import optionalanalizer.metamodel.entity.MUncategorizedIsPresentAtom;
import ro.lrg.xcore.metametamodel.Group;
import utilities.Atom;

public class UncategorizedIsPresentInvocationBasedTests extends TestBaseClass{

	private static final  String packageName = "uncategorizedIsPresentInvocationBasedExample";
	private static final  String testFileName = "Example1.java"; 
	private static final int[] linesWithProblems = {12, 17, 26};
	public UncategorizedIsPresentInvocationBasedTests()
			throws BadNamingException {
		super(packageName, testFileName, linesWithProblems);
	}
	
	@Override
	protected List<Atom> getAtoms() {
		Group<MUncategorizedIsPresentAtom> group = getMCompilationUnit()
				.uncategorizedIsPresentInvocationBasedAntipatternBuilder();
		
		return group.getElements().stream()
			.map(mAtom -> (Atom)mAtom.getUnderlyingObject())
			.collect(Collectors.toList());
	}
	
	@Test
	@Override
	public void test() {
		super.test();
	}

}
