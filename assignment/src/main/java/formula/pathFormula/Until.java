package formula.pathFormula;

import formula.*;
import formula.stateFormula.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import tsmodel.TSModel;
import tsmodel.TSState;
import tsmodel.TSTransition;

public class Until extends PathFormula {
    public final StateFormula left;
    public final StateFormula right;
    private Set<String> leftActions;
    private Set<String> rightActions;
    private boolean isValid = true;
    private boolean validPath = false;

    public Until(StateFormula left, StateFormula right, Set<String> leftActions, Set<String> rightActions) {
        super();
        this.left = left;
        this.right = right;
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
        buffer.append("(");
        left.writeToBuffer(buffer);
        buffer.append(" " + FormulaParser.UNTIL_TOKEN + " ");
        right.writeToBuffer(buffer);
        buffer.append(")");
    }

	@Override
	public boolean isValidState(TSState state, StateFormula sf) {
		boolean[] visited = new boolean[TSModel.numberOfStates];
		if(ForAll.class.isInstance(sf)){
			recursiveTraversal(state, visited, true);
			return isValid;
		} else if(ThereExists.class.isInstance(sf)){
			recursiveTraversalPath(state, visited, true);
			return validPath;
		} else {
			return false;
		}
	}
		
	public boolean validLeftActions(Set<String> act){	
		if(leftActions.size() == 0){
			return true; //No left action specified
		}
		Set<String> intersection = new HashSet<String>(leftActions);
		intersection.retainAll(act);
		return (intersection.size() > 0);
	}
	
	public boolean validRightActions(Set<String> act){	
		if(rightActions.size() == 0){
			return true; //No right action specified
		}
		Set<String> intersection = new HashSet<String>(rightActions);
		intersection.retainAll(act);
		return (intersection.size() > 0);
	}
	
	public void recursiveTraversal(TSState state, boolean[] visited, boolean currentLeft) {
		if (visited[state.getIndex()]) {
			return;
		}		
		visited[state.getIndex()] = true;
		ArrayList<TSTransition> transitions = state.getTransitions();
		for (int i = 0; i < transitions.size(); i++) {
			TSTransition currentT = transitions.get(i);
			TSState futureState = currentT.getTarget();
			if((currentLeft) && right.isValidState(futureState)){
				currentLeft = false;
			} 
			if((!currentLeft) && right.isValidState(futureState) && validRightActions(currentT.getActions())){
				continue;
			} else if((currentLeft) && !validLeftActions(currentT.getActions())){
				isValid = false;
			}
			recursiveTraversal(futureState, visited, currentLeft);
		}
	} 
	
	public void recursiveTraversalPath(TSState state, boolean[] visited, boolean currentLeft) {
		if (visited[state.getIndex()]) {
			return;
		}				 
		visited[state.getIndex()] = true;
		ArrayList<TSTransition> transitions = state.getTransitions();
		for (int i = 0; i < transitions.size(); i++) {
			TSTransition currentT = transitions.get(i);
			TSState futureState = currentT.getTarget();
			if((currentLeft) && right.isValidState(futureState)){
				currentLeft = false;
			} 
			if((!currentLeft) && right.isValidState(futureState) && validRightActions(currentT.getActions())){
				validPath = true;
				return;
			} else if((currentLeft) && !validLeftActions(currentT.getActions())){
				return;
			} else if((currentLeft) && left.isValidState(futureState) && visited[futureState.getIndex()]){
				validPath = true;
			} 
			recursiveTraversalPath(futureState, visited, currentLeft);
		}
	} 
	
}
