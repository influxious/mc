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


	@Test
	public void buildAndCheckModel_atomicP() {
		try {
			Model model = Model
					.parseModel("src/test/resources/my-examples/model/modelAtomicP.json");
			TSModel ts = Model.transform(model);
			StateFormula fairnessConstraint = new FormulaParser(
					"src/test/resources/constraint1.json").parse();
			StateFormula query = new FormulaParser(
					"src/test/resources/my-examples/ctl/ctlAtomicP.json").parse();
			ModelChecker mc = new SimpleModelChecker();
			assertTrue(mc.check(ts, fairnessConstraint, query));
		} catch (IOException e) {
			e.printStackTrace();
			fail(e.toString());
		}
	}


	@Test
	public void buildAndCheckModel_and() {
		try {
			Model model = Model
					.parseModel("src/test/resources/my-examples/model/modelAnd.json");
			TSModel ts = Model.transform(model);
			StateFormula fairnessConstraint = new FormulaParser(
					"src/test/resources/constraint1.json").parse();
			StateFormula query = new FormulaParser(
					"src/test/resources/my-examples/ctl/ctlAnd.json").parse();
			ModelChecker mc = new SimpleModelChecker();
			assertTrue(mc.check(ts, fairnessConstraint, query));
		} catch (IOException e) {
			e.printStackTrace();
			fail(e.toString());
		}
	}

	@Test
	public void buildAndCheckModel_or() {
		try {
			Model model = Model
					.parseModel("src/test/resources/my-examples/model/modelOr.json");
			TSModel ts = Model.transform(model);
			StateFormula fairnessConstraint = new FormulaParser(
					"src/test/resources/constraint1.json").parse();
			StateFormula query = new FormulaParser(
					"src/test/resources/my-examples/ctl/ctlOr.json").parse();
			ModelChecker mc = new SimpleModelChecker();
			assertTrue(mc.check(ts, fairnessConstraint, query));
		} catch (IOException e) {
			e.printStackTrace();
			fail(e.toString());
		}
	}


	@Test
	public void buildAndCheckModel_not() {
		try {
			Model model = Model
					.parseModel("src/test/resources/my-examples/model/modelNot.json");
			TSModel ts = Model.transform(model);
			StateFormula fairnessConstraint = new FormulaParser(
					"src/test/resources/constraint1.json").parse();
			StateFormula query = new FormulaParser(
					"src/test/resources/my-examples/ctl/ctlNot.json").parse();
			ModelChecker mc = new SimpleModelChecker();
			assertTrue(mc.check(ts, fairnessConstraint, query));
		} catch (IOException e) {
			e.printStackTrace();
			fail(e.toString());
		}
	}


	@Test
	public void buildAndCheckModel_forAllAlways() {
		try {
			Model model = Model
					.parseModel("src/test/resources/my-examples/model/modelForAllAlways.json");
			TSModel ts = Model.transform(model);
			StateFormula fairnessConstraint = new FormulaParser(
					"src/test/resources/my-examples/constraint/cForAllAlways.json").parse();
			StateFormula query = new FormulaParser(
					"src/test/resources/my-examples/ctl/ctlForAllAlways.json").parse();
			ModelChecker mc = new SimpleModelChecker();
			assertTrue(mc.check(ts, fairnessConstraint, query));
		} catch (IOException e) {
			e.printStackTrace();
			fail(e.toString());
		}
	}


	@Test
	public void buildAndCheckModel_thereExistsAlways() {
		try {
			Model model = Model
					.parseModel("src/test/resources/my-examples/model/modelThereExistsAlways.json");
			TSModel ts = Model.transform(model);
			StateFormula fairnessConstraint = new FormulaParser(
					"src/test/resources/my-examples/constraint/cThereExistsAlways.json").parse();
			StateFormula query = new FormulaParser(
					"src/test/resources/my-examples/ctl/ctlThereExistsAlways.json").parse();
			ModelChecker mc = new SimpleModelChecker();
			assertTrue(mc.check(ts, fairnessConstraint, query));
		} catch (IOException e) {
			e.printStackTrace();
			fail(e.toString());
		}
	}

	@Test
	public void buildAndCheckModel_forAllEventually() {
		try {
			Model model = Model
					.parseModel("src/test/resources/my-examples/model/modelForAllEventually.json");
			TSModel ts = Model.transform(model);
			StateFormula fairnessConstraint = new FormulaParser(
					"src/test/resources/my-examples/constraint/cForAllEventually.json").parse();
			StateFormula query = new FormulaParser(
					"src/test/resources/my-examples/ctl/ctlForAllEventually.json").parse();
			ModelChecker mc = new SimpleModelChecker();
			assertTrue(mc.check(ts, fairnessConstraint, query));
		} catch (IOException e) {
			e.printStackTrace();
			fail(e.toString());
		}
	}

	@Test
	public void buildAndCheckModel_thereExistsEventually() {
		try {
			Model model = Model
					.parseModel("src/test/resources/my-examples/model/modelThereExistsEventually.json");
			TSModel ts = Model.transform(model);
			StateFormula fairnessConstraint = new FormulaParser(
					"src/test/resources/my-examples/constraint/cThereExistsEventually.json").parse();
			StateFormula query = new FormulaParser(
					"src/test/resources/my-examples/ctl/ctlThereExistsEventually.json").parse();
			ModelChecker mc = new SimpleModelChecker();
			assertTrue(mc.check(ts, fairnessConstraint, query));

		} catch (IOException e) {
			e.printStackTrace();
			fail(e.toString());
		}
	}

	@Test
	public void buildAndCheckModel_forAllUntil() {
		try {
			Model model = Model
					.parseModel("src/test/resources/my-examples/model/modelForAllUntil.json");
			TSModel ts = Model.transform(model);
			StateFormula fairnessConstraint = new FormulaParser(
					"src/test/resources/my-examples/constraint/cForAllUntil.json").parse();
			StateFormula query = new FormulaParser(
					"src/test/resources/my-examples/ctl/ctlForAllUntil.json").parse();
			ModelChecker mc = new SimpleModelChecker();
			assertTrue(mc.check(ts, fairnessConstraint, query));
		} catch (IOException e) {
			e.printStackTrace();
			fail(e.toString());
		}
	}

	@Test
	public void buildAndCheckModel_thereExistsUntil() {
		try {
			Model model = Model
					.parseModel("src/test/resources/my-examples/model/modelThereExistsUntil.json");
			TSModel ts = Model.transform(model);
			StateFormula fairnessConstraint = new FormulaParser(
					"src/test/resources/my-examples/constraint/cThereExistsUntil.json").parse();
			StateFormula query = new FormulaParser(
					"src/test/resources/my-examples/ctl/ctlThereExistsUntil.json").parse();
			ModelChecker mc = new SimpleModelChecker();
			assertTrue(mc.check(ts, fairnessConstraint, query));
		} catch (IOException e) {
			e.printStackTrace();
			fail(e.toString());
		}
	}

	@Test
	public void buildAndCheckModel_forAllNext() {
		try {
			Model model = Model
					.parseModel("src/test/resources/my-examples/model/modelForAllNext.json");
			TSModel ts = Model.transform(model);
			StateFormula fairnessConstraint = new FormulaParser(
					"src/test/resources/my-examples/constraint/cForAllNext.json").parse();
			StateFormula query = new FormulaParser(
					"src/test/resources/my-examples/ctl/ctlForAllNext.json").parse();
			ModelChecker mc = new SimpleModelChecker();
			assertTrue(mc.check(ts, fairnessConstraint, query));
		} catch (IOException e) {
			e.printStackTrace();
			fail(e.toString());
		}
	}

	@Test
	public void buildAndCheckModel_thereExistsNext() {
		try {
			Model model = Model
					.parseModel("src/test/resources/my-examples/model/modelThereExistsNext.json");
			TSModel ts = Model.transform(model);
			StateFormula fairnessConstraint = new FormulaParser(
					"src/test/resources/my-examples/constraint/cThereExistsNext.json").parse();
			StateFormula query = new FormulaParser(
					"src/test/resources/my-examples/ctl/ctlThereExistsNext.json").parse();
			ModelChecker mc = new SimpleModelChecker();
			assertTrue(mc.check(ts, fairnessConstraint, query));
		} catch (IOException e) {
			e.printStackTrace();
			fail(e.toString());
		}
	}


	/*
	@Test
	public void buildAndCheckModel_examples() {
		try {
			Model model = Model.parseModel("src/test/resources/model2.json");
			TSModel ts = Model.transform(model);

			 StateFormula fairnessConstraint = new
			 FormulaParser("src/test/resources/constraint1.json").parse();
			 StateFormula query = new
			 FormulaParser("src/test/resources/ctl2.json").parse();
			 ModelChecker mc = new SimpleModelChecker();
			 assertTrue(mc.check(ts, fairnessConstraint, query));

		} catch (IOException e) {
			e.printStackTrace();
			fail(e.toString());
		}
	}


	
	
	*/

	// New:
	@Test
	public void buildAndCheckModel_thereExistsUntil2() {
		try {
			Model model = Model
					.parseModel("src/test/resources/my-examples/model/modelThereExistsUntil2.json");
			TSModel ts = Model.transform(model);
			StateFormula fairnessConstraint = new FormulaParser(
					"src/test/resources/constraint1.json").parse();
			StateFormula query = new FormulaParser(
					"src/test/resources/my-examples/ctl/ctlThereExistsUntil.json").parse();
			ModelChecker mc = new SimpleModelChecker();
			assertTrue(mc.check(ts, fairnessConstraint, query));
		} catch (IOException e) {
			e.printStackTrace();
			fail(e.toString());
		}
	}

	@Test
	public void buildAndCheckModel_thereExistsAlways2() {
		try {
			Model model = Model
					.parseModel("src/test/resources/my-examples/model/modelForAllAlways2.json");
			TSModel ts = Model.transform(model);
			StateFormula fairnessConstraint = new FormulaParser(
					"src/test/resources/constraint1.json").parse();
			StateFormula query = new FormulaParser(
					"src/test/resources/my-examples/ctl/ctlForAllAlways.json").parse();
			ModelChecker mc = new SimpleModelChecker();
			assertTrue(mc.check(ts, fairnessConstraint, query));
		} catch (IOException e) {
			e.printStackTrace();
			fail(e.toString());
		}
	}

	@Test
	public void buildAndCheckModel_mutualExclusion() {
		try {
			Model model = Model
					.parseModel("src/test/resources/my-examples/model/modelMutualExclusion.json");
			TSModel ts = Model.transform(model);
			StateFormula fairnessConstraint = new FormulaParser(
					"src/test/resources/constraint1.json").parse();
			StateFormula query = new FormulaParser(
					"src/test/resources/my-examples/ctl/ctlMutualExclusion.json").parse();
			ModelChecker mc = new SimpleModelChecker();
			assertTrue(mc.check(ts, fairnessConstraint, query));
		} catch (IOException e) {
			e.printStackTrace();
			fail(e.toString());
		}
	}
}
