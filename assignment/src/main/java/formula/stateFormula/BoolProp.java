package formula.stateFormula;

import tsmodel.TSModel;
import tsmodel.TSState;

public class BoolProp extends StateFormula {
    public final boolean value;

    public BoolProp(boolean value) {
        this.value = value;
    }

    @Override
    public void writeToBuffer(StringBuilder buffer) {
        String stringValue = (value) ? "True" : "False";
        buffer.append(" " + stringValue + " ");
    }

    @Override
    public boolean isValidState(TSState state, TSModel model){
    	return value;
    } 
}
