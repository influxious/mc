package formula.stateFormula;

import tsmodel.*;

public abstract class StateFormula {
    public abstract void writeToBuffer(StringBuilder buffer);

    @Override
    public String toString() {
        StringBuilder buffer = new StringBuilder();
        writeToBuffer(buffer);
        return buffer.toString();
    }

    public boolean isValidState(TSState state, TSModel model){
    	return isValidState(state, model);
    }
    
}
