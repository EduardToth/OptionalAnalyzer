package tests;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.Test;

import optionalanalyzer.metamodel.entity.MRule13sAntipattern;
import optionalanalyzer.metamodel.entity.MRule20sAntipattern;
import ro.lrg.xcore.metametamodel.Group;
import utilities.Antipattern;

public class Rule20Tests extends TestBaseClass{

	private static final  String packageName = "rule20Examples";
	private static final  String testFileName = "Example1.java"; 
	private static final int[] linesWithProblems = {7, 8, 9};

	public Rule20Tests() throws BadNamingException {
		super(packageName, testFileName, linesWithProblems);
	}

	@Override
	protected List<Antipattern> getAntipatterns() {
		Group<MRule20sAntipattern> group = getMCompilationUnit().rule20AntipatternDetector();
		
		return group.getElements().stream()
			.map(MRule20sAntipattern::getUnderlyingObject)
			.map(Antipattern.class::cast)
			.collect(Collectors.toList());
	}
	
	@Test
	@Override
	public void test() {
		super.test();
	}
}