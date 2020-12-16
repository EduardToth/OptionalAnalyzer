package tests;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.Test;

import optionalanalizer.metamodel.entity.MRule16Atom;
import ro.lrg.xcore.metametamodel.Group;
import utilities.Atom;

public class Rule16Tests extends TestBaseClass{

	private static final  String packageName = "rule16Examples";
	private static final  String testFileName = "Example1.java"; 
	private static final int[] linesWithProblems = {9, 11, 13, 15, 17};

	public Rule16Tests() throws BadNamingException {
		super(packageName, testFileName, linesWithProblems);
	}

	@Override
	protected List<Atom> getAtoms() {
		Group<MRule16Atom> group = getMCompilationUnit().rule16AntipatternBuilder();
		
		return group.getElements().parallelStream()
			.map(mAtom -> (Atom)mAtom.getUnderlyingObject())
			.collect(Collectors.toList());
	}
	
	@Test
	@Override
	public void test() {
		super.test();
	}
}