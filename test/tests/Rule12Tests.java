package tests;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import optionalanalizer.metamodel.entity.MRule12Atom;
import ro.lrg.xcore.metametamodel.Group;
import utilities.Atom;

public class Rule12Tests extends TestBaseClass{

	private static final  String packageName = "rule1Examples";
	private static final  String testFileName = "BasicTest.java"; 
	private static final int[] linesWithProblems = {8, 16, 21, 26};

	public Rule12Tests() throws BadNamingException {
		super(packageName, testFileName, linesWithProblems);
	}

	@Override
	protected List<Atom> getAtoms() {
		Group<MRule12Atom> group = getMCompilationUnit().rule12AntipatternBuilder();
		
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