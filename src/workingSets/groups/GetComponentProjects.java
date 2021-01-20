package workingSets.groups;



import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.ui.internal.WorkingSet;

import optionalanalizer.metamodel.entity.MProject;
import optionalanalizer.metamodel.entity.MWorkingSet;
import optionalanalizer.metamodel.factory.Factory;
import ro.lrg.xcore.metametamodel.Group;
import ro.lrg.xcore.metametamodel.IRelationBuilder;
import ro.lrg.xcore.metametamodel.RelationBuilder;

@RelationBuilder
public class GetComponentProjects implements IRelationBuilder<MProject, MWorkingSet>{

	@Override
	public Group<MProject> buildGroup(MWorkingSet arg0) {

		final WorkingSet workingSet = (WorkingSet) arg0.getUnderlyingObject();
		final IAdaptable[] adaptables = workingSet.getElements();

		List<MProject> mProjects = Arrays.asList(adaptables)
				.stream()
				.map(adaptable -> adaptable.getAdapter(IResource.class))
				.map(IResource::getProject)
				.map(JavaCore::create)
				.map(Factory.getInstance()::createMProject)
				.collect(Collectors.toList());

		Group<MProject> group = new Group<>();
		
	
		group.addAll(mProjects);
		
		return group;
	}
}
