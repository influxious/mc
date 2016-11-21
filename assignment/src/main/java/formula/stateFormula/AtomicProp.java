package formula.stateFormula;

import java.util.Stack;

import tsmodel.TSState;

public class AtomicProp extends StateFormula {
    public final String label;

    public AtomicProp(String label) {
        this.label = label;
    }

    @Override
    public void writeToBuffer(StringBuilder buffer) {
        buffer.append(" " + label + " ");
    }
    
    @Override
    public boolean isValidState(TSState state, Stack<String> stack){    	
    	return state.containsAtomicProp(label);
    }
    
    @Override
    public boolean passConstraint(TSState state){
    	return state.containsAtomicProp(label);
    }
	



}
