package Threads;

import java.util.ArrayList;
import java.util.List;
import java.util.Observer;

import SharedObjects.KitchenCounter;
import SharedObjects.Requests;
import SharedObjects.ServerControl;
import SharedObjects.SimTime;
import main.Log;

/**
 * Thread for the kitchen Staff
 * @author calumthompson
 *
 * Producer for - Requests class
 * Consumer for - KitchenCounter class  
 */
public class KitchenStaff extends Thread implements Subject{
	
	private Requests requests;
	private KitchenCounter counter;
	private SimTime time;
	private List<Observer> registeredObservers = new ArrayList<Observer>();
	private String item;
	private int id;
	private ServerControl control;
	
	public KitchenStaff(Requests r, KitchenCounter k, SimTime t,ServerControl control, int id){
		this.requests = r;
		this.counter = k;
		this.time = t;
		this.id = id;
		this.control = control;
	}
	
	public void run() {
		
		while(true) {
			if(this.control.get(this.id-1)==false) {continue;} 
			else {			
				if(requests.isEmpty() == 0) {
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}else {
					item = requests.get();
					notifyObservers();
					sendToLog("Kitchen Staff are making: " + item);
					
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					counter.put(item);
					
					sendToLog("Kitchen Staff have made: " + item);
				}
			}
		}
	}
	
	private static synchronized void sendToLog(String details) {
		Log l = Log.INSTANCE;
		l.log(details);
	}

	public void registerObserver(Observer obs) {
		registeredObservers.add(obs);

	}

	public void removeObserver(Observer obs) {
		registeredObservers.remove(obs);

	}

	public void notifyObservers() {
		for (Observer obs : registeredObservers) {
			obs.update(null, this);
		}

	}
	
	public String getItem() {
		return item;
	}
	public int getID() {
		return id;
	}
}
