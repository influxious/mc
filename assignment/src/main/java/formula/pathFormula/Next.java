package formula.pathFormula;

import formula.FormulaParser;
import formula.stateFormula.*;

import java.util.ArrayList;
import java.util.Set;
import java.util.Stack;

import tsmodel.TSModel;
import tsmodel.TSState;
import tsmodel.TSTransition;

public class Next extends PathFormula {
    public final StateFormula stateFormula;
    private Set<String> actions;
    
    private boolean validAll = true;
    private boolean validPath = false;
    private boolean allPaths;

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
		traversalConstraint(state);
		return true;
    }
	
	public void traversalConstraint(TSState state) {
		if (!TSModel.visited[state.getIndex()]) {
			return; 
		}
		TSModel.visited[state.getIndex()] = false;
		ArrayList<TSTransition> transitions = state.getTransitions();
		for (int i = 0; i < transitions.size(); i++) {
			TSTransition currentT = transitions.get(i);

			if(foundInvalidPathC(currentT)){
				continue;
			}
			traversalConstraint(currentT.getTarget());
		}
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
				allPaths(state, currentT, stack);
			} else {
				somePaths(currentT, stack);
			}
		}
	}
	
	public void allPaths(TSState state, TSTransition currentT, Stack<String> stack){
		TSState futureState = currentT.getTarget();
	
		if(invalidAP(futureState, stack)){
			validAll = false;
			stack.push("Invalid atomic proposition found at " + futureState.getName());
			stack.push(state.getName() + " --" + currentT.printActions() + "--> " + futureState.getName());
			return;
		} else if(invalidAction(currentT)){
			validAll = false;
			stack.push("Invalid action from " + state.getName() + " to " + futureState.getName());
			stack.push(state.getName() + " --" + currentT.printActions() + "--> " + futureState.getName());
			return;
		}
		traversal(futureState, stack);
		if(!validAll){
			stack.push(state.getName() + " --" + currentT.printActions() + "--> " + futureState.getName());
			return;
		}
	}
	
	public void somePaths(TSTransition currentT, Stack<String> stack){
		TSState futureState = currentT.getTarget();
		
		if(foundInvalidPath(currentT, futureState, stack)){
			return;
		} else if(foundValidPath(currentT, futureState, stack)){
			validPath = true;
		}
		traversal(futureState, stack);
	}
	
	public boolean foundInvalidPathC(TSTransition currentT){
		return ((!stateFormula.passConstraint(currentT.getTarget())) || !currentT.validActions(actions));
	}
	
	public boolean foundInvalidPath(TSTransition currentT, TSState futureState, Stack<String> stack){
		return ((!stateFormula.isValidState(futureState, stack)) || !currentT.validActions(actions));
	}
	
	public boolean invalidAP(TSState futureState, Stack<String> stack){
		return ((!stateFormula.isValidState(futureState, stack)));
	}
	
	public boolean invalidAction(TSTransition currentT){
		return (!currentT.validActions(actions));
	}
	
	public boolean foundValidPath(TSTransition currentT, TSState futureState, Stack<String> stack){
		return (stateFormula.isValidState(futureState, stack) && currentT.validActions(actions));
	}
	
}
