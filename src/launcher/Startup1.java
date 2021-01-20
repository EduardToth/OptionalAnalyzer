package launcher;

import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.ui.IStartup;
import org.eclipse.ui.internal.WorkingSet;

import FullAnalysis.Analysis;
import optionalanalizer.metamodel.factory.Factory;
import ro.lrg.insider.view.ToolRegistration;
import ro.lrg.insider.view.ToolRegistration.XEntityConverter;
import ro.lrg.xcore.metametamodel.XEntity;
import rule10Antipattern.Rule10Atom;
import rule12Antipattern.Rule12Atom;
import rule13Antipattern.Rule13Atom;
import rule14Antipattern.Rule14Atom;
import rule15Antipattern.Rule15Atom;
import rule16Antipattern.Rule16Atom;
import rule17Antipattern.Rule17Atom;
import rule18Antipattern.Rule18Atom;
import rule19Antipattern.Rule19Atom;
import rule20Antipattern.Rule20Atom;
import rule21Antipattern.Rule21Atom;
import rule25Antipattern.Rule25Atom;
import rule26Antipattern.Rule26Atom;
import rule_1Antipattern.Rule1Atom;
import rule_2Antipattern.Rule2Atom;
import rule_3Antipattern.Rule3Atom;
import rule_4Antipattern.Rule4Atom;
import rule_5Antipattern.Rule5Atom;
import rule_6Antipattern.Rule6Atom;
import rule_7Antipattern.Rule7Atom;
import rule_8Antipattern.Rule8Atom;
import rule_9Antipattern.Rule9Atom;
import uncategorizedIsPresentUsage.UncategorizedIsPresentAtom;

import org.eclipse.core.internal.resources.Project;
import org.eclipse.core.runtime.CoreException;
public class Startup1 implements IStartup {

	@Override
	public void earlyStartup() {
		ToolRegistration.getInstance().registerXEntityConverter(

				new XEntityConverter() {
					@Override
					public XEntity convert(Object element) {
						if(element instanceof Rule1Atom) {
							return Factory.getInstance().createMRule1Atom((Rule1Atom)element);
						} else if(element instanceof Rule2Atom) {
							return Factory.getInstance().createMRule2Atom((Rule2Atom)element);
						} else if(element instanceof Rule3Atom) {
							return Factory.getInstance().createMRule3Atom((Rule3Atom)element);
						} else if(element instanceof Rule4Atom) {
							return Factory.getInstance().createMRule4Atom((Rule4Atom)element);
						} else if(element instanceof Rule5Atom) {
							return Factory.getInstance().createMRule5Atom((Rule5Atom)element);
						} else if(element instanceof Rule6Atom) {
							return Factory.getInstance().createMRule6Atom((Rule6Atom)element);
						} else if(element instanceof Rule7Atom) {
							return Factory.getInstance().createMRule7Atom((Rule7Atom)element);
						} else if(element instanceof Rule8Atom) {
							return Factory.getInstance().createMRule8Atom((Rule8Atom)element);
						} else if(element instanceof Rule9Atom) {
							return Factory.getInstance().createMRule9Atom((Rule9Atom)element);
						} else if(element instanceof Rule10Atom) {
							return Factory.getInstance().createMRule10Atom((Rule10Atom)element);
						} else if(element instanceof Rule12Atom) {
							return Factory.getInstance().createMRule12Atom((Rule12Atom)element);
						} else if(element instanceof Rule13Atom) {
							return Factory.getInstance().createMRule13Atom((Rule13Atom)element);
						} else if(element instanceof Rule14Atom) {
							return Factory.getInstance().createMRule14Atom((Rule14Atom)element);
						} else if(element instanceof Rule15Atom) {
							return Factory.getInstance().createMRule15Atom((Rule15Atom)element);
						} else if(element instanceof Rule16Atom) {
							return Factory.getInstance().createMRule16Atom((Rule16Atom)element);
						} else if(element instanceof Rule17Atom) {
							return Factory.getInstance().createMRule17Atom((Rule17Atom)element);
						} else if(element instanceof Rule18Atom) {
							return Factory.getInstance().createMRule18Atom((Rule18Atom)element);
						} else if(element instanceof Rule19Atom) {
							return Factory.getInstance().createMRule19Atom((Rule19Atom)element);
						} else if(element instanceof Rule20Atom) {
							return Factory.getInstance().createMRule20Atom((Rule20Atom)element);
						} else if(element instanceof Rule21Atom) {
							return Factory.getInstance().createMRule21Atom((Rule21Atom)element);
						} else if(element instanceof Rule25Atom) {
							return Factory.getInstance().createMRule25Atom((Rule25Atom)element);
						} else if(element instanceof Rule26Atom) {
							return Factory.getInstance().createMRule26Atom((Rule26Atom)element);
						} else if(element instanceof ICompilationUnit) {
							return Factory.getInstance().createMCompilationUnit((ICompilationUnit)element);
						} else if(element instanceof IJavaProject) {
							return Factory.getInstance().createMProject((IJavaProject)element);
						} else if(element instanceof Analysis) {
							return Factory.getInstance().createMAnalysis((Analysis)element);
						}  else if(element instanceof UncategorizedIsPresentAtom) {
							return Factory.getInstance().createMUncategorizedIsPresentAtom((UncategorizedIsPresentAtom)element);
						}  else if(element instanceof WorkingSet) {
							return Factory.getInstance().createMWorkingSet((WorkingSet)element);
						} 
						System.out.println("-----------> " + element.getClass());
						return null;
					}
				}
				);
	}
}