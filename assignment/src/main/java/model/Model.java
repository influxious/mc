package model;

import java.io.FileReader;
import java.io.IOException;

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
        for (State s : model.states) {
            for (Transition t : model.transitions) {
            	if (t.getSource().equals(s.getName())){
            		s.addTransition(t);
            	}
            }
            System.out.println(s);
        }
        return model;
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
//    public Transition[] getTransitions() {
//    	System.out.println("HERRE");
//        return transitions;
//    }

}
