package tests;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.Test;

import optionalanalizer.metamodel.entity.MRule14Atom;
import ro.lrg.xcore.metametamodel.Group;
import utilities.Atom;

public class Rule14Tests extends TestBaseClass{

	private static final  String packageName = "rule14Examples";
	private static final  String testFileName = "Example1.java"; 
	private static final int[] linesWithProblems = {6, 7, 8, 9};

	public Rule14Tests() throws BadNamingException {
		super(packageName, testFileName, linesWithProblems);
	}

	@Override
	protected List<Atom> getAtoms() {
		Group<MRule14Atom> group = getMCompilationUnit().rule14AntipatternBuilder();
		
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
