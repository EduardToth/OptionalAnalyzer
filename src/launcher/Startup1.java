package launcher;

import org.eclipse.jdt.core.ICompilationUnit;

import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IPackageFragment;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.ui.IStartup;
import org.eclipse.ui.internal.WorkingSet;

import FullAnalysis.Analysis;
import optionalanalyzer.metamodel.factory.Factory;
import ro.lrg.insider.view.ToolRegistration;
import ro.lrg.insider.view.ToolRegistration.XEntityConverter;
import ro.lrg.xcore.metametamodel.XEntity;
import rule10Antipattern.Rule10Antipattern;
import rule12Antipattern.Rule12Antipattern;
import rule13Antipattern.Rule13Antipattern;
import rule14Antipattern.Rule14Antipattern;
import rule15Antipattern.Rule15Antipattern;
import rule16Antipattern.Rule16Antipattern;
import rule17Antipattern.Rule17Antipattern;
import rule18Antipattern.Rule18Antipattern;
import rule19Antipattern.Rule19Antipattern;
import rule20Antipattern.Rule20Antipattern;
import rule21Antipattern.Rule21Antipattern;
import rule25Antipattern.Rule25Antipattern;
import rule26Antipattern.Rule26Antipattern;
import rule_1Antipattern.Rule1Antipattern;
import rule_2Antipattern.Rule2Antipattern;
import rule_3Antipattern.Rule3Antipattern;
import rule_4Antipattern.Rule4Antipattern;
import rule_5Antipattern.Rule5Antipattern;
import rule_6Antipattern.Rule6Antipattern;
import rule_7Antipattern.Rule7Antipattern;
import rule_8Antipattern.Rule8Antipattern;
import rule_9Antipattern.Rule9Antipattern;
import uncategorizedIsPresentUsage.UncategorizedIsPresentAntipattern;

import org.eclipse.core.internal.resources.Project;
import org.eclipse.core.runtime.CoreException;
public class Startup1 implements IStartup {

	@Override
	public void earlyStartup() {
		try {
			ToolRegistration.getInstance().registerXEntityConverter(this::convert);
		}catch(RuntimeException runtimeException) {
			throw new RuntimeException("You may have too many compilation errors in the source selected");
		}

	}

	public XEntity convert(Object element) {

		if(element instanceof Rule1Antipattern) {
			return Factory.getInstance().createMRule1sAntipattern((Rule1Antipattern)element);
		} else if(element instanceof Rule2Antipattern) {
			return Factory.getInstance().createMRule2sAntipattern((Rule2Antipattern)element);
		} else if(element instanceof Rule3Antipattern) {
			return Factory.getInstance().createMRule3sAntipattern((Rule3Antipattern)element);
		} else if(element instanceof Rule4Antipattern) {
			return Factory.getInstance().createMRule4sAntipattern((Rule4Antipattern)element);
		} else if(element instanceof Rule5Antipattern) {
			return Factory.getInstance().createMRule5sAntipattern((Rule5Antipattern)element);
		} else if(element instanceof Rule6Antipattern) {
			return Factory.getInstance().createMRule6sAntipattern((Rule6Antipattern)element);
		} else if(element instanceof Rule7Antipattern) {
			return Factory.getInstance().createMRule7sAntipattern((Rule7Antipattern)element);
		} else if(element instanceof Rule8Antipattern) {
			return Factory.getInstance().createMRule8sAntipattern((Rule8Antipattern)element);
		} else if(element instanceof Rule9Antipattern) {
			return Factory.getInstance().createMRule9sAntipattern((Rule9Antipattern)element);
		} else if(element instanceof Rule10Antipattern) {
			return Factory.getInstance().createMRule10sAntipattern((Rule10Antipattern)element);
		} else if(element instanceof Rule12Antipattern) {
			return Factory.getInstance().createMRule12sAntipattern((Rule12Antipattern)element);
		} else if(element instanceof Rule13Antipattern) {
			return Factory.getInstance().createMRule13sAntipattern((Rule13Antipattern)element);
		} else if(element instanceof Rule14Antipattern) {
			return Factory.getInstance().createMRule14sAntipattern((Rule14Antipattern)element);
		} else if(element instanceof Rule15Antipattern) {
			return Factory.getInstance().createMRule15sAntipattern((Rule15Antipattern)element);
		} else if(element instanceof Rule16Antipattern) {
			return Factory.getInstance().createMRule16sAntipattern((Rule16Antipattern)element);
		} else if(element instanceof Rule17Antipattern) {
			return Factory.getInstance().createMRule17sAntipattern((Rule17Antipattern)element);
		} else if(element instanceof Rule18Antipattern) {
			return Factory.getInstance().createMRule18sAntipattern((Rule18Antipattern)element);
		} else if(element instanceof Rule19Antipattern) {
			return Factory.getInstance().createMRule19sAntipattern((Rule19Antipattern)element);
		} else if(element instanceof Rule20Antipattern) {
			return Factory.getInstance().createMRule20sAntipattern((Rule20Antipattern)element);
		} else if(element instanceof Rule21Antipattern) {
			return Factory.getInstance().createMRule21sAntipattern((Rule21Antipattern)element);
		} else if(element instanceof Rule25Antipattern) {
			return Factory.getInstance().createMRule25sAntipattern((Rule25Antipattern)element);
		} else if(element instanceof Rule26Antipattern) {
			return Factory.getInstance().createMRule26sAntipattern((Rule26Antipattern)element);
		} else if(element instanceof ICompilationUnit) {
			return Factory.getInstance().createMCompilationUnit((ICompilationUnit)element);
		} else if(element instanceof IJavaProject) {
			return Factory.getInstance().createMProject((IJavaProject)element);
		} else if(element instanceof Analysis) {
			return Factory.getInstance().createMAnalysis((Analysis)element);
		}  else if(element instanceof UncategorizedIsPresentAntipattern) {
			return Factory.getInstance().createMUncategorizedIsPresentPossibleAntipattern((UncategorizedIsPresentAntipattern)element);
		}  else if(element instanceof WorkingSet) {
			return Factory.getInstance().createMWorkingSet((WorkingSet)element);
		}  else if(element instanceof IPackageFragment) {
			return Factory.getInstance().createMPackage((IPackageFragment)element);
		}  else if(element instanceof Project) {
			try {
				IJavaProject javaPoject = JavaCore.create((Project)element);
				return Factory.getInstance().createMProject(javaPoject);
			} catch(Exception exception) {
				exception.printStackTrace();
			}
		}
		return null;
	}
}