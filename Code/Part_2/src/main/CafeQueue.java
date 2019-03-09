package main;
import java.util.Queue;

import part1.Order;
import part1.OrderList;

public class CafeQueue extends Thread{

	private Queue<Order> queue;
	
	CafeQueue(){
		
	}
	
	/*
	 * Run method for the thread.
	 * While there are orders left on the orderList continue adding them to the queue.
	 * Thread will sleep inbetween orders being added to delay the simulation.
	 * @see java.lang.Thread#run()
	 */
	public void run() {
		while(OrderList.getSize() != 0 && queue.size() != 0) {
			try {
				addToQueue();
				sleep(50);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	/*
	 * Add an order to the queue from the orderList
	 */
	private synchronized void addToQueue() {
		Order order = OrderList.getOrder();
		sendToLog( order.getID() + " has joined the queue");
		queue.add(order);
	}
	
	/*
	 * Method for the server thread to access orders from the queue.
	 * Accessed orders will be removed from the queue.
	 */
	public synchronized Order serveCustomer() {
		
		sendToLog("Customer Served : " + queue.peek().getID());
		return queue.poll();
	}
	
	// Sends a string to the Log class
	private synchronized void sendToLog(String details) {
		Log log = Log.getInstance(details);
	}
	
}
