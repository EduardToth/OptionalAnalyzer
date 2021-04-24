package tests;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.Test;

import optionalanalyzer.metamodel.entity.MRule17sAntipattern;
import ro.lrg.xcore.metametamodel.Group;
import utilities.Antipattern;

public class Rule17Tests extends TestBaseClass{

	private static final  String packageName = "rule17Examples";
	private static final  String testFileName = "Example1.java"; 
	private static final int[] linesWithProblems = {7, 13, 17};

	public Rule17Tests() throws BadNamingException {
		super(packageName, testFileName, linesWithProblems);
	}

	@Override
	protected List<Antipattern> getAntipatterns() {
		return getMCompilationUnit().rule17AntipatternDetector()
				.getElements()
				.stream()
				.map(MRule17sAntipattern::getUnderlyingObject)
				.map(Antipattern.class::cast)
				.collect(Collectors.toList());
	}

	@Test
	@Override
	public void test() {
		super.test();
	}
}