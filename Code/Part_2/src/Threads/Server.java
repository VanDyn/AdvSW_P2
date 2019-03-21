package Threads;

import java.math.BigDecimal; 
import java.util.LinkedList;
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
	private String description;
	private CafeQueue queue;
	private List<Observer> registeredObservers = new ArrayList<Observer>();
	private SimTime time; //server speed lookup
	private ServerControl control; //controls activation state of the server.
	private int serverNo; //Numerical ID
	private KitchenCounter counter;
	private Requests requests;
	private ArrayList<String> arr;
	
	public Server(CafeQueue q, SimTime t, ServerControl c, int i, KitchenCounter k, Requests r) {
		this.queue = q; // take class as argument
		this.time = t; //shared time control
		this.control = c; //server activation state control
		this.serverNo = i; //ID
		this.counter = k;
		this.requests = r;
		this.total = new BigDecimal(0);
		arr = new ArrayList<String>();
	}

	public void run() {
		try {
			Thread.sleep(this.serverNo*100); //ensure that server threads are not running simultaneously with different delays
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		while (queue.getQueueSize() != 0) {
			if(this.control.get(this.serverNo)==false) {continue;} //Do nothing if server is not active
			else{
				arr.clear();
				Order order = queue.serveCustomer(); // get orders from class
				LinkedList<MenuItem> list = order.getItemList();
				int length = list.size();
				customerName = order.getID();
				total = new BigDecimal(0);
				notifyObservers(); //Publish cleared order array
				
				try 
				{
					for (int i = 0; i < length; i++) //process item, message, and sleep
					{
						MenuItem item = list.pop();
						total = total.add(item.getCost());
						description = item.getDescription();
						
						requests.put(description);
						
						while(counter.get(description) == false) {
							Thread.sleep(time.get());  //sleep random max 3 seconds
						}
						arr.add(description);
						notifyObservers(); //Publish complete order array
						sendToLog("Processing: " + description + " total: " + (total).toString());
						Thread.sleep(time.get()); //get thread delay from SimTime class
					}
				} catch (Exception e) {}
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
