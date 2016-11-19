package formula.stateFormula;

import tsmodel.TSModel;
import tsmodel.TSState;

public class And extends StateFormula {
    public final StateFormula left;
    public final StateFormula right;

    public And(StateFormula left, StateFormula right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public void writeToBuffer(StringBuilder buffer) {
        buffer.append("(");
        left.writeToBuffer(buffer);
        buffer.append(" && ");
        right.writeToBuffer(buffer);
        buffer.append(")");
    }
    
    @Override
    public boolean isValidState(TSState state, TSModel model){
    	if(left.isValidState(state, model) && right.isValidState(state, model)){
    		return true;
    	} else {
    		return false;
    	}
    }

}