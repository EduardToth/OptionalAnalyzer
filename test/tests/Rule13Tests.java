package tests;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.Test;

import optionalanalizer.metamodel.entity.MRule13Atom;
import ro.lrg.xcore.metametamodel.Group;
import utilities.Atom;

public class Rule13Tests extends TestBaseClass{

	private static final  String packageName = "rule13Examples";
	private static final  String testFileName = "Example1.java"; 
	private static final int[] linesWithProblems = {10, 11, 12, 13};

	public Rule13Tests() throws BadNamingException {
		super(packageName, testFileName, linesWithProblems);
	}

	@Override
	protected List<Atom> getAtoms() {
		Group<MRule13Atom> group = getMCompilationUnit()
				.rule13AntipatternBuilder();
		
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
