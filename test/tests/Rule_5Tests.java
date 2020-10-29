package tests;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import optionalanalizer.metamodel.entity.MRule5Atom;
import ro.lrg.xcore.metametamodel.Group;
import utilities.Atom;

public class Rule_5Tests extends TestBaseClass{

	private static final  String packageName = "rule5Examples";
	private static final  String testFileName = "Example1.java"; 
	private static final int[] linesWithProblems = {18, 29, 40, 51, 62};

	public Rule_5Tests() throws BadNamingException {
		super(packageName, testFileName, linesWithProblems);
	}

	@Override
	protected List<Atom> getAtoms() {
		Group<MRule5Atom> group = getMCompilationUnit().rule_5AntipatternBuilder();
		
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