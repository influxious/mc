package tsmodel;

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
