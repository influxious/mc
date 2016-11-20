package formula.pathFormula;

import formula.*;
import formula.stateFormula.*;
import java.util.ArrayList;
import java.util.Set;
import tsmodel.TSModel;
import tsmodel.TSState;
import tsmodel.TSTransition;

public class Until extends PathFormula {
    public final StateFormula left;
    public final StateFormula right;
    private Set<String> leftActions;
    private Set<String> rightActions;
    
    private boolean validAll = true;
    private boolean validPath = false;
    private boolean checkingLeft = true;
    private boolean allPaths;

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
		} else if(invalidAction(currentT)){
			validAll = false;
		}
		traversal(futureState);
	}

	public void somePaths(TSTransition currentT){
		TSState futureState = currentT.getTarget();
		
		if(foundValidPath(currentT, futureState)){
			validPath = true;
		} else if(invalidAction(currentT)){
			return; /* look at other paths */
		} else if(handleLoop(futureState)){
			validPath = true; 
		}
		traversal(futureState);
	}

	public boolean foundValidPath(TSTransition currentT, TSState futureState){
		if((checkingLeft) && right.isValidState(futureState)){
			checkingLeft = false;
		} return currentT.validActions(rightActions);
	}
	
	public boolean invalidAction(TSTransition currentT){
		return ((checkingLeft) && !currentT.validActions(leftActions));
	}
	
	public boolean handleLoop(TSState futureState){
		return ((checkingLeft) && left.isValidState(futureState) && TSModel.visited[futureState.getIndex()]);
	}	
	
}
