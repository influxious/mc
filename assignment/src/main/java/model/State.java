package model;

import java.util.Arrays;

/**
 * 
 * */
public class State {
    
    private boolean init;
    private String name;
    private String [] label;
    	
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
    
    @Override
    public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("State: "+this.name+"\n");
		sb.append("Initial: "+this.init+"\n");
		sb.append("Atomic Propositions: "+Arrays.toString(this.label)+"\n");
		return sb.toString();
    }
	
}
