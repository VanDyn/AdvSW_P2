package main;

import java.math.BigDecimal;
import java.util.LinkedList;

class Server implements Runnable 
{ 
	private String customerName;
	private BigDecimal total;
	private String description;
	private CafeQueue;
	
	public Server(CafeQueue queue)
	{
		this.CafeQueue = queue; //take class as argument
	}

	public void run()
	{
		Order order = CafeQueue.serveCustomer(); //get orders from class
		LinkedList<MenuItem> list = order.getItemList();
		int length = list.size();
		customerName = order.getName();
		BigDecimal total;
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
