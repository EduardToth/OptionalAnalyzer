package tests;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.Test;

import optionalanalyzer.metamodel.entity.MRule6sAntipattern;
import ro.lrg.xcore.metametamodel.Group;
import utilities.Antipattern;

public class Rule_6Tests extends TestBaseClass{

	private static final  String packageName = "rule6Examples";
	private static final  String testFileName = "Example1.java"; 
	private static final int[] linesWithProblems = {18, 29, 40, 51};

	public Rule_6Tests() throws BadNamingException {
		super(packageName, testFileName, linesWithProblems);
	}

	@Override
	protected List<Antipattern> getAntipatterns() {
		Group<MRule6sAntipattern> group = getMCompilationUnit().rule_6AntipatternDetector();
		
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