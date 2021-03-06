package modelChecker;

import tsmodel.TSModel;
import formula.stateFormula.StateFormula;

/**
 * Defines the interface to model checker.
 *
 */
public interface ModelChecker {
    /**
     * verifies whether the model satisfies the query under the given
     * constraint.
     * 
     * @param model
     *            - model to verify
     * @param constraint
     *            - the constraint applied to the model before verification
     *            against the query.
     * @param query
     *            - the state formula to verify the model against.
     * @return - true if the model satisfies the query under the applied
     *         constraint.
     */
    public boolean check(TSModel model, StateFormula constraint, StateFormula query);

    // Returns a trace of the previous check attempt if it failed.
    public void getTrace();
    
    public void printAll(TSModel model);
}
