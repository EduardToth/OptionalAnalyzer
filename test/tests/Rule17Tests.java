package tests;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.Test;

import optionalanalizer.metamodel.entity.MRule17Atom;
import ro.lrg.xcore.metametamodel.Group;
import utilities.Antipattern;

public class Rule17Tests extends TestBaseClass{

	private static final  String packageName = "rule17Examples";
	private static final  String testFileName = "Example1.java"; 
	private static final int[] linesWithProblems = {7, 13, 17};

	public Rule17Tests() throws BadNamingException {
		super(packageName, testFileName, linesWithProblems);
	}

	@Override
	protected List<Antipattern> getAtoms() {
		Group<MRule17Atom> group = getMCompilationUnit().rule17AntipatternDetector();
		
		return group.getElements().stream()
			.map(mAtom -> (Antipattern)mAtom.getUnderlyingObject())
			.collect(Collectors.toList());
	}
	
	@Test
	@Override
	public void test() {
		super.test();
	}
}