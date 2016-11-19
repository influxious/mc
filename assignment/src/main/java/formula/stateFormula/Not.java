package formula.stateFormula;

import tsmodel.TSModel;
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
    public boolean isValidState(TSState state, TSModel model){
    	if (stateFormula.isValidState(state, model)){
    		return false;
    	} else {
    		return true;
    	}
    }
    
}
