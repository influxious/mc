package modelChecker;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

import tsmodel.*;
import formula.stateFormula.StateFormula;

public class SimpleModelChecker implements ModelChecker {
	
	boolean valid = true;
	Stack<String> stack = new Stack<String>();

	@Override
	public boolean check(TSModel model, StateFormula constraint, StateFormula query) {
		ArrayList<TSState> iStates = model.getInitialStates();	
		for (int i = 0; i < iStates.size(); i++) {
			TSState s = iStates.get(i);
			if(!query.isValidState(s, stack)){
				getTrace();
				return false;
			}
			
			
			
			if(!constraint.passConstraint(s)){
				return false;
			}
		}
		return true;
	}
	

	@Override
	public void getTrace() {
		List<String> list = new ArrayList<String>(stack);
		Collections.reverse(list);
		System.out.println("Error Trace:");
		for (String x : list)
        {
            System.out.println(x);
        }
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	public void printAll(TSModel model) {
		ArrayList<TSState> states = model.getInitialStates();
		for (int i = 0; i < states.size(); i++) {
			TSState s = states.get(i);
			recursivePrintAll(s);
		}
	}

	public void recursivePrintAll(TSState state) {
		if (TSModel.visited[state.getIndex()]) {
			return;
		}
		System.out.println("State name: " + state.getName());
		TSModel.visited[state.getIndex()] = true;
		ArrayList<TSTransition> transitions = state.getTransitions();
		for (int i = 0; i < transitions.size(); i++) {
			TSTransition currentT = transitions.get(i);
			TSState futureState = currentT.getTarget();
			recursivePrintAll(futureState);
		}
	}

	

}
