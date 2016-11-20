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
    
    private boolean validAll = true;
    private boolean validPath = false;
    private boolean allPaths;

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
		if(ForAll.class.isInstance(sf)){
			allPaths = true;
			traversal(state);
			return validAll;
		} else { /* There exists */
			allPaths = false;
			traversal(state);
			return validPath;
		}
	}
	
	public void traversal(TSState state) {
		if (TSModel.visited[state.getIndex()]) {
			return; /* Every state will only be visited once */
		} 
		TSModel.visited[state.getIndex()] = true;
		
		ArrayList<TSTransition> transitions = state.getTransitions();
		for (int i = 0; i < transitions.size(); i++) {
			TSTransition currentT = transitions.get(i);
			if (allPaths) { /* Either all paths or one path */
				allPaths(currentT);
			} else {
				somePaths(currentT);
			}
		}
	}

	public void allPaths(TSTransition currentT){
		TSState futureState = currentT.getTarget();
		if(foundValidPath(currentT, futureState)){
			return; /* look at other paths */
		} else if(foundInvalidPath(currentT, futureState)){
			validAll = false;
		}
		traversal(futureState);
	}
	
	public void somePaths(TSTransition currentT){
		TSState futureState = currentT.getTarget();
		if(foundValidPath(currentT, futureState)){
			validPath = true;
		} else if(foundInvalidPath(currentT, futureState)){
			return;
		}
		traversal(futureState);
	}

	public boolean foundValidPath(TSTransition currentT, TSState futureState){
		return (stateFormula.isValidState(futureState) && currentT.validActions(rightActions));
	}
	
	public boolean foundInvalidPath(TSTransition currentT, TSState futureState){
		return (!stateFormula.isValidState(futureState) && !currentT.validActions(leftActions));
	}

	
}
