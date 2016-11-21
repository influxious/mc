package formula.stateFormula;

import java.util.Stack;

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
    public boolean isValidState(TSState state, Stack<String> stack){
    	if(left.isValidState(state, stack) && right.isValidState(state, stack)){
    		return true;
    	} else {
    		return false;
    	}
    }
    
    @Override
    public boolean passConstraint(TSState state){
    	if(left.passConstraint(state) && right.passConstraint(state)){
    		return true;
    	} else {
    		return false;
    	}
    }

}