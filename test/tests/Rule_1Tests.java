package tests;

import java.util.List;


import org.junit.Test;
import java.util.stream.Collectors;

import optionalanalizer.metamodel.entity.MRule1sAntipattern;
import ro.lrg.xcore.metametamodel.Group;
import utilities.Antipattern;


public class Rule_1Tests extends TestBaseClass{

	private static final  String packageName = "rule1Examples";
	private static final  String testFileName = "Example1.java"; 
	private static final int[] linesWithProblems = {9, 15, 21, 26};

	public Rule_1Tests() throws BadNamingException {
		super(packageName, testFileName, linesWithProblems);
	}

	@Override
	protected List<Antipattern> getAntipatterns() {
		Group<MRule1sAntipattern> group = getMCompilationUnit().rule_1AntipatternDetector();

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
