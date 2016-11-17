package modelChecker;

import java.util.ArrayList;

import tsmodel.*;
import formula.stateFormula.StateFormula;
import model.*;

public class SimpleModelChecker implements ModelChecker {

    @Override
    public boolean check(TSModel model, StateFormula constraint, StateFormula query) {    	
    	ArrayList<TSState> states = model.getInitialStates();
//        System.out.println(states.size());
//        System.out.println(query);
    	for(int i=0; i<states.size(); i++){
    		TSState s = states.get(i);
        	if(!query.isValidState(s)){
        		return false;
        	}
    	}
    	return true;
    }

    @Override
    public String[] getTrace() {
        // TODO Auto-generated method stub
        return null;
    }



}
