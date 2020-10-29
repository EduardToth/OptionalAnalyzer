package tests;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IPackageFragment;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.core.dom.CompilationUnit;

import optionalanalizer.metamodel.entity.MCompilationUnit;
import optionalanalizer.metamodel.factory.Factory;
import utilities.Atom;
import utilities.UtilityClass;

public abstract class TestBaseClass {

	private static final String TEST_PROJECT = "TestProject";
	private String packageName;
	private IPackageFragment iPackageFragment;
	private String testFileName;
	private long nrOfProblemsTheTestShouldFind;
	private int[] linesWithProblems;
	private ICompilationUnit iCompilationUnit;

	public TestBaseClass(String packageName, String testFileName,
			int[] linesWithProblems) throws BadNamingException {
		TestUtil.importProject(TEST_PROJECT, TEST_PROJECT + ".zip");
		this.packageName = packageName;
		this.testFileName = testFileName;
		this.nrOfProblemsTheTestShouldFind = linesWithProblems.length;
		this.linesWithProblems = linesWithProblems;
		getIntoProject();
	}

	private Optional<IPackageFragment> getPackageFragment(IJavaProject iJavaProject) {
		IPackageFragment[] packageFragments = new IPackageFragment[ 0 ];
		try {
			packageFragments = iJavaProject.getPackageFragments();
		} catch (JavaModelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		for(IPackageFragment iPackageFragment : packageFragments) {
			if(iPackageFragment.getElementName().equals(packageName)) {
				this.iPackageFragment = iPackageFragment;
				break;
			}
		}

		return Optional.ofNullable(this.iPackageFragment);
	}

	private void getIntoProject() throws BadNamingException {
		final IJavaProject iJavaProject = TestUtil.getProject(TEST_PROJECT)
				.orElseThrow(() -> new BadNamingException("Did not find the project in the workspace"));

		this.iPackageFragment = getPackageFragment(iJavaProject).orElseThrow(
				() -> new BadNamingException("The package: " + this.packageName + " does not exist in the project: " + TEST_PROJECT));

		this.iCompilationUnit = getCompilationUnit().orElseThrow(() -> new BadNamingException("The filename: " + testFileName +
				" does not exist in package " + this.packageName));

	}

	private Optional<ICompilationUnit> getCompilationUnit() {
		ICompilationUnit iCompilationUnit = null;
		try {
			for(ICompilationUnit unit : iPackageFragment.getCompilationUnits()) {
				if(unit.getElementName().equals(this.testFileName)) {
					iCompilationUnit = unit;
				}
			}
		} catch (JavaModelException e) {
			e.printStackTrace();
		}

		return Optional.ofNullable(iCompilationUnit);
	}

	private Map<Integer, Boolean> getErrorLineMap() {
		Map<Integer, Boolean> errorLineMap = new HashMap<>();

		for(int errorLineNr : linesWithProblems) {
			errorLineMap.put(errorLineNr, false);
		}

		return errorLineMap;
	}

	public void test() {

		Map<Integer, Boolean> errorLineMap = getErrorLineMap();
		boolean testPasses = false;
		try {
			testPasses = passes(errorLineMap);
		} catch (TestingException e) {
			fail(e.getMessage());
		}

		if(errorLineMap.containsValue(false)) {
			final List<Integer> unrevealedTestLines = getUnrevealedTests(errorLineMap);
			String errorMessage = getErrorInStringForm(unrevealedTestLines);
			fail(errorMessage);
		}

		assertTrue(testPasses);
	}

	private List<Integer> getUnrevealedTests(Map<Integer, Boolean> errorLineMap) {
		return errorLineMap.entrySet().stream()
				.filter(entry -> entry.getValue() == false)
				.map(entry -> entry.getKey())
				.collect(Collectors.toList());

	}

	private String getErrorInStringForm(List<Integer> unrevealedTestLines) {
		return unrevealedTestLines.stream()
				.map(lineNr -> "The test did not recognize the problem on line " + lineNr + " as a problem")
				.reduce((text1, text2) -> text1 + System.lineSeparator() + text2)
				.orElse("");
	}

	private boolean passes(Map<Integer, Boolean> errorLineMap) throws TestingException {
		CompilationUnit compilationUnit = UtilityClass.parse(iCompilationUnit);
		List<Atom> atoms = getAtoms();
		System.out.println(atoms);
		long nrOfAtomsFound =  atoms.stream()
				.map(atom -> compilationUnit.getLineNumber(atom.getWrappedElement().getStartPosition()))
				.filter(lineNr -> errorLineMap.containsKey(lineNr))
				.peek(lineNr -> errorLineMap.replace(lineNr, true))
				.count();

		if(nrOfAtomsFound > this.nrOfProblemsTheTestShouldFind) {
			List<Integer> problemLinesFound = getAllProblemLines(atoms);
			problemLinesFound.removeAll(Arrays.asList(linesWithProblems));
			throw new TestingException("more problems found at the following lines: " );
		}

		return nrOfAtomsFound == this.nrOfProblemsTheTestShouldFind;
	}

	private List<Integer> getAllProblemLines(List<Atom> atoms) {
		CompilationUnit compilationUnit = UtilityClass.parse(iCompilationUnit);

		return atoms.stream()
				.map(atom -> compilationUnit.getLineNumber(atom.getWrappedElement().getStartPosition()))
				.collect(Collectors.toList());
	}

	protected abstract List<Atom> getAtoms();

	protected MCompilationUnit getMCompilationUnit() {
		return Factory.getInstance().createMCompilationUnit(iCompilationUnit);
	}
}
