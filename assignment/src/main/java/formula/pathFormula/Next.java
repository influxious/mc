package formula.pathFormula;

import formula.FormulaParser;
import formula.stateFormula.*;

import java.util.Set;

import tsmodel.TSModel;
import tsmodel.TSState;

public class Next extends PathFormula {
    public final StateFormula stateFormula;
    private Set<String> actions;

    public Next(StateFormula stateFormula, Set<String> actions) {
        this.stateFormula = stateFormula;
        this.actions = actions;
    }

    public Set<String> getActions() {
        return actions;
    }

    @Override
    public void writeToBuffer(StringBuilder buffer) {
        buffer.append(FormulaParser.NEXT_TOKEN);
        stateFormula.writeToBuffer(buffer);
        ;
    }

	@Override
	public boolean isValidState(TSState state, StateFormula sf,  TSModel model) {
		// TODO Auto-generated method stub
		return false;
	}

}
