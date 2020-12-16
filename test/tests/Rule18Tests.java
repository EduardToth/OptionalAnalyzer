package tests;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.Test;

import optionalanalizer.metamodel.entity.MRule18Atom;
import ro.lrg.xcore.metametamodel.Group;
import utilities.Atom;

public class Rule18Tests extends TestBaseClass{

	private static final  String packageName = "rule18Examples";
	private static final  String testFileName = "Example1.java"; 
	private static final int[] linesWithProblems = {10, 11, 12, 13, 14};

	public Rule18Tests() throws BadNamingException {
		super(packageName, testFileName, linesWithProblems);
	}

	@Override
	protected List<Atom> getAtoms() {
		Group<MRule18Atom> group = getMCompilationUnit().rule18AntipatternBuilder();

		return group.getElements()
				.stream()
				.map(mAtom -> (Atom)mAtom.getUnderlyingObject())
				.collect(Collectors.toList());
	}

	@Test
	@Override
	public void test() {
		super.test();
	}
}
