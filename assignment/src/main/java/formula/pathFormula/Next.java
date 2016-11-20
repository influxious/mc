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
	
		if(foundInvalidPath(currentT, futureState)){
			validAll = false;
		}
		traversal(futureState);
	}
	
	public void somePaths(TSTransition currentT){
		TSState futureState = currentT.getTarget();
		
		if(foundInvalidPath(currentT, futureState)){
			return;
		} else if(foundValidPath(currentT, futureState)){
			validPath = true;
		}
		traversal(futureState);
	}
	
	
	public boolean foundInvalidPath(TSTransition currentT, TSState futureState){
		return ((!stateFormula.isValidState(futureState)) || !currentT.validActions(actions));
	}
	
	public boolean foundValidPath(TSTransition currentT, TSState futureState){
		return (stateFormula.isValidState(futureState) && currentT.validActions(actions));
	}
	
}
