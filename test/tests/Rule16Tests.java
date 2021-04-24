package tests;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.Test;

import optionalanalyzer.metamodel.entity.MRule16sAntipattern;
import utilities.Antipattern;

public class Rule16Tests extends TestBaseClass{

	private static final  String packageName = "rule16Examples";
	private static final  String testFileName = "Example1.java"; 
	private static final int[] linesWithProblems = {9, 11, 13, 15, 17};

	public Rule16Tests() throws BadNamingException {
		super(packageName, testFileName, linesWithProblems);
	}

	@Override
	protected List<Antipattern> getAntipatterns() {
		return getMCompilationUnit().rule16AntipatternDetector()
				.getElements()
				.stream()
				.map(MRule16sAntipattern::getUnderlyingObject)
				.map(Antipattern.class::cast)
				.collect(Collectors.toList());
	}

	@Test
	@Override
	public void test() {
		super.test();
	}
}