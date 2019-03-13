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

public class Server implements Runnable  
{
	private String customerName;
	private BigDecimal total;
	private String description;
	private CafeQueue queue;
	private List<Observer> observers = new ArrayList<Observer>();
	private int state = 0;
	
	public Server(CafeQueue queue)
	{
		this.queue = queue; //take class as argument
	}

	public int getState() {
	   return state;
	}

	public void setState(int state) {
	   this.state = state;
	   notifyAllObservers();
	}

	public void attach(Observer observer){
	   observers.add(observer);		
	}

	public void notifyAllObservers(){
	   for (Observer observer : observers) {
	         observer.update(null, observer);
	   }
	} 	
	
	public void run()
	{
		Order order = CafeQueue.serveCustomer(); //get orders from class
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
				System.out.println("Processing: " + description + " total: " + (total).toString());
				Thread.sleep((long)(Math.random() * 3000));  //sleep random max 3 seconds
			}
		}
		catch(Exception e){}
	}

}
