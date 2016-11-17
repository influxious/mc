package tsmodel;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;

public class TSState {
    
    private String name;
    private Set<String> atomicProp;
    private ArrayList<TSTransition> transitions = new ArrayList<TSTransition>();

    public TSState(String name, Set<String> atomicProp){
    	this.name = name;
    	this.atomicProp = atomicProp;
    }
    
    public void addTransition(TSTransition t){
    	transitions.add(t);
    }
    
    public ArrayList<TSTransition> getTransitions(){
    	return transitions;
    }
    
    public boolean containsAtomicProp(String ap){
    	if (atomicProp.contains(ap)){
    		return true;
    	} else {
    		return false;
    	}
    }
    
    @Override
    public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("State: "+this.name+"\n");
		sb.append("Atomic Propositions: ");
		
	    Iterator<String> it = atomicProp.iterator();
	    while(it.hasNext()){
	    	 sb.append(it.next()+ " ");
	    }
	    sb.append("\nTransitions: \n");
	    for(int i=0; i<transitions.size(); i++){
		    sb.append("\t -" + name + "-" + transitions.get(i));
	    }
	    sb.append("\n");
		return sb.toString();
    }


	
}
