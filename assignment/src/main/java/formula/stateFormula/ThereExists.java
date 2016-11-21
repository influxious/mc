package formula.stateFormula;

import java.util.Stack;

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
    public boolean isValidState(TSState state, Stack<String> stack){
    	return pathFormula.isValidState(state, this, stack);
    }
    
    @Override
    public boolean passConstraint(TSState state){
    	return pathFormula.passConstraint(state, this);
    }
    
}
