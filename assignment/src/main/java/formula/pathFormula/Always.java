package formula.pathFormula;

import formula.FormulaParser;
import formula.stateFormula.*;

import java.util.*;

import tsmodel.TSModel;
import tsmodel.TSState;
import tsmodel.TSTransition;

public class Always extends PathFormula {
    public final StateFormula stateFormula;
    private Set<String> actions = new HashSet<String>();
    private boolean isValid = true;
	private boolean validPath = false;

    
    public Always(StateFormula stateFormula, Set<String> actions) {
        this.stateFormula = stateFormula;
        this.actions = actions;
    }

    public Set<String> getActions() {
        return actions;
    }

    @Override
    public void writeToBuffer(StringBuilder buffer) {
        buffer.append(FormulaParser.ALWAYS_TOKEn);
        stateFormula.writeToBuffer(buffer);
    }

	@Override
	public boolean isValidState(TSState state, StateFormula sf) {
		boolean[] visited = new boolean[TSModel.numberOfStates];
		if(ForAll.class.isInstance(sf)){
			recursiveTraversal(state, visited, sf);
			return isValid;
		} else if(ThereExists.class.isInstance(sf)){
			recursiveTraversalPath(state, visited, sf);
			return validPath;
		} else {
			return false;
		}
	}
	
	public void recursiveTraversal(TSState state, boolean[] visited, StateFormula query) {
		if (visited[state.getIndex()]) {
			return;
		}		
		if(!stateFormula.isValidState(state)){
			isValid = false;
		} 
		visited[state.getIndex()] = true;
		ArrayList<TSTransition> transitions = state.getTransitions();
		for (int i = 0; i < transitions.size(); i++) {
			TSTransition currentT = transitions.get(i);
			TSState futureState = currentT.getTarget();
			recursiveTraversal(futureState, visited, query);
		}
	} 	
	
	public void recursiveTraversalPath(TSState state, boolean[] visited, StateFormula query) {
		if (visited[state.getIndex()]) {
			return;
		}	
		System.out.println("State: " + state.getName());

		if(!stateFormula.isValidState(state)){
			System.out.println("Invalid State");
			return;
		} else if(state.getTransitions().size() == 0){
			System.out.println("Last Valid - TRUE");
			validPath = true;
		}
		visited[state.getIndex()] = true;
		ArrayList<TSTransition> transitions = state.getTransitions();
		for (int i = 0; i < transitions.size(); i++) {
			TSTransition currentT = transitions.get(i);
			TSState futureState = currentT.getTarget();
			recursiveTraversalPath(futureState, visited, query);
		}
	}
	
	
	
	
	
/*	public void recursiveTraversalExists(TSState state, boolean[] visited, StateFormula query, TSModel model, boolean current) {
		if (visited[state.getIndex()]) {
			return;
		}	
		System.out.println("State: " + state.getName());

		
		if(!current){
			if(stateFormula.isValidState(state, model)){
				current = true;
				System.out.println("Valid");
			} 
//			if(state.getTransitions().size() == 0){
//				System.out.println("Last Valid");
//				//validPath = true
//			}
		} else {
			if(!stateFormula.isValidState(state, model)){
				System.out.println("Invalid");
				return;

			} else if(state.getTransitions().size() == 0){
				System.out.println("Last Valid - TRUE");
				validPath = true;
			}
		}
	
		visited[state.getIndex()] = true;
		ArrayList<TSTransition> transitions = state.getTransitions();
		for (int i = 0; i < transitions.size(); i++) {
			TSTransition currentT = transitions.get(i);
			TSState futureState = currentT.getTarget();
			recursiveTraversalExists(futureState, visited, query, model, current);
		}
	} */
}
