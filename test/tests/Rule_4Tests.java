package tests;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.Test;

import optionalanalyzer.metamodel.entity.MRule4sAntipattern;
import ro.lrg.xcore.metametamodel.Group;
import utilities.Antipattern;

public class Rule_4Tests extends TestBaseClass{

	private static final  String packageName = "rule4Examples";
	private static final  String testFileName = "Example1.java"; 
	private static final int[] linesWithProblems = {18, 29, 40, 51, 78};

	public Rule_4Tests() throws BadNamingException {
		super(packageName, testFileName, linesWithProblems);
	}

	@Override
	protected List<Antipattern> getAntipatterns() {
		Group<MRule4sAntipattern> group = getMCompilationUnit().rule_4AntipatternDetector();

		return group.getElements().stream()
				.map(MRule4sAntipattern::getUnderlyingObject)
				.map(Antipattern.class::cast)
				.collect(Collectors.toList());
	}

	@Test
	@Override
	public void test() {
		super.test();
	}
}
