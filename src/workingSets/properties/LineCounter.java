package workingSets.properties;

import optionalanalyzer.metamodel.entity.MProject;
import optionalanalyzer.metamodel.entity.MWorkingSet;
import ro.lrg.xcore.metametamodel.IPropertyComputer;
import ro.lrg.xcore.metametamodel.PropertyComputer;

@PropertyComputer
public class LineCounter implements IPropertyComputer<Integer, MWorkingSet>{

	@Override
	public Integer compute(MWorkingSet arg0) {
		return arg0.getComponentProjects()
				.getElements()
				.stream()
				.mapToInt(MProject::lineCounter)
				.sum();
	}


}