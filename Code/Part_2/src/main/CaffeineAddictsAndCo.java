package main;

import java.util.*;
import java.util.Map.Entry;

/**
 * A class to run the main method.  Please run this class!
 * 
 * @author All
 *
 */
public class CaffeineAddictsAndCo {
	
	public static OrderList ol;
	
	public static void main(String[] args) throws NotOnMenuException, CreateNewCustomerException, EmptyLinkedListException {
		
	   String filename = "src/main/existingOrders";
	   Interface i = new Interface(filename);
	   ol = new OrderList();
	   Bakery bakery = new Bakery();
	   SandwichCounter sandC = new SandwichCounter();
	   
	   KitchenStaff kitchen = new KitchenStaff(sandC,bakery);
	   Thread producerThread = new Thread(kitchen);
	   producerThread.start();
	   
	   CafeQueue queue = new CafeQueue(i);
	   Server server = new Server(queue,sandC,bakery);
	   
	   CafeGUI gui = new CafeGUI(queue, server);
	   queue.start();
	   server.start();
	   
	   //Server s1 = new Server(queue); s1.start();
		
	}
}
