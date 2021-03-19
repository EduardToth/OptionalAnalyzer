package tests;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.Test;

import optionalanalyzer.metamodel.entity.MRule9sAntipattern;
import ro.lrg.xcore.metametamodel.Group;
import utilities.Antipattern;

public class Rule_9Tests extends TestBaseClass{

	private static final  String packageName = "rule9Examples";
	private static final  String testFileName = "Example1.java"; 
	private static final int[] linesWithProblems = {15, 79};

	public Rule_9Tests() throws BadNamingException {
		super(packageName, testFileName, linesWithProblems);
	}

	@Override
	protected List<Antipattern> getAntipatterns() {
		Group<MRule9sAntipattern> group = getMCompilationUnit().rule_9AntipatternDetector();
		
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
