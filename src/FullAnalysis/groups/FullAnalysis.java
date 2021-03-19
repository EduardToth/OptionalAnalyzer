package FullAnalysis.groups;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.javatuples.Pair;

import FullAnalysis.Analysis;
import optionalanalyzer.metamodel.entity.MAnalysis;
import optionalanalyzer.metamodel.entity.MCompilationUnit;
import optionalanalyzer.metamodel.entity.MRule10sAntipattern;
import optionalanalyzer.metamodel.entity.MRule12sAntipattern;
import optionalanalyzer.metamodel.entity.MRule13sAntipattern;
import optionalanalyzer.metamodel.entity.MRule14sAntipattern;
import optionalanalyzer.metamodel.entity.MRule15sAntipattern;
import optionalanalyzer.metamodel.entity.MRule16sAntipattern;
import optionalanalyzer.metamodel.entity.MRule17sAntipattern;
import optionalanalyzer.metamodel.entity.MRule18sAntipattern;
import optionalanalyzer.metamodel.entity.MRule19sAntipattern;
import optionalanalyzer.metamodel.entity.MRule1sAntipattern;
import optionalanalyzer.metamodel.entity.MRule20sAntipattern;
import optionalanalyzer.metamodel.entity.MRule21sAntipattern;
import optionalanalyzer.metamodel.entity.MRule25sAntipattern;
import optionalanalyzer.metamodel.entity.MRule26sAntipattern;
import optionalanalyzer.metamodel.entity.MRule2sAntipattern;
import optionalanalyzer.metamodel.entity.MRule3sAntipattern;
import optionalanalyzer.metamodel.entity.MRule4sAntipattern;
import optionalanalyzer.metamodel.entity.MRule5sAntipattern;
import optionalanalyzer.metamodel.entity.MRule6sAntipattern;
import optionalanalyzer.metamodel.entity.MRule7sAntipattern;
import optionalanalyzer.metamodel.entity.MRule8sAntipattern;
import optionalanalyzer.metamodel.entity.MRule9sAntipattern;
import optionalanalyzer.metamodel.entity.MUncategorizedIsPresentPossibleAntipattern;
import optionalanalyzer.metamodel.factory.Factory;
import ro.lrg.xcore.metametamodel.Group;
import ro.lrg.xcore.metametamodel.IRelationBuilder;
import ro.lrg.xcore.metametamodel.RelationBuilder;
import ro.lrg.xcore.metametamodel.XEntity;

@RelationBuilder
public class FullAnalysis implements IRelationBuilder<MAnalysis, MCompilationUnit>{

	@Override
	public Group<MAnalysis> buildGroup(MCompilationUnit arg0) {

		 List<MAnalysis> result = getMAntipatterns(arg0)
				.map(this::getUnderLyingObject)
				.map(this::convertInEssentialInfo)
				.map(pair -> new Analysis(pair.getValue0(), pair.getValue1()))
				.distinct()
				.map(Factory.getInstance()::createMAnalysis)
				.collect(Collectors.toList());
		

		Group<MAnalysis> resGroup = new Group<MAnalysis>();
		resGroup.addAll(result);

		return resGroup;
	}

	private Pair<String, String> convertInEssentialInfo(Object antipattern) {

		String className = antipattern.getClass().getName();
		String antipatternInfo = antipattern.toString();

		return new Pair<String, String>(className, antipatternInfo);
	}

	private Object getUnderLyingObject(XEntity xEntity) {

		if(xEntity instanceof MRule1sAntipattern) {
			return ((MRule1sAntipattern)xEntity).getUnderlyingObject();
		}else if(xEntity instanceof MRule2sAntipattern) {
			return ((MRule2sAntipattern)xEntity).getUnderlyingObject();
		} else if(xEntity instanceof MRule3sAntipattern) {
			return ((MRule3sAntipattern)xEntity).getUnderlyingObject();
		}else if(xEntity instanceof MRule4sAntipattern) {
			return ((MRule4sAntipattern)xEntity).getUnderlyingObject();
		} else if(xEntity instanceof MRule5sAntipattern) {
			return ((MRule5sAntipattern)xEntity).getUnderlyingObject();
		}else if(xEntity instanceof MRule6sAntipattern) {
			return ((MRule6sAntipattern)xEntity).getUnderlyingObject();
		}else if(xEntity instanceof MRule7sAntipattern) {
			return ((MRule7sAntipattern)xEntity).getUnderlyingObject();
		}else if(xEntity instanceof MRule8sAntipattern) {
			return ((MRule8sAntipattern)xEntity).getUnderlyingObject();
		}else if(xEntity instanceof MRule9sAntipattern) {
			return ((MRule9sAntipattern)xEntity).getUnderlyingObject();
		}else if(xEntity instanceof MRule10sAntipattern) {
			return ((MRule10sAntipattern)xEntity).getUnderlyingObject();
		}else if(xEntity instanceof MRule12sAntipattern) {
			return ((MRule12sAntipattern)xEntity).getUnderlyingObject();
		}else if(xEntity instanceof MRule13sAntipattern) {
			return ((MRule13sAntipattern)xEntity).getUnderlyingObject();
		}else if(xEntity instanceof MRule14sAntipattern) {
			return ((MRule14sAntipattern)xEntity).getUnderlyingObject();
		}else if(xEntity instanceof MRule15sAntipattern) {
			return ((MRule15sAntipattern)xEntity).getUnderlyingObject();
		}else if(xEntity instanceof MRule16sAntipattern) {
			return ((MRule16sAntipattern)xEntity).getUnderlyingObject();
		}else if(xEntity instanceof MRule17sAntipattern) {
			return ((MRule17sAntipattern)xEntity).getUnderlyingObject();
		}else if(xEntity instanceof MRule18sAntipattern) {
			return ((MRule18sAntipattern)xEntity).getUnderlyingObject();
		}else if(xEntity instanceof MRule19sAntipattern) {
			return ((MRule19sAntipattern)xEntity).getUnderlyingObject();
		}else if(xEntity instanceof MRule20sAntipattern) {
			return ((MRule20sAntipattern)xEntity).getUnderlyingObject();
		}else if(xEntity instanceof MRule21sAntipattern) {
			return ((MRule21sAntipattern)xEntity).getUnderlyingObject();
		}else if(xEntity instanceof MRule25sAntipattern) {
			return ((MRule25sAntipattern)xEntity).getUnderlyingObject();
		}else if(xEntity instanceof MRule26sAntipattern) {
			return ((MRule26sAntipattern)xEntity).getUnderlyingObject();
		}else if(xEntity instanceof MUncategorizedIsPresentPossibleAntipattern) {
			return ((MUncategorizedIsPresentPossibleAntipattern)xEntity).getUnderlyingObject();
		}
		return null;
	}
	
	private Stream<? extends XEntity> getMAntipatterns(MCompilationUnit arg0) {
		return Stream.of(arg0.rule_1AntipatternDetector(),
				arg0.rule_2AntipatternDetector(),
				arg0.rule_3AntipatternDetector(),
				arg0.rule_4AntipatternDetector(),
				arg0.rule_5AntipatternDetector(),
				arg0.rule_6AntipatternDetector(),
				arg0.rule_7AntipatternDetector(),
				arg0.rule_8AntipatternDetector(),
				arg0.rule_9AntipatternDetector(),
				arg0.rule10AntipatternDetector(),
				arg0.rule12AntipatternDetector(),
				arg0.rule13AntipatternDetector(),
				arg0.rule14AntipatternDetector(),
				arg0.rule15AntipatternDetector(),
				arg0.rule16AntipatternDetector(),
				arg0.rule17AntipatternDetector(),
				arg0.rule18AntipatternDetector(),
				arg0.rule19AntipatternDetector(),
				arg0.rule20AntipatternDetector(),
				arg0.rule21AntipatternDetector(),
				arg0.rule25AntipatternDetector(),
				arg0.rule26AntipatternDetector(),
				arg0.uncategorizedIsPresentInvocationBasedAntipatternDetector()
				).flatMap(group -> group.getElements().stream());
	}
}
