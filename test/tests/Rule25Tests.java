package tests;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.Test;

import optionalanalyzer.metamodel.entity.MRule25sAntipattern;
import ro.lrg.xcore.metametamodel.Group;
import utilities.Antipattern;

public class Rule25Tests extends TestBaseClass{

	private static final  String packageName = "rule25Examples";
	private static final  String testFileName = "Example1.java"; 
	private static final int[] linesWithProblems = {8, 11, 15, 19};

	public Rule25Tests() throws BadNamingException {
		super(packageName, testFileName,  linesWithProblems);
	}

	@Override
	protected List<Antipattern> getAntipatterns() {
		Group<MRule25sAntipattern> group = getMCompilationUnit().rule25AntipatternDetector();
		
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