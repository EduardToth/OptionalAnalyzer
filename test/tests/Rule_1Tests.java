package tests;

import java.util.List;
import org.junit.jupiter.api.Test;
import java.util.stream.Collectors;

import optionalanalizer.metamodel.entity.MRule1Atom;
import ro.lrg.xcore.metametamodel.Group;
import utilities.Atom;
//valid
public class Rule_1Tests extends TestBaseClass{

	private static final  String packageName = "rule1Examples";
	private static final  String testFileName = "Example1.java"; 
	private static final int[] linesWithProblems = {9, 15, 21, 26};

	public Rule_1Tests() throws BadNamingException {
		super(packageName, testFileName, linesWithProblems);
	}

	@Override
	protected List<Atom> getAtoms() {
		Group<MRule1Atom> group = getMCompilationUnit().rule_1AntipatternBuilder();
		
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
