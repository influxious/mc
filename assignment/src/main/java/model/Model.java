package model;

import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
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

	public static TSModel transform(Model model) {
		TSModel tsmodel = new TSModel();
		Map<String, TSState> allStates = new HashMap<String, TSState>();
		int index = 0;

		for (State s : model.states) {
			Set<String> atomicProps = new HashSet<String>(Arrays.asList(s
					.getLabel()));
			TSState st = new TSState(index, s.getName(), atomicProps);
			allStates.put(s.getName(), st);
			index++;
			if (s.isInit()) {
				tsmodel.addInitialState(st);
			}
		}

		for (Transition t : model.transitions) {
			TSState sourceState = allStates.get(t.getSource());
			Set<String> actions = new HashSet<String>(Arrays.asList(t
					.getActions()));
			TSState targetState = allStates.get(t.getTarget());
			TSTransition ts = new TSTransition(targetState, actions);
			sourceState.addTransition(ts);
		}

		System.out.println("number of states " + index);
		tsmodel.numberOfStates = index;
		return tsmodel;
	}

	// build map from name of state to actual state

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
