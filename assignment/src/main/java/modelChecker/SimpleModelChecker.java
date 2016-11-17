package modelChecker;

import formula.stateFormula.StateFormula;
import model.*;

public class SimpleModelChecker implements ModelChecker {

    @Override
    public boolean check(Model model, StateFormula constraint, StateFormula query) {
        State[] states = model.getStates();
    	
    	
    	
    	
        return false;
    }

    @Override
    public String[] getTrace() {
        // TODO Auto-generated method stub
        return null;
    }

}
