package tests;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.Test;

import optionalanalizer.metamodel.entity.MRule25Atom;
import ro.lrg.xcore.metametamodel.Group;
import utilities.Atom;

public class Rule25Tests extends TestBaseClass{

	private static final  String packageName = "rule25Examples";
	private static final  String testFileName = "Example1.java"; 
	private static final int[] linesWithProblems = {8, 11, 15, 19};

	public Rule25Tests() throws BadNamingException {
		super(packageName, testFileName,  linesWithProblems);
	}

	@Override
	protected List<Atom> getAtoms() {
		Group<MRule25Atom> group = getMCompilationUnit().rule25AntipatternBuilder();
		
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