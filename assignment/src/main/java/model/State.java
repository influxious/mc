package model;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * 
 * */
public class State {
    
    private boolean init;
    private String name;
    private String [] label;
    
    private ArrayList<Transition> transitions = new ArrayList<Transition>();
	
    /**
     * Is state an initial state
     * @return boolean init 
     * */
    public boolean isInit() {
	return init;
    }
	
    /**
     * Returns the name of the state
     * @return String name 
     * */
    public String getName() {
	return name;
    }
	
    /**
     * Returns the labels of the state
     * @return Array of string labels
     * */
    public String[] getLabel() {
	return label;
    }
    
    public void addTransition(Transition t){
    	transitions.add(t);
    }
    
    public ArrayList<Transition> getTransitions(){
    	return transitions;
    }
    
    @Override
    public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("State: "+this.name+"\n");
		sb.append("Initial: "+this.init+"\n");
		sb.append("Atomic Propositions: "+Arrays.toString(this.label)+"\n");
		sb.append("Transitions: \n");
		for(int i=0; i<transitions.size(); i++){
			sb.append("\t -"+transitions.get(i)+"\n");
		}
		return sb.toString();
    }
	
}
