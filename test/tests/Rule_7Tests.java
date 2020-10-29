package tests;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import optionalanalizer.metamodel.entity.MRule7Atom;
import ro.lrg.xcore.metametamodel.Group;
import utilities.Atom;

public class Rule_7Tests extends TestBaseClass{

	private static final  String packageName = "rule7Examples";
	private static final  String testFileName = "Example1.java"; 
	private static final int[] linesWithProblems = {60, 48, 38, 28, 18};

	public Rule_7Tests() throws BadNamingException {
		super(packageName, testFileName, linesWithProblems);
	}

	@Override
	protected List<Atom> getAtoms() {
		Group<MRule7Atom> group = getMCompilationUnit().rule_7AntipatternBuilder();
		
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