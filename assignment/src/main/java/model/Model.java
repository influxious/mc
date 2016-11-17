package model;

import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import tsmodel.*;

import com.google.gson.Gson;

/**
 * A model is consist of states and transitions
 */
public class Model {
    State[] states;
    Transition[] transitions;

    public static Model parseModel(String filePath) throws IOException {
        Gson gson = new Gson();
        Model model = gson.fromJson(new FileReader(filePath), Model.class);
        return model;
    }

    public static TSModel transform(Model model){
    	TSModel tsmodel = new TSModel();
    	for(State s : model.states){
    		if(s.isInit()){
    			Set<String> atomicProps = new HashSet<String>(Arrays.asList(s.getLabel()));
    			TSState st = new TSState(s.getName(), atomicProps);
    			for (Transition t : model.transitions) {
    	            if(t.getSource().equals(s.getName())){
    	    			Set<String> actions = new HashSet<String>(Arrays.asList(t.getActions()));
    	            	TSTransition ts = new TSTransition(t.getTarget(), actions);
    	            	st.addTransition(ts);
    	            }
    	        }
    			tsmodel.addInitialState(st);
    		}
    	} return tsmodel;
    }
    
    /**
     * Returns the list of the states
     * 
     * @return list of state for the given model
     */
    public State[] getStates() {
        return states;
    }

    /**
     * Returns the list of transitions
     * 
     * @return list of transition for the given model
     */
    public Transition[] getTransitions() {
        return transitions;
    }

}
