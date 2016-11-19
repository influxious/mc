package formula.stateFormula;

import tsmodel.TSModel;
import tsmodel.TSState;
import formula.FormulaParser;
import formula.pathFormula.PathFormula;

public class ThereExists extends StateFormula {
    public final PathFormula pathFormula;

    public ThereExists(PathFormula pathFormula) {
        this.pathFormula = pathFormula;
    }

    @Override
    public void writeToBuffer(StringBuilder buffer) {
        buffer.append("(");
        buffer.append(FormulaParser.THEREEXISTS_TOKEN);
        pathFormula.writeToBuffer(buffer);
        buffer.append(")");
    }
    
    @Override
    public boolean isValidState(TSState state, TSModel model){
    	return pathFormula.isValidState(state, this, model);
    }
    
}
