package tests;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import optionalanalizer.metamodel.entity.MRule19Atom;

import ro.lrg.xcore.metametamodel.Group;
import utilities.Atom;

public class Rule19Tests extends TestBaseClass{

	private static final  String packageName = "rule19Examples";
	private static final  String testFileName = "Example1.java"; 
	private static final int[] linesWithProblems = {12, 13, 14, 15};

	public Rule19Tests() throws BadNamingException {
		super(packageName, testFileName, linesWithProblems);
	}

	@Override
	protected List<Atom> getAtoms() {
		Group<MRule19Atom> group = getMCompilationUnit().rule19AntipatternBuilder();
		
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