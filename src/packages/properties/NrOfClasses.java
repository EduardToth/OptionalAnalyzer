package packages.properties;

import optionalanalyzer.metamodel.entity.MCompilationUnit;
import optionalanalyzer.metamodel.entity.MPackage;
import ro.lrg.xcore.metametamodel.IPropertyComputer;
import ro.lrg.xcore.metametamodel.PropertyComputer;


@PropertyComputer
public class NrOfClasses implements IPropertyComputer<Integer, MPackage>{


	@Override
	public Integer compute(MPackage arg0) {

		return arg0.compilationUnitDetector()
				.getElements()
				.stream()
				.mapToInt(MCompilationUnit::nrOfClasses)
				.sum();
	}
}