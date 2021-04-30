package projects.properties;

import optionalanalyzer.metamodel.entity.MPackage;
import optionalanalyzer.metamodel.entity.MProject;
import ro.lrg.xcore.metametamodel.IPropertyComputer;
import ro.lrg.xcore.metametamodel.PropertyComputer;

@PropertyComputer
public class LineCounter implements IPropertyComputer<Integer, MProject>{


	@Override
	public Integer compute(MProject arg0) {

		return arg0.packageDetector().getElements()
				.stream()
				.mapToInt(MPackage::lineCounter)
				.sum();
	}
}