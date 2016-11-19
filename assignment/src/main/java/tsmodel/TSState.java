package tsmodel;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;

public class TSState {
    
    private String name;
    private Set<String> atomicProp;
    private ArrayList<TSTransition> transitions = new ArrayList<TSTransition>();
    int index;

     
    public TSState(int index, String name, Set<String> atomicProp){
    	this.index = index;
    	this.name = name;
    	this.atomicProp = atomicProp;
    }
    
    public void addTransition(TSTransition t){
    	transitions.add(t);
    }
    
    public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
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
    	System.out.println("building string");
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
	    System.out.println("built string " + sb.toString());
		return sb.toString();
    }

	public Set<String> getAtomicProp() {
		return atomicProp;
	}


	
}
