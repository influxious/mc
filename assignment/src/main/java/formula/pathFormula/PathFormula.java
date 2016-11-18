package formula.pathFormula;
import formula.stateFormula.StateFormula;
import tsmodel.TSState;

public abstract class PathFormula {
    public abstract void writeToBuffer(StringBuilder buffer);
    
    public abstract boolean isValidState(TSState state, StateFormula sf);
;}
