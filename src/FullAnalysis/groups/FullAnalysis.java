package FullAnalysis.groups;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.javatuples.Pair;

import FullAnalysis.Analysis;
import optionalanalizer.metamodel.entity.MAnalysis;
import optionalanalizer.metamodel.entity.MCompilationUnit;
import optionalanalizer.metamodel.entity.MRule10Atom;
import optionalanalizer.metamodel.entity.MRule12Atom;
import optionalanalizer.metamodel.entity.MRule13Atom;
import optionalanalizer.metamodel.entity.MRule14Atom;
import optionalanalizer.metamodel.entity.MRule15Atom;
import optionalanalizer.metamodel.entity.MRule16Atom;
import optionalanalizer.metamodel.entity.MRule17Atom;
import optionalanalizer.metamodel.entity.MRule18Atom;
import optionalanalizer.metamodel.entity.MRule19Atom;
import optionalanalizer.metamodel.entity.MRule1Atom;
import optionalanalizer.metamodel.entity.MRule20Atom;
import optionalanalizer.metamodel.entity.MRule21Atom;
import optionalanalizer.metamodel.entity.MRule25Atom;
import optionalanalizer.metamodel.entity.MRule26Atom;
import optionalanalizer.metamodel.entity.MRule2Atom;
import optionalanalizer.metamodel.entity.MRule3Atom;
import optionalanalizer.metamodel.entity.MRule4Atom;
import optionalanalizer.metamodel.entity.MRule5Atom;
import optionalanalizer.metamodel.entity.MRule6Atom;
import optionalanalizer.metamodel.entity.MRule7Atom;
import optionalanalizer.metamodel.entity.MRule8Atom;
import optionalanalizer.metamodel.entity.MRule9Atom;
import optionalanalizer.metamodel.entity.MUncategorizedIsPresentAtom;
import optionalanalizer.metamodel.factory.Factory;
import ro.lrg.xcore.metametamodel.Group;
import ro.lrg.xcore.metametamodel.IRelationBuilder;
import ro.lrg.xcore.metametamodel.RelationBuilder;
import ro.lrg.xcore.metametamodel.XEntity;

@RelationBuilder
public class FullAnalysis implements IRelationBuilder<MAnalysis, MCompilationUnit>{

	@Override
	public Group<MAnalysis> buildGroup(MCompilationUnit arg0) {

		 List<MAnalysis> result = getMAtoms(arg0)
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

	private Pair<String, String> convertInEssentialInfo(Object atom) {

		String className = atom.getClass().getName();
		String antipatternInfo = atom.toString();

		return new Pair<String, String>(className, antipatternInfo);
	}

	private Object getUnderLyingObject(XEntity xEntity) {

		if(xEntity instanceof MRule1Atom) {
			return ((MRule1Atom)xEntity).getUnderlyingObject();
		}else if(xEntity instanceof MRule2Atom) {
			return ((MRule2Atom)xEntity).getUnderlyingObject();
		} else if(xEntity instanceof MRule3Atom) {
			return ((MRule3Atom)xEntity).getUnderlyingObject();
		}else if(xEntity instanceof MRule4Atom) {
			return ((MRule4Atom)xEntity).getUnderlyingObject();
		} else if(xEntity instanceof MRule5Atom) {
			return ((MRule5Atom)xEntity).getUnderlyingObject();
		}else if(xEntity instanceof MRule6Atom) {
			return ((MRule6Atom)xEntity).getUnderlyingObject();
		}else if(xEntity instanceof MRule7Atom) {
			return ((MRule7Atom)xEntity).getUnderlyingObject();
		}else if(xEntity instanceof MRule8Atom) {
			return ((MRule8Atom)xEntity).getUnderlyingObject();
		}else if(xEntity instanceof MRule9Atom) {
			return ((MRule9Atom)xEntity).getUnderlyingObject();
		}else if(xEntity instanceof MRule10Atom) {
			return ((MRule10Atom)xEntity).getUnderlyingObject();
		}else if(xEntity instanceof MRule12Atom) {
			return ((MRule12Atom)xEntity).getUnderlyingObject();
		}else if(xEntity instanceof MRule13Atom) {
			return ((MRule13Atom)xEntity).getUnderlyingObject();
		}else if(xEntity instanceof MRule14Atom) {
			return ((MRule14Atom)xEntity).getUnderlyingObject();
		}else if(xEntity instanceof MRule15Atom) {
			return ((MRule15Atom)xEntity).getUnderlyingObject();
		}else if(xEntity instanceof MRule16Atom) {
			return ((MRule16Atom)xEntity).getUnderlyingObject();
		}else if(xEntity instanceof MRule17Atom) {
			return ((MRule17Atom)xEntity).getUnderlyingObject();
		}else if(xEntity instanceof MRule18Atom) {
			return ((MRule18Atom)xEntity).getUnderlyingObject();
		}else if(xEntity instanceof MRule19Atom) {
			return ((MRule19Atom)xEntity).getUnderlyingObject();
		}else if(xEntity instanceof MRule20Atom) {
			return ((MRule20Atom)xEntity).getUnderlyingObject();
		}else if(xEntity instanceof MRule21Atom) {
			return ((MRule21Atom)xEntity).getUnderlyingObject();
		}else if(xEntity instanceof MRule25Atom) {
			return ((MRule25Atom)xEntity).getUnderlyingObject();
		}else if(xEntity instanceof MRule26Atom) {
			return ((MRule26Atom)xEntity).getUnderlyingObject();
		}else if(xEntity instanceof MUncategorizedIsPresentAtom) {
			return ((MUncategorizedIsPresentAtom)xEntity).getUnderlyingObject();
		}
		return null;
	}
	
	private Stream<? extends XEntity> getMAtoms(MCompilationUnit arg0) {
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
