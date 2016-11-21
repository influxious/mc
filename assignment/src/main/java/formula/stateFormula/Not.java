package formula.stateFormula;

import java.util.Stack;

import tsmodel.TSState;
import formula.FormulaParser;

public class Not extends StateFormula {
    public final StateFormula stateFormula;

    public Not(StateFormula stateFormula) {
        this.stateFormula = stateFormula;
    }

    @Override
    public void writeToBuffer(StringBuilder buffer) {
        buffer.append(FormulaParser.NOT_TOKEN);
        buffer.append("(");
        stateFormula.writeToBuffer(buffer);
        buffer.append(")");
    }

    @Override
    public boolean isValidState(TSState state, Stack<String> stack){
    	if (stateFormula.isValidState(state, stack)){
    		return false;
    	} else {
    		return true;
    	}
    }
    
    @Override
    public boolean passConstraint(TSState state){
    	return stateFormula.passConstraint(state);
    }
    
}
