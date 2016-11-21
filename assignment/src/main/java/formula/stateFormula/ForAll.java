package formula.stateFormula;

import java.util.Stack;

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
    public boolean isValidState(TSState state, Stack<String> stack){
    	return pathFormula.isValidState(state, this, stack);
    }
    
    @Override
    public boolean passConstraint(TSState state){
    	return pathFormula.passConstraint(state, this);
    }
    
}
