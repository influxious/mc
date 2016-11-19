package formula.stateFormula;

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
    public boolean isValidState(TSState state){
    	return state.containsAtomicProp(label);
    }
    
	



}
