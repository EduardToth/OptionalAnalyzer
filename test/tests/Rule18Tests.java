package tests;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.Test;

import optionalanalyzer.metamodel.entity.MRule18sAntipattern;
import ro.lrg.xcore.metametamodel.Group;
import utilities.Antipattern;

public class Rule18Tests extends TestBaseClass{

	private static final  String packageName = "rule18Examples";
	private static final  String testFileName = "Example1.java"; 
	private static final int[] linesWithProblems = {10, 11, 12, 13, 14};

	public Rule18Tests() throws BadNamingException {
		super(packageName, testFileName, linesWithProblems);
	}

	@Override
	protected List<Antipattern> getAntipatterns() {
		Group<MRule18sAntipattern> group = getMCompilationUnit().rule18AntipatternDetector();

		return group.getElements()
				.stream()
				.map(mAntipattern -> (Antipattern)mAntipattern.getUnderlyingObject())
				.collect(Collectors.toList());
	}

	@Test
	@Override
	public void test() {
		super.test();
	}
}
