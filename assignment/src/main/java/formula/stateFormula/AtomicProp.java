package formula.stateFormula;

import tsmodel.TSModel;
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
    public boolean isValidState(TSState state, TSModel model){
    	return state.containsAtomicProp(label);
    }
    
	



}
