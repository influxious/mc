package tsmodel;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;


public class  TSTransition {

	private TSState target;
    private Set<String> actions;
    
    public TSTransition(TSState target, Set<String> actions){
    	this.target = target;
    	this.actions = actions;
    }
    
	public TSState getTarget() {
		return target;
	}
	
	public Set<String> getActions() {
		return actions;
	}
	
	public boolean validActions(Set<String> act){	
		if(act.size() == 0){
			return true; 
		}
		Set<String> intersection = new HashSet<String>(act);
		intersection.retainAll(actions);
		return (intersection.size() > 0);
	}
	
	
	 @Override
	 public String toString() {
			StringBuilder sb = new StringBuilder();
		    Iterator<String> it = actions.iterator();
		    while(it.hasNext()){
		    	 sb.append(it.next()+ "-" + target + "\n");
		    }
			return sb.toString();
	 }  
    
    
    

	
}
