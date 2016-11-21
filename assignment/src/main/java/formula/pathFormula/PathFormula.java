package formula.pathFormula;
import java.util.Stack;

import formula.stateFormula.StateFormula;
import tsmodel.TSState;

public abstract class PathFormula {
    public abstract void writeToBuffer(StringBuilder buffer);
    
    public abstract boolean isValidState(TSState state, StateFormula sf, Stack<String> stack);

	public abstract boolean passConstraint(TSState state, StateFormula sf);


;}
