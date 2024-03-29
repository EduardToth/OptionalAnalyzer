package tests;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IPackageFragment;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.junit.After;

import optionalanalyzer.metamodel.entity.MCompilationUnit;
import optionalanalyzer.metamodel.factory.Factory;
import utilities.Antipattern;
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
		try {
			TestUtil.deleteProject(TEST_PROJECT);
		} catch(RuntimeException e) {}
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
	
	private ICompilationUnit[] getComponentICompilationUnits(IPackageFragment iPackageFragment) {
		try {
			return iPackageFragment.getCompilationUnits();
		} catch (JavaModelException e) {
			return new ICompilationUnit[ 0 ];
		}
	}

	private Optional<ICompilationUnit> getCompilationUnit() {
		ICompilationUnit[] iCompilationUnits = getComponentICompilationUnits(iPackageFragment);
		
		return Arrays.stream(iCompilationUnits)
				.filter(unit -> unit.getElementName().equals(this.testFileName))
				.findFirst();
	}

	private Map<Integer, Boolean> getErrorLineMap() {
		return Arrays.stream(linesWithProblems)
			.boxed()
			.collect(Collectors.toMap(Function.identity(), nr -> false));
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
				.filter(Predicate.not(Map.Entry::getValue))
				.map(Map.Entry::getKey)
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
		List<Antipattern> antipatterns = getAntipatterns();
		

		long nrOfAntipatternsFound =  antipatterns.stream()
				.map(antipattern -> compilationUnit.getLineNumber(antipattern.getWrappedElement().getStartPosition()))
				.filter(errorLineMap::containsKey)
				.peek(lineNr -> errorLineMap.replace(lineNr, true))
				.count();

		if(nrOfAntipatternsFound > this.nrOfProblemsTheTestShouldFind) {
			List<Integer> problemLinesFound = getAllProblemLines(antipatterns);
			
			problemLinesFound.removeAll(Arrays.asList(linesWithProblems));
			
			throw new TestingException("more problems found at the following lines: " );
		}

		return nrOfAntipatternsFound == this.nrOfProblemsTheTestShouldFind;
	}
 
	private List<Integer> getAllProblemLines(List<Antipattern> antipatterns) {
		CompilationUnit compilationUnit = UtilityClass.parse(iCompilationUnit);

		return antipatterns.stream()
				.map(Antipattern::getWrappedElement)
				.map(antipattern -> antipattern.getStartPosition())
				.map(compilationUnit::getLineNumber)
				.collect(Collectors.toList());
	}

	protected abstract List<Antipattern> getAntipatterns();

	protected MCompilationUnit getMCompilationUnit() {
		return Factory.getInstance().createMCompilationUnit(iCompilationUnit);
	}

	@After
	public void deleteProject() {
		TestUtil.deleteProject(TEST_PROJECT);
	}
}
