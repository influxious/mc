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
    }

	@Override
	public boolean isValidState(TSState state, StateFormula sf) {
		boolean[] visited = new boolean[TSModel.numberOfStates];
		if(ForAll.class.isInstance(sf)){
			recursiveTraversal(state, visited);
			return allPathsValid;
		} else if(ThereExists.class.isInstance(sf)){
			System.out.println("*******");
			recursiveTraversalPath(state, visited);
			return validPath;
		} else {
			return false;
		}
	}
	
	public void printActions(){
		System.out.println("Left Action Size: "+leftActions.size());
		Iterator<String> it = leftActions.iterator();
	    while(it.hasNext()){
	    	 System.out.println(it.next());
	    }
	    System.out.println("Right Action Size: "+rightActions.size());
		Iterator<String> it2 = rightActions.iterator();
	    while(it2.hasNext()){
	    	 System.out.println(it2.next());
	    }
	}
	
	public boolean validLeftActions(Set<String> act){	
		if(leftActions.size() == 0){
			return true; //No left action specified
		}
		Set<String> intersection = new HashSet<String>(leftActions);
		intersection.retainAll(act);
		System.out.println("intersection 1: " +intersection.size());
		return (intersection.size() > 0);
	}
	
	public boolean validRightActions(Set<String> act){	
		if(rightActions.size() == 0){
			return true; //No right action specified
		}
		Set<String> intersection = new HashSet<String>(rightActions);
		intersection.retainAll(act);
		System.out.println("intersection: " +intersection.size());
		return (intersection.size() > 0);
	}

	private void recursiveTraversal(TSState state, boolean[] visited) {
		if (visited[state.getIndex()]) {
			return;
		}		
		if(state.getTransitions().size() == 0){
			allPathsValid = false;
		}
		visited[state.getIndex()] = true;
		ArrayList<TSTransition> transitions = state.getTransitions();
		for (int i = 0; i < transitions.size(); i++) {
			TSTransition currentT = transitions.get(i);
			TSState futureState = currentT.getTarget();
			if(stateFormula.isValidState(futureState) && validRightActions(currentT.getActions())){
				continue;
			} else if(!stateFormula.isValidState(futureState) && !validLeftActions(currentT.getActions())){
				allPathsValid = false;
			}
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
			System.out.println(state.getName() + " -> " + futureState.getName());
			if(stateFormula.isValidState(futureState) && validRightActions(currentT.getActions())){
				validPath = true;
				return;
			} else if(!stateFormula.isValidState(futureState) && !validLeftActions(currentT.getActions())){
				return;
			}
			recursiveTraversalPath(futureState, visited);
		}
	}
	
}
