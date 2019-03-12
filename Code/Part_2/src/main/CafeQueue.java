package main;

import java.util.LinkedList;
import java.util.List;
import java.util.Observer;
import java.util.Queue;

import main.Order;
import main.OrderList;

public class CafeQueue extends Thread implements CafeQueueInterface{

	private static Queue<Order> queue;
	private List<Observer> registeredObservers = new LinkedList<Observer>();
	
	CafeQueue(){
		
	}
	

	
	/*
	 * Run method for the thread.
	 * While there are orders left on the orderList continue adding them to the queue.
	 * Thread will sleep inbetween orders being added to delay the simulation.
	 * @see java.lang.Thread#run()
	 */
	public void run() {
		while(OrderList.getSize != 0 && queue.size() != 0) {
			addToQueue();
			notifyObservers();
			sleep(50);
		}
	}
	
	/*
	 * Add an order to the queue
	 */
	private synchronized void addToQueue() {
		Order order = OrderList.getOrder();
		sendToLog("%s has joined the queue", order.getName());
		queue.add(order);
	}
	
	/*
	 * Method for the server thread to access orders from the queue.
	 * Accessed orders will be removed from the queue.
	 */
	public static synchronized Order serveCustomer() {
		
		sendToLog("Customer Served : %s", queue.peek().getName());
		return queue.poll();
	}
	
	// Sends a string to the Log class
	private synchronized void sendToLog(String details) {
		Log log = Log.getInstance(details);
	}

	@Override
	public void registerObserver(Observer obs) {
		registeredObservers.add(obs);
		
	}

	@Override
	public void removeObserver(Observer obs) {
		registeredObservers.remove(obs);
		
	}

	@Override
	public void notifyObservers() {
		for(Observer obs : registeredObservers) obs.update();
		
	}
	
}
