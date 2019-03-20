package SharedObjects;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Shared Object to store the food that needs prepared
 * Server - Producer
 * KitchenStaff - Consumer
 * 
 * @author calumthompson
 *
 */
public class Requests {
	
	private Queue<String> orderedFood;
	
	private boolean done;
	
	public Requests(){
		this.orderedFood = new LinkedList<String>();
		this.done = false;
	}
	
	public synchronized void put(String s) {
		done = false;
		orderedFood.add(s);
		done = true;
	}
	
	public synchronized String get() {
		
		if(orderedFood.size() == 0) {done = false; return "-1";}
		done = true;
		return orderedFood.poll();
	}
	
	public synchronized boolean getDone() {
		return done;
	}
	
	public void setDone() {
		done = false;
	}
	
}
