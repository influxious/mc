package formula.stateFormula;

import tsmodel.TSState;

public class Or extends StateFormula {
    public final StateFormula left;
    public final StateFormula right;

    public Or(StateFormula left, StateFormula right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public void writeToBuffer(StringBuilder buffer) {
        buffer.append("(");
        left.writeToBuffer(buffer);
        buffer.append(" || ");
        right.writeToBuffer(buffer);
        buffer.append(")");
    }

    @Override
    public boolean isValidState(TSState state){
    	if(left.isValidState(state) || right.isValidState(state)){
    		return true;
    	} else {
    		return false;
    	}
    }
    
}