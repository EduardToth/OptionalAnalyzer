package tests;
//valid
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Test;

import optionalanalizer.metamodel.entity.MRule4Atom;
import ro.lrg.xcore.metametamodel.Group;
import utilities.Antipattern;

public class Rule_4Tests extends TestBaseClass{

	private static final  String packageName = "rule4Examples";
	private static final  String testFileName = "Example1.java"; 
	private static final int[] linesWithProblems = {18, 29, 40, 51, 78};

	public Rule_4Tests() throws BadNamingException {
		super(packageName, testFileName, linesWithProblems);
	}

	@Override
	protected List<Antipattern> getAtoms() {
		Group<MRule4Atom> group = getMCompilationUnit().rule_4AntipatternDetector();
		
		return group.getElements().stream()
			.map(mAtom -> (Antipattern)mAtom.getUnderlyingObject())
			.collect(Collectors.toList());
	}
	
	@Test
	@Override
	public void test() {
		super.test();
	}
}
