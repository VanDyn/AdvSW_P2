package SharedObjects;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Shared Object to store the available food.
 * Server - Consumer
 * KitchenStaff - Producer
 * 
 * @author calumthompson
 *
 */
public class KitchenCounter {
	private Map<String,Integer> availableFood;
	
	private boolean empty;
	private boolean done;
	
	public KitchenCounter(){
		this.availableFood  = new HashMap<String,Integer>();
		this.empty = true;
		this.done = false;
	}
	
	public synchronized void put(String s) {
		if(availableFood.containsKey(s) == false){
			availableFood.put(s,1);
		}else {
			availableFood.put(s, availableFood.get(s) + 1);
		}
	}
	// catch exception
	public synchronized boolean get(String s) {
		
		if(availableFood.containsKey(s) && availableFood.get(s) != 0) { 
			availableFood.put(s, availableFood.get(s) - 1);
			done = true;
			return true;
		}else {
			return false;
		}
		
	}
	public boolean getDone() {
		return done;
	}
}
