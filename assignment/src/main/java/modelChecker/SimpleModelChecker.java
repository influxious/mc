package modelChecker;

import java.util.ArrayList;
import java.util.Arrays;

import tsmodel.*;
import formula.stateFormula.StateFormula;
import model.*;

public class SimpleModelChecker implements ModelChecker {

	@Override
	public boolean check(TSModel model, StateFormula constraint,
			StateFormula query) {
		ArrayList<TSState> iStates = model.getInitialStates();
		boolean[] visited = new boolean[model.numberOfStates];
		
		// System.out.println(states.size());
		// System.out.println(query);		
		for (int i = 0; i < iStates.size(); i++) {
			TSState s = iStates.get(i);
			
			//System.out.println("Current: " + s.getName());
			recursiveTraversal(s, visited, query);
			if (!query.isValidState(s)) {
				return false;
			}
		}
		return true;
	}

	public void recursiveTraversal(TSState state, boolean[] visited, StateFormula query) {
		if (visited[state.getIndex()]) {
			return;
		}
		System.out.println("State name: " + state.getName());
		
		if (!query.isValidState(state)) {
			System.out.println("Does not hold");
		}
		
		visited[state.getIndex()] = true;
		ArrayList<TSTransition> transitions = state.getTransitions();
		for (int i = 0; i < transitions.size(); i++) {
			TSTransition currentT = transitions.get(i);
			TSState futureState = currentT.getTarget();
			recursiveTraversal(futureState, visited, query);
		}
	}

	public void printAll(TSModel model) {
		ArrayList<TSState> states = model.getInitialStates();
		boolean[] visited = new boolean[model.numberOfStates];

		for (int i = 0; i < states.size(); i++) {
			TSState s = states.get(i);
			recursivePrintAll(s, visited);
		}
	}

	public void recursivePrintAll(TSState state, boolean[] visited) {
		if (visited[state.getIndex()]) {
			return;
		}
		System.out.println("State name: " + state.getName());
		
		visited[state.getIndex()] = true;
		ArrayList<TSTransition> transitions = state.getTransitions();
		for (int i = 0; i < transitions.size(); i++) {
			TSTransition currentT = transitions.get(i);
			TSState futureState = currentT.getTarget();
			recursivePrintAll(futureState, visited);
		}
	}

	@Override
	public String[] getTrace() {
		// TODO Auto-generated method stub
		return null;
	}

}
