package formula.pathFormula;

import formula.FormulaParser;
import formula.stateFormula.*;
import java.util.*;
import tsmodel.TSModel;
import tsmodel.TSState;
import tsmodel.TSTransition;

public class Eventually extends PathFormula {
    public final StateFormula stateFormula;
    private Set<String> leftActions;
    private Set<String> rightActions;
    private boolean allPathsValid = true;
    private boolean validPath = false;

    public Eventually(StateFormula stateFormula, Set<String> leftActions, Set<String> rightActions) {
        super();
        this.stateFormula = stateFormula;
        this.leftActions = leftActions;
        this.rightActions = rightActions;
    }

    public Set<String> getLeftActions() {
        return leftActions;
    }

    public Set<String> getRightActions() {
        return rightActions;
    }

    @Override
    public void writeToBuffer(StringBuilder buffer) {
        buffer.append(FormulaParser.EVENTUALLY_TOKEN);
        stateFormula.writeToBuffer(buffer);
        ;
    }

	@Override
	public boolean isValidState(TSState state, StateFormula sf) {
		boolean[] visited = new boolean[TSModel.numberOfStates];
		if(ForAll.class.isInstance(sf)){
			recursiveTraversal(state, visited);
			return allPathsValid;
		} else if(ThereExists.class.isInstance(sf)){
			recursiveTraversalPath(state, visited);
			return validPath;
		} else {
			return false;
		}
	}

	private void recursiveTraversal(TSState state, boolean[] visited) {
		if (visited[state.getIndex()]) {
			return;
		}		
		if(stateFormula.isValidState(state)){
			return;
		} else if(state.getTransitions().size() == 0){
			allPathsValid = false;
		}
		visited[state.getIndex()] = true;
		ArrayList<TSTransition> transitions = state.getTransitions();
		for (int i = 0; i < transitions.size(); i++) {
			TSTransition currentT = transitions.get(i);
			TSState futureState = currentT.getTarget();
			recursiveTraversal(futureState, visited);
		}
	}

	private void recursiveTraversalPath(TSState state, boolean[] visited) {
		if (visited[state.getIndex()]) {
			return;
		}	
		if(stateFormula.isValidState(state)){
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
