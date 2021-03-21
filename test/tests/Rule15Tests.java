package tests;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.Test;

import optionalanalyzer.metamodel.entity.MRule15sAntipattern;
import ro.lrg.xcore.metametamodel.Group;
import utilities.Antipattern;

public class Rule15Tests extends TestBaseClass{

	private static final  String packageName = "rule15Examples";
	private static final  String testFileName = "Example1.java"; 
	private static final int[] linesWithProblems = {12, 16, 20, 24};

	public Rule15Tests() throws BadNamingException {
		super(packageName, testFileName, linesWithProblems);
	}

	@Override
	protected List<Antipattern> getAntipatterns() {
		Group<MRule15sAntipattern> group = getMCompilationUnit().rule15AntipatternDetector();
		
		return group.getElements().stream()
			.map(MRule15sAntipattern::getUnderlyingObject)
			.map(Antipattern.class::cast)
			.collect(Collectors.toList());
	}
	
	@Test
	@Override
	public void test() {
		super.test();
	}
}