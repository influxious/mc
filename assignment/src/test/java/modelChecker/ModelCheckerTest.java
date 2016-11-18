package modelChecker;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

import tsmodel.TSModel;
import formula.FormulaParser;
import formula.stateFormula.StateFormula;
import modelChecker.ModelChecker;
import modelChecker.SimpleModelChecker;
import model.Model;

public class ModelCheckerTest {

	/*
	 * An example of how to set up and call the model building methods and make
	 * a call to the model checker itself. The contents of model.json,
	 * constraint1.json and ctl.json are just examples, you need to add new
	 * models and formulas for the mutual exclusion task.
	 */

//	@Test
//	public void buildAndCheckModel_atomicP() {
//		try {
//			Model model = Model
//					.parseModel("src/test/resources/modelAtomicP.json");
//			TSModel ts = Model.transform(model);
//			StateFormula fairnessConstraint = new FormulaParser(
//					"src/test/resources/constraint1.json").parse();
//			StateFormula query = new FormulaParser(
//					"src/test/resources/ctlAtomicP.json").parse();
//			ModelChecker mc = new SimpleModelChecker();
//			assertTrue(mc.check(ts, fairnessConstraint, query));
//		} catch (IOException e) {
//			e.printStackTrace();
//			fail(e.toString());
//		}
//	}
//
//	@Test
//	public void buildAndCheckModel_and() {
//		try {
//			Model model = Model
//					.parseModel("src/test/resources/modelAnd.json");
//			TSModel ts = Model.transform(model);
//			StateFormula fairnessConstraint = new FormulaParser(
//					"src/test/resources/constraint1.json").parse();
//			StateFormula query = new FormulaParser(
//					"src/test/resources/ctlAnd.json").parse();
//			ModelChecker mc = new SimpleModelChecker();
//			assertTrue(mc.check(ts, fairnessConstraint, query));
//		} catch (IOException e) {
//			e.printStackTrace();
//			fail(e.toString());
//		}
//	}
//	
//	@Test
//	public void buildAndCheckModel_or() {
//		try {
//			Model model = Model
//					.parseModel("src/test/resources/modelOr.json");
//			TSModel ts = Model.transform(model);
//			StateFormula fairnessConstraint = new FormulaParser(
//					"src/test/resources/constraint1.json").parse();
//			StateFormula query = new FormulaParser(
//					"src/test/resources/ctlOr.json").parse();
//			ModelChecker mc = new SimpleModelChecker();
//			assertTrue(mc.check(ts, fairnessConstraint, query));
//		} catch (IOException e) {
//			e.printStackTrace();
//			fail(e.toString());
//		}
//	}
//	
//	@Test
//	public void buildAndCheckModel_not() {
//		try {
//			Model model = Model
//					.parseModel("src/test/resources/modelNot.json");
//			TSModel ts = Model.transform(model);
//			StateFormula fairnessConstraint = new FormulaParser(
//					"src/test/resources/constraint1.json").parse();
//			StateFormula query = new FormulaParser(
//					"src/test/resources/ctlNot.json").parse();
//			ModelChecker mc = new SimpleModelChecker();
//			assertTrue(mc.check(ts, fairnessConstraint, query));
//		} catch (IOException e) {
//			e.printStackTrace();
//			fail(e.toString());
//		}
//	}
	
	@Test
	public void buildAndCheckModel_forAllAlways() {
		try {
			Model model = Model
					.parseModel("src/test/resources/modelForAllAlways.json");
			TSModel ts = Model.transform(model);
			StateFormula fairnessConstraint = new FormulaParser(
					"src/test/resources/constraint1.json").parse();
			StateFormula query = new FormulaParser(
					"src/test/resources/ctlForAllAlways.json").parse();
			ModelChecker mc = new SimpleModelChecker();
//			mc.printAll(ts);
			assertTrue(mc.check(ts, fairnessConstraint, query));
		} catch (IOException e) {
			e.printStackTrace();
			fail(e.toString());
		}
	}

//	@Test
//	public void buildAndCheckModel() {
//		try {
//			Model model = Model.parseModel("src/test/resources/model1.json");
//			// TSModel ts = Model.transform(model);
//
//			// StateFormula fairnessConstraint = new
//			// FormulaParser("src/test/resources/constraint1.json").parse();
//			// StateFormula query = new
//			// FormulaParser("src/test/resources/ctl2.json").parse();
//			// ModelChecker mc = new SimpleModelChecker();
//
//			// TO IMPLEMENT
//			// assertTrue(mc.check(model, fairnessConstraint, query));
//		} catch (IOException e) {
//			e.printStackTrace();
//			fail(e.toString());
//		}
//	}

//	@Test
//	public void buildAndCheckModel2() {
//		try {
//			Model model = Model.parseModel("src/test/resources/modelForAllAlways.json");
//			TSModel ts = Model.transform(model);
//			
//			
//			// StateFormula fairnessConstraint = new
//			// FormulaParser("src/test/resources/constraint1.json").parse();
//			// System.out.println("Fairness: " + fairnessConstraint.toString());
//			//
//			// StateFormula query = new
//			// FormulaParser("src/test/resources/ctl2.json").parse();
//			// System.out.println("Query: "+query.toString());
//
//			 ModelChecker mc = new SimpleModelChecker();
//			 mc.printAll(ts);
//			 
//			// TO IMPLEMENT
//			// assertTrue(mc.check(model, fairnessConstraint, query));
//		} catch (IOException e) {
//			e.printStackTrace();
//			fail(e.toString());
//		}
//	}

}
