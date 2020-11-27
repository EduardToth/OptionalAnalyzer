package tests;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.Test;

import optionalanalizer.metamodel.entity.MRule6Atom;
import ro.lrg.xcore.metametamodel.Group;
import utilities.Atom;

public class Rule_6Tests extends TestBaseClass{

	private static final  String packageName = "rule6Examples";
	private static final  String testFileName = "Example1.java"; 
	private static final int[] linesWithProblems = {18, 29, 40, 51};

	public Rule_6Tests() throws BadNamingException {
		super(packageName, testFileName, linesWithProblems);
	}

	@Override
	protected List<Atom> getAtoms() {
		Group<MRule6Atom> group = getMCompilationUnit().rule_6AntipatternBuilder();
		
		return group.getElements().stream()
			.map(mAtom -> (Atom)mAtom.getUnderlyingObject())
			.collect(Collectors.toList());
	}
	
	@Test
	@Override
	public void test() {
		super.test();
	}
}