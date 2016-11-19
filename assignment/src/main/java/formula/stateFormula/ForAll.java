package formula.stateFormula;

import tsmodel.TSModel;
import tsmodel.TSState;
import formula.*;
import formula.pathFormula.PathFormula;

public class ForAll extends StateFormula {
    public final PathFormula pathFormula;

    public ForAll(PathFormula pathFormula) {
        this.pathFormula = pathFormula;
    }

    @Override
    public void writeToBuffer(StringBuilder buffer) {
        buffer.append("(");
        buffer.append(FormulaParser.FORALL_TOKEN);
        pathFormula.writeToBuffer(buffer);
        buffer.append(")");
    }
    
    @Override
    public boolean isValidState(TSState state, TSModel model){
    	return pathFormula.isValidState(state, this, model);
    }
    
}
