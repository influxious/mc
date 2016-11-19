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
    
    public abstract boolean isValidState(TSState state);
    
}
