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
			recursiveTraversal(state, visited);
			return isValid;
		} else if(ThereExists.class.isInstance(sf)){
			recursiveTraversalPath(state, visited);
			return validPath;
		} else {
			return false;
		}
	}
	
	public void recursiveTraversal(TSState state, boolean[] visited) {
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
			recursiveTraversal(futureState, visited);
		}
	} 	
	
	public void recursiveTraversalPath(TSState state, boolean[] visited) {
		if (visited[state.getIndex()]) {
			return;
		}	
		if(!stateFormula.isValidState(state)){
			return;
		} else if(state.getTransitions().size() == 0){
			validPath = true;
			return;
		}
		visited[state.getIndex()] = true;
		ArrayList<TSTransition> transitions = state.getTransitions();
		for (int i = 0; i < transitions.size(); i++) {
			TSTransition currentT = transitions.get(i);
			TSState futureState = currentT.getTarget();
			recursiveTraversalPath(futureState, visited);
		}
	}
	

}
