package tests;

import org.junit.Test;

public class TestRunner {

	@Test
	public void run() {
		try {
			Rule_1Tests rule_1Tests = new Rule_1Tests();
			Rule_2Tests rule_2Tests = new Rule_2Tests();
			Rule_3Tests rule_3Tests = new Rule_3Tests();
			Rule_4Tests rule_4Tests = new Rule_4Tests();
			Rule_5Tests rule_5Tests = new Rule_5Tests();
			Rule_6Tests rule_6Tests = new Rule_6Tests();
			Rule_7Tests rule_7Tests = new Rule_7Tests();
			Rule_8Tests rule_8Tests = new Rule_8Tests();
			Rule_9Tests rule_9Tests = new Rule_9Tests();
			Rule10Tests rule10Tests = new Rule10Tests();
			Rule12Tests rule11Tests = new Rule12Tests();
			Rule14Tests rule14Tests = new Rule14Tests();
			Rule15Tests rule15Tests = new Rule15Tests();
			Rule16Tests rule16Tests = new Rule16Tests();
			Rule17Tests rule17Tests = new Rule17Tests();
			Rule18Tests rule18Tests = new Rule18Tests();
			Rule19Tests rule19Tests = new Rule19Tests();
			Rule20Tests rule20Tests = new Rule20Tests();
			Rule21Tests rule21Tests = new Rule21Tests();
			Rule25Tests rule25Tests = new Rule25Tests();
			Rule26Tests rule26Tests = new Rule26Tests();

			rule_1Tests.test();
			rule_2Tests.test();
			rule_3Tests.test();
			rule_4Tests.test();
			rule_5Tests.test();
			rule_6Tests.test();
			rule_7Tests.test();
			rule_8Tests.test();
			rule_9Tests.test();
			rule10Tests.test();
			rule11Tests.test();
			rule14Tests.test();
			rule15Tests.test();
			rule16Tests.test();
			rule17Tests.test();
			rule18Tests.test();
			rule19Tests.test();
			rule20Tests.test();
			rule21Tests.test();
			rule25Tests.test();
			rule26Tests.test();
		} catch (BadNamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		//		TestBaseClass[] testObjects = new TestBaseClass[21];
		//		try {
		//			testObjects[ 0 ] = new Rule_1Tests();
		//			testObjects[ 1 ] = new Rule_2Tests();
		//			testObjects[ 2 ] = new Rule_3Tests();
		//			testObjects[ 3 ] = new Rule_4Tests();
		//			testObjects[ 4 ] = new Rule_5Tests();
		//			testObjects[ 5 ] = new Rule_6Tests();
		//			testObjects[ 6 ] = new Rule_7Tests();
		//			testObjects[ 7 ] = new Rule_8Tests();
		//			testObjects[ 8 ] = new Rule_9Tests();
		//			testObjects[ 9 ] = new Rule10Tests();
		//			testObjects[ 10 ] = new Rule12Tests();
		//			testObjects[ 11 ] = new Rule14Tests();
		//			testObjects[ 12 ] = new Rule15Tests();
		//			testObjects[ 13 ] = new Rule16Tests();
		//			testObjects[ 14 ] = new Rule17Tests();
		//			testObjects[ 15 ] = new Rule18Tests();
		//			testObjects[ 16 ] = new Rule19Tests();
		//			testObjects[ 17 ] = new Rule20Tests();
		//			testObjects[ 18 ] = new Rule21Tests();
		//			testObjects[ 19 ] = new Rule25Tests();
		//			testObjects[ 20 ] = new Rule26Tests();
		//		}catch(BadNamingException e) {
		//			e.printStackTrace();
		//		}
		//		int i=0;
		//		for(; i < 21; ) {
		//			try {
		//				testObjects[ i ].test();
		//				i++;			
		//			}catch(ClassCastException cce) {
		//				System.out.println("Pe aici...");
		//			}
		//		}
	}
	
	public static void main(String[] args) {
		for(int i=0; i < 100; i++) {
			System.out.println("Heloooo");
		}
	}
}
