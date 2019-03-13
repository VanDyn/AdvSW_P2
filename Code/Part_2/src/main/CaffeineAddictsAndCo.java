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
	   
	   
	   CafeQueue queue = new CafeQueue(i); 
	   CafeGUI gui = new CafeGUI(queue);
	   queue.start();
	   Server s1 = new Server(queue); s1.start();
	   
//	   Thread queue = new Thread(new CafeQueue(i));
//	   queue.start();
//	   CafeGUI gui = new CafeGUI();
//	   Thread s1 = new Thread(new Server());
//	   s1.start();
	   
	   //Log.INSTANCE.logToFile();
		
	}
}
