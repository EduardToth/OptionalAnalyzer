package tests;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.Test;

import optionalanalyzer.metamodel.entity.MRule7sAntipattern;
import ro.lrg.xcore.metametamodel.Group;
import utilities.Antipattern;

public class Rule_7Tests extends TestBaseClass{

	private static final  String packageName = "rule7Examples";
	private static final  String testFileName = "Example1.java"; 
	private static final int[] linesWithProblems = {60, 38, 28, 18};

	public Rule_7Tests() throws BadNamingException {
		super(packageName, testFileName, linesWithProblems);
	}

	@Override
	protected List<Antipattern> getAntipatterns() {
		Group<MRule7sAntipattern> group = getMCompilationUnit().rule_7AntipatternDetector();
		
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