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
    @Test
    public void buildAndCheckModel() {
        try {
            Model model = Model.parseModel("src/test/resources/model1.json");
            TSModel ts = Model.transform(model);
            
//            StateFormula fairnessConstraint = new FormulaParser("src/test/resources/constraint1.json").parse();
//            StateFormula query = new FormulaParser("src/test/resources/ctl2.json").parse();
//            ModelChecker mc = new SimpleModelChecker();

            // TO IMPLEMENT
            // assertTrue(mc.check(model, fairnessConstraint, query));
        } catch (IOException e) {
            e.printStackTrace();
            fail(e.toString());
        }
    }
    
    @Test
    public void buildAndCheckModel2() {
        try {
            Model model = Model.parseModel("src/test/resources/model1.json");

            StateFormula fairnessConstraint = new FormulaParser("src/test/resources/constraint1.json").parse();
	        System.out.println("Fairness: " + fairnessConstraint.toString());
            
            StateFormula query = new FormulaParser("src/test/resources/ctl2.json").parse();
	        System.out.println("Query: "+query.toString());
	          
            ModelChecker mc = new SimpleModelChecker();

            // TO IMPLEMENT
             assertTrue(mc.check(model, fairnessConstraint, query));
        } catch (IOException e) {
            e.printStackTrace();
            fail(e.toString());
        }
    }

}
