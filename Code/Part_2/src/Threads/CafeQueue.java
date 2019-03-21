package Threads;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Observer;
import java.util.Queue;

import RefactoredCode.Interface;
import RefactoredCode.Order;
import SharedObjects.ServerControl;
import SharedObjects.SimTime;
import main.Log;

/**
 * Class to handle the threading for the Queue
 * Orders are read in over time to simulate customers joining the queue
 * @author calumthompson
 *
 */
public class CafeQueue extends Thread implements Subject{

	private Queue<Order> queue = new LinkedList<>();
	private List<Observer> registeredObservers = new ArrayList<Observer>();
	private SimTime time;

	private ServerControl control;
	
	Interface i;
	
	public CafeQueue(Interface i, SimTime t){
		this.i = i;
		this.time = t;
	}
	
	public CafeQueue(Interface i, SimTime t, ServerControl q){
		this.i = i;
		//this.queue = new ArrayList<>();
		this.time = t;
		this.control = q;
	}
	

	
	/*
	 * Run method for the thread.
	 * While there are orders left on the orderList continue adding them to the queue.
	 * Thread will sleep inbetween orders being added to delay the simulation.
	 * @see java.lang.Thread#run()
	 */
	public void run() {		
		
		/*addToQueue();
		addToQueue();
		addToQueue();
		*/
		while(i.getSize() != 0) {
			addToQueue();
			notifyObservers();
			try {
				Thread.sleep(time.get());
			} catch (InterruptedException e) {
				e.printStackTrace();
				}
			
			}
		}
			

	/*
	 * Adds an order to the queue
	 */
	private synchronized void addToQueue() {
		Order order = i.getOrder();
		sendToLog(order.getID() + " has joined the queue");
		queue.add(order);
	}
	
	/*
	 * Method for the server thread to access orders from the queue.
	 * Accessed orders will be removed from the queue.
	 */
	public synchronized Order serveCustomer() {
		sendToLog("Customer Served : " + queue.peek().getID());
		Order temp = queue.poll();
		notifyObservers();
		return temp;
	}
	
	// Sends a string to the Log class
	private synchronized void sendToLog(String details) {
		Log l = Log.INSTANCE;
		l.log(details);
	}

	/**
	 * Methods to make the class Observable
	 */
	public void registerObserver(Observer obs) {
		registeredObservers.add(obs);
		
	}

	public void removeObserver(Observer obs) {
		registeredObservers.remove(obs);
		
	}

	public void notifyObservers() {
		for(Observer obs : registeredObservers) {
			obs.update(null, this);
		}
		
	}
	
	public synchronized Queue<Order> getQueue() {
		return queue;
	}
	
	public synchronized int getQueueSize(){
		
		return queue.size();
	}
	
	public synchronized boolean isEmpty() {
		return queue.isEmpty();
	}
	
	// return list of the customers in the queue
	public synchronized ArrayList<String> getQueueMembers(){
		ArrayList<String> members = new ArrayList<String>();
		
		for(Order m : queue) {
			members.add(m.getID());
		}
		
		return members;
	}
	
	// return a list of the number of items each customer has
	public synchronized ArrayList<String> getQueueOrders(){
		ArrayList<String> orders = new ArrayList<String>();
		
		for(Order n : queue) {
			orders.add("   " + Integer.toString(n.getItemNumber()) + " Items");
		}
		
		return orders;
	}
	
}
