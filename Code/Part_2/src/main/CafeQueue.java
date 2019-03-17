package main;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Observer;
import java.util.Queue;
import java.util.Observable;

import main.Order;

public class CafeQueue extends Thread implements Subject{

	private static Queue<Order> queue = new LinkedList<>();
	private List<Observer> registeredObservers = new ArrayList<Observer>();
	//Interface i;
	
	CafeQueue(Interface i){
		//this.i = i;
		//this.queue = new ArrayList<>();
	}
	

	
	/*
	 * Run method for the thread.
	 * While there are orders left on the orderList continue adding them to the queue.
	 * Thread will sleep inbetween orders being added to delay the simulation.
	 * @see java.lang.Thread#run()
	 */
	public void run() {
		//System.out.println(Interface.getSize());
		while(Interface.getSize() != 0) {// && queue.size() != 0) {
			addToQueue();
			notifyObservers();
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	/*
	 * Add an order to the queue
	 */
	private synchronized void addToQueue() {
		Order order = Interface.getOrder();
		//System.out.println(">Q<" + order.getID() + " has joined the queue");
		sendToLog(order.getID() + " has joined the queue");
		queue.add(order);
	}
	
	/*
	 * Method for the server thread to access orders from the queue.
	 * Accessed orders will be removed from the queue.
	 */
	public static synchronized Order serveCustomer() {
		System.out.println(">Q< Serving customer: " + queue.peek().getID());
		sendToLog("Customer Served : " + queue.peek().getID());
		return queue.poll();
	}
	
	// Sends a string to the Log class
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
		for(Observer obs : registeredObservers) {
			obs.update(null, this);
		}
		
	}
	
	public static Queue<Order> getQueue() {
		return queue;
	}
	
	public synchronized int getQueueSize(){
		
		return queue.size();
	}
}