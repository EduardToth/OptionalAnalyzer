package tests;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.Test;

import optionalanalizer.metamodel.entity.MRule5Atom;
import ro.lrg.xcore.metametamodel.Group;
import utilities.Antipattern;

public class Rule_5Tests extends TestBaseClass{

	private static final  String packageName = "rule5Examples";
	private static final  String testFileName = "Example1.java"; 
	private static final int[] linesWithProblems = {18, 29, 40, 51};

	public Rule_5Tests() throws BadNamingException {
		super(packageName, testFileName, linesWithProblems);
	}

	@Override
	protected List<Antipattern> getAtoms() {
		Group<MRule5Atom> group = getMCompilationUnit().rule_5AntipatternDetector();

		return group.getElements()
				.stream()
				.map(mAtom -> (Antipattern)mAtom.getUnderlyingObject())
				.collect(Collectors.toList());
	}

	@Test
	@Override
	public void test() {
		super.test();
	}
}