package tests;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.Test;

import optionalanalizer.metamodel.entity.MRule3sAntipattern;
import ro.lrg.xcore.metametamodel.Group;
import utilities.Antipattern;
//valid
public class Rule_3Tests extends TestBaseClass{

	private static final  String packageName = "rule3Examples";
	private static final  String testFileName = "Example1.java"; 
	private static final int[] linesWithProblems = {16, 29, 40, 51};

	public Rule_3Tests() throws BadNamingException {
		super(packageName, testFileName, linesWithProblems);
	}

	@Override
	protected List<Antipattern> getAntipatterns() {
		Group<MRule3sAntipattern> group = getMCompilationUnit().rule_3AntipatternDetector();
		
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
