package tests;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.Test;

import optionalanalyzer.metamodel.entity.MRule2sAntipattern;
import ro.lrg.xcore.metametamodel.Group;
import utilities.Antipattern;
//valid
public class Rule_2Tests extends TestBaseClass{

	private static final  String packageName = "rule2Examples";
	private static final  String testFileName = "Example1.java"; 
	private static final int[] linesWithProblems = {11, 18, 26, 33, 41};

	public Rule_2Tests() throws BadNamingException {
		super(packageName, testFileName,  linesWithProblems);
	}

	@Override
	protected List<Antipattern> getAntipatterns() {
		Group<MRule2sAntipattern> group = getMCompilationUnit().rule_2AntipatternDetector();
		
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