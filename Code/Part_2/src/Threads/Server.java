package Threads;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.Observable;
import java.util.Observer;

import RefactoredCode.MenuItem;
import RefactoredCode.Order;
import SharedObjects.KitchenCounter;
import SharedObjects.Requests;
import SharedObjects.ServerControl;
import SharedObjects.SimTime;
import Threads.CafeQueue;
import main.Log;

import java.util.ArrayList;
import java.util.List;

public class Server extends Thread implements Subject {
	private String customerName;
	private BigDecimal total;
	private Order order;
	private String description;
	private CafeQueue queue;
	private List<Observer> observers = new ArrayList<Observer>();
	private List<Observer> registeredObservers = new ArrayList<Observer>();
	private SimTime time;
	private ServerControl control;
	private int serverNo; 
	private KitchenCounter counter;
	private Requests requests;
	private ArrayList<String> arr;
	
	public Server(CafeQueue q, SimTime t, ServerControl c, int i, KitchenCounter k, Requests r) {
		this.queue = q; // take class as argument
		this.time = t;
		this.control = c;
		this.serverNo = i;
		this.counter = k;
		this.requests = r;
		this.total = new BigDecimal(0);
		arr = new ArrayList<String>();
	}

	// public void setState(int state) {
	// this.state = state;
	// notifyAllObservers();
	// }

	// There is a lot happening in this method. Refactor it into multiple
	// methods? I need these so that I can display what is happening in the GUI

	public void run() {
		try {
			Thread.sleep(this.serverNo*100);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		while (queue.getQueueSize() != 0) {
			if(this.control.get(this.serverNo)==false) {continue;} 
			else {
				arr.clear();
				Order order = queue.serveCustomer(); // get orders from class
				// System.out.println(">s< Serving: " + order.getID());
				LinkedList<MenuItem> list = order.getItemList();
				int length = list.size();
				customerName = order.getID();
				total = new BigDecimal(0);
				notifyObservers();
				
				try {
					for (int i = 0; i < length; i++) // print processing message and
														// sleep
					{
						MenuItem item = list.pop();
						total = total.add(item.getCost());
						description = item.getDescription();
						
						requests.put(description);
						
						while(counter.get(description) == false) {
							Thread.sleep(time.get());  //sleep random max 3 seconds
						}
						arr.add(description);
						notifyObservers();
						sendToLog("Processing: " + description + " total: " + (total).toString());
						Thread.sleep(time.get());
						//Thread.sleep((long) (Math.random() * 3000)); // sleep random
																		// max 3
																		// seconds
					}
				} catch (Exception e) {
				}
			}
			
			}
			
	}

	public String getItem() {
		return description;
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

	public String getCustomerName() {
		return customerName;
	}
	
	public int getServerNo(){
		return this.serverNo;
	}
	
	public String getTotal(){
		return this.total.toString();
	}
	public synchronized ArrayList<String> getArr(){
		return arr;
	}
}
