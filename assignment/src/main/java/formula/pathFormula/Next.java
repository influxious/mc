package formula.pathFormula;

import formula.FormulaParser;
import formula.stateFormula.*;

import java.util.ArrayList;
import java.util.Set;

import tsmodel.TSModel;
import tsmodel.TSState;
import tsmodel.TSTransition;

public class Next extends PathFormula {
    public final StateFormula stateFormula;
    private Set<String> actions;
    boolean isValid = true;
    boolean validPath = false;

    public Next(StateFormula stateFormula, Set<String> actions) {
        this.stateFormula = stateFormula;
        this.actions = actions;
    }

    public Set<String> getActions() {
        return actions;
    }

    @Override
    public void writeToBuffer(StringBuilder buffer) {
        buffer.append(FormulaParser.NEXT_TOKEN);
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
		visited[state.getIndex()] = true;
		ArrayList<TSTransition> transitions = state.getTransitions();
		for (int i = 0; i < transitions.size(); i++) {
			TSTransition currentT = transitions.get(i);
			TSState futureState = currentT.getTarget();
			if(!stateFormula.isValidState(futureState)){
				isValid = false;
			}
			recursiveTraversal(futureState, visited);
		}
	} 	
	
	public void recursiveTraversalPath(TSState state, boolean[] visited) {
		if (visited[state.getIndex()]) {
			return;
		}		
		visited[state.getIndex()] = true;
		ArrayList<TSTransition> transitions = state.getTransitions();
		for (int i = 0; i < transitions.size(); i++) {
			TSTransition currentT = transitions.get(i);
			TSState futureState = currentT.getTarget();
			if(stateFormula.isValidState(futureState)){
				validPath = true;
			}
			recursiveTraversalPath(futureState, visited);
		}
	} 
	

}
