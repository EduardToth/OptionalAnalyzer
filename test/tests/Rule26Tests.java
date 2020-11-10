package tests;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import optionalanalizer.metamodel.entity.MRule26Atom;
import ro.lrg.xcore.metametamodel.Group;
import utilities.Atom;

public class Rule26Tests extends TestBaseClass{

	private static final  String packageName = "rule26Examples";
	private static final  String testFileName = "Example1.java"; 
	private static final int[] linesWithProblems = {12, 13, 14, 15};

	public Rule26Tests() throws BadNamingException {
		super(packageName, testFileName,  linesWithProblems);
	}

	@Override
	protected List<Atom> getAtoms() {
		Group<MRule26Atom> group = getMCompilationUnit().rule26AntipatternBuilder();
		
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