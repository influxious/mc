package formula.stateFormula;

import java.util.Stack;

import tsmodel.*;

public abstract class StateFormula {
    public abstract void writeToBuffer(StringBuilder buffer);

    @Override
    public String toString() {
        StringBuilder buffer = new StringBuilder();
        writeToBuffer(buffer);
        return buffer.toString();
    }
    
    public abstract boolean isValidState(TSState state, Stack<String> stack);
 
    public abstract boolean passConstraint(TSState state);
}
