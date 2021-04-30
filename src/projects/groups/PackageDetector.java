package projects.groups;


import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IPackageFragment;
import org.eclipse.jdt.core.JavaModelException;

import optionalanalyzer.metamodel.entity.MPackage;
import optionalanalyzer.metamodel.entity.MProject;
import optionalanalyzer.metamodel.factory.Factory;
import ro.lrg.xcore.metametamodel.Group;
import ro.lrg.xcore.metametamodel.IRelationBuilder;
import ro.lrg.xcore.metametamodel.RelationBuilder;

@RelationBuilder
public class PackageDetector implements IRelationBuilder<MPackage, MProject>{

	@Override
	public Group<MPackage> buildGroup(MProject arg0) {

		IJavaProject iJavaProject = ((IJavaProject)arg0.getUnderlyingObject());

		Group<MPackage> group = new Group<>();

		getPackages(iJavaProject).stream()
		.map(Factory.getInstance()::createMPackage)
		.forEach(group::add);

		return group;
	}

	private List<IPackageFragment> getPackages(IJavaProject iJavaProject) {
		try {
			IPackageFragment[] iPackageFragments = iJavaProject.getPackageFragments();

			return Arrays.stream(iPackageFragments)
					.filter(Predicate.not(IPackageFragment::isReadOnly))
					.collect(Collectors.toList());
		} catch (JavaModelException e) {
			e.printStackTrace();
		}
		
		return Collections.emptyList();
	}

}
