package main;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.Observable;
import java.util.Observer;

import main.CafeQueue;
import main.MenuItem;
import main.Order;

import java.util.ArrayList;
import java.util.List;

public class Server extends Thread implements Subject
{
	private String customerName;
	private BigDecimal total;
	private Order order;
	private String description;
	private CafeQueue queue;
	private List<Observer> observers = new ArrayList<Observer>();
	private List<Observer> registeredObservers = new ArrayList<Observer>();

	public Server(CafeQueue q)
	{
		this.queue = q; //take class as argument
	}

	

//	public void setState(int state) {
//	   this.state = state;
//	   notifyAllObservers();
//	}

	
	
	
	// There is a lot happening in this method.  Refactor it into multiple methods?  I need these so that I can display what is happening in the GUI
	
	public void run()
	{
		while(queue.getQueueSize() != 0) {
			Order order = CafeQueue.serveCustomer(); //get orders from class
			//System.out.println(">s< Serving: " + order.getID());
			LinkedList<MenuItem> list = order.getItemList();
			int length = list.size();
			customerName = order.getID();
			BigDecimal total = new BigDecimal(0);;
			try 
			{
				for(int i=0;i<length;i++) //print processing message and sleep
				{
					MenuItem item = list.pop();
					total.add(item.getCost());
					description = item.getDescription();
					
					notifyObservers();
					sendToLog("Processing: " + description + " total: " + (total).toString());
					
					Thread.sleep((long)(Math.random() * 3000));  //sleep random max 3 seconds
				}
			}
			catch(Exception e){}
		}

	}
	
	public String getItem(){
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
		for(Observer obs : registeredObservers) {
			obs.update(null, this);
		}
		
	}


}
