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
	/**
	 * Prepared food are added to the kitchen counter from the kitchen staff 
	 * @param s	item
	 */
	public synchronized void put(String s) {
		if(availableFood.containsKey(s) == false){
			availableFood.put(s,1);
		}else {
			availableFood.put(s, availableFood.get(s) + 1);
		}
	}
	/**
	 * Get a specific food item from the kitchen counter.
	 * @param s
	 * @return		true is servers item has been prepared
	 */
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
