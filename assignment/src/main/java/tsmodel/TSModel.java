package tsmodel;

import java.util.ArrayList;


/**
 * A model is consist of states and transitions
 */
public class TSModel {
	
	ArrayList<TSState> initialStates = new ArrayList<TSState>();
	public static int numberOfStates;
	
	
	
	public void addInitialState(TSState iState){
		initialStates.add(iState);
	}
	
	public ArrayList<TSState> getInitialStates(){
		return initialStates;
	}
}
