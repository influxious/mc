package formula.stateFormula;

import java.util.Stack;

import tsmodel.TSState;

public class BoolProp extends StateFormula {
    public final boolean value;

    public BoolProp(boolean value) {
        this.value = value;
    }

    @Override
    public void writeToBuffer(StringBuilder buffer) {
        String stringValue = (value) ? "True" : "False";
        buffer.append(" " + stringValue + " ");
    }

    @Override
    public boolean isValidState(TSState state, Stack<String> stack){
    	return value;
    } 
    
    @Override
    public boolean passConstraint(TSState state){
    	return value;
    }
}
