package tests;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.Test;

import optionalanalizer.metamodel.entity.MRule14Atom;
import ro.lrg.xcore.metametamodel.Group;
import utilities.Antipattern;

public class Rule14Tests extends TestBaseClass{

	private static final  String packageName = "rule14Examples";
	private static final  String testFileName = "Example1.java"; 
	private static final int[] linesWithProblems = {6, 7, 8, 9};

	public Rule14Tests() throws BadNamingException {
		super(packageName, testFileName, linesWithProblems);
	}

	@Override
	protected List<Antipattern> getAtoms() {
		Group<MRule14Atom> group = getMCompilationUnit().rule14AntipatternDetector();
		
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
