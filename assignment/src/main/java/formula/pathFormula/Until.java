package formula.pathFormula;

import formula.*;
import formula.stateFormula.*;

import java.util.ArrayList;
import java.util.Set;
import java.util.Stack;

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
	public boolean isValidState(TSState state, StateFormula sf, Stack<String> stack) {
		if(ForAll.class.isInstance(sf)){
			allPaths = true;
			traversal(state, stack);
			return validAll;
		} else { /* There exists */
			allPaths = false;
			traversal(state, stack);
			return validPath;
		}
	}
	
	@Override
    public boolean passConstraint(TSState state, StateFormula sf){
		return true;
    }
	
	public void traversal(TSState state, Stack<String> stack) {
		if (TSModel.visited[state.getIndex()]) {
			return; /* Every state will only be visited once */
		} 
		TSModel.visited[state.getIndex()] = true;
		
		ArrayList<TSTransition> transitions = state.getTransitions();
		for (int i = 0; i < transitions.size(); i++) {
			TSTransition currentT = transitions.get(i);
			if (allPaths) { /* Either all paths or one path */
				allPaths(currentT, stack);
			} else {
				somePaths(currentT, stack);
			}
		}
	}
	
	public void allPaths(TSTransition currentT, Stack<String> stack){
		TSState futureState = currentT.getTarget();
		
		if(foundValidPath(currentT, futureState, stack)){
			return; /* look at other paths */
		} else if(invalidAction(currentT)){
			validAll = false;
			return;
		}
		traversal(futureState, stack);
	}

	public void somePaths(TSTransition currentT, Stack<String> stack){
		TSState futureState = currentT.getTarget();
		
		if(foundValidPath(currentT, futureState, stack)){
			validPath = true;
		} else if(invalidAction(currentT)){
			return; /* look at other paths */
		} else if(handleLoop(futureState, stack)){
			validPath = true; 
		}
		traversal(futureState, stack);
	}

	public boolean foundValidPath(TSTransition currentT, TSState futureState, Stack<String> stack){
		if((checkingLeft) && right.isValidState(futureState, stack)){
			checkingLeft = false;
		} return currentT.validActions(rightActions);
	}
	
	public boolean invalidAction(TSTransition currentT){
		return ((checkingLeft) && !currentT.validActions(leftActions));
	}
	
	public boolean handleLoop(TSState futureState, Stack<String> stack){
		return ((checkingLeft) && left.isValidState(futureState, stack) && TSModel.visited[futureState.getIndex()]);
	}	
	
}
