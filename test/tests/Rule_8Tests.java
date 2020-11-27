package tests;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.Test;

import optionalanalizer.metamodel.entity.MRule8Atom;
import ro.lrg.xcore.metametamodel.Group;
import utilities.Atom;

public class Rule_8Tests extends TestBaseClass{

	private static final  String packageName = "rule8Examples";
	private static final  String testFileName = "Example1.java"; 
	private static final int[] linesWithProblems = {12, 22, 31, 42};

	public Rule_8Tests() throws BadNamingException {
		super(packageName, testFileName,linesWithProblems);
	}

	@Override
	protected List<Atom> getAtoms() {
		Group<MRule8Atom> group = getMCompilationUnit().rule_8AntipatternBuilder();
		
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