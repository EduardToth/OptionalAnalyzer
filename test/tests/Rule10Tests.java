package tests;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.Test;

import optionalanalizer.metamodel.entity.MRule10Atom;
import ro.lrg.xcore.metametamodel.Group;
import utilities.Atom;

public class Rule10Tests extends TestBaseClass{

	private static final  String packageName = "rule10Examples";
	private static final  String testFileName = "Example1.java"; 
	private static final int[] linesWithProblems = {14, 25, 36, 47};

	public Rule10Tests() throws BadNamingException {
		super(packageName, testFileName,  linesWithProblems);
	}

	@Override
	protected List<Atom> getAtoms() {
		Group<MRule10Atom> group = getMCompilationUnit().rule10AntipatternBuilder();
		
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